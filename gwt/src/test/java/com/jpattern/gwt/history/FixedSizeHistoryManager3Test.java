package com.jpattern.gwt.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.history.HistoryService;
import com.jpattern.gwt.client.history.IHistoryManager;
import com.jpattern.gwt.client.history.IHistoryManagerObserver;
import com.jpattern.gwt.client.history.DirectHistoryEngine;
import com.jpattern.gwt.client.logger.SysoutLoggerService;
import com.jpattern.gwt.client.navigationevent.ANavigationEvent;
import com.jpattern.gwt.client.navigationevent.NullNavigationEventCallback;
import com.jpattern.gwt.client.navigationevent.RootNavigationEvent;
import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.IPresenterDefinition;
import com.jpattern.gwt.client.presenter.NullPresenter;
import com.jpattern.gwt.client.view.IShowViewStrategy;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullHasWidgets;
import com.jpattern.gwt.client.view.NullShowViewStrategy;
import com.jpattern.gwt.client.view.NullView;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/ago/2011
 */
public class FixedSizeHistoryManager3Test extends BaseTest {

	public static List<IPresenter> createdPresenterList = new ArrayList<IPresenter>();
	private AApplication applicationProvider;
	private HistoryManagerObserver historyManagerObserver = new HistoryManagerObserver();
	
	@Override
	protected void setUp() throws Exception {
		applicationProvider = new AApplication() {
			public void stopSystem() {}
			public void startSystem() {	
				setLoggerService(new SysoutLoggerService());
				setHistoryService(new HistoryService(new DirectHistoryEngine(getLoggerService()), historyManagerObserver,getLoggerService()));
			}		};
		applicationProvider.startSystem();
	}

	@Override
	protected void tearDown() throws Exception {
	}

	public void testHistoryManager() throws Exception {
		
		MockMainNavigationEvent mainNavigationEvent = new MockMainNavigationEvent("");
		mainNavigationEvent.registerNavigationEvent(new NavEvent("event1"), new NullHasWidgets(), new NullNavigationEventCallback());
		mainNavigationEvent.registerNavigationEvent(new NavEvent("event2"), new NullHasWidgets(), new NullNavigationEventCallback());
		mainNavigationEvent.registerNavigationEvent(new NavEvent("event3"), new NullHasWidgets(), new NullNavigationEventCallback());
		mainNavigationEvent.registerNavigationEvent(new NavEvent("event4"), new NullHasWidgets(), new NullNavigationEventCallback());
		
		RootNavigationEvent rootNavigationEvent = new RootNavigationEvent( mainNavigationEvent, new NullHasWidgets(),applicationProvider);
		rootNavigationEvent.startApplication();
		IPresenter rootPresenter = mainNavigationEvent.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		System.out.println("App started");
		
		rootPresenter.launchNavigationEvent("noEvent", true);
		assertEquals( "" , historyManagerObserver.eventName );
		
		System.out.println("------- START EVENT event1 -------" );
		rootPresenter.launchNavigationEvent("event1", true);
		assertEquals( "_event1" , historyManagerObserver.eventName );
		assertEquals( 1 , createdPresenterList.size() );
		assertEquals( "event1", createdPresenterList.get(0).getNavigationEvent().getName());
		assertEquals( 0, ((SimplePresenter)createdPresenterList.get(0)).id);
		createdPresenterList.clear();
		
		System.out.println("------- START EVENT event2 -------" );
		rootPresenter.launchNavigationEvent("event2", true);
		assertEquals( "_event2" , historyManagerObserver.eventName );
		assertEquals( 1 , createdPresenterList.size() );
		assertEquals( "event2", createdPresenterList.get(0).getNavigationEvent().getName());
		assertEquals( 1, ((SimplePresenter)createdPresenterList.get(0)).id);
		createdPresenterList.clear();
		
		rootPresenter.launchNavigationEvent("event3", true);
		assertEquals( "_event3" , historyManagerObserver.eventName );
		assertEquals( 1 , createdPresenterList.size() );
		assertEquals( "event3", createdPresenterList.get(0).getNavigationEvent().getName());
		assertEquals( 2, ((SimplePresenter)createdPresenterList.get(0)).id);
		createdPresenterList.clear();
		
		rootPresenter.launchNavigationEvent("event4", true);
		assertEquals( "_event4" , historyManagerObserver.eventName );
		assertEquals( 1 , createdPresenterList.size() );
		assertEquals( "event4", createdPresenterList.get(0).getNavigationEvent().getName());
		assertEquals( 3, ((SimplePresenter)createdPresenterList.get(0)).id);
		createdPresenterList.clear();
		
		IHistoryManager historyManager = applicationProvider.getHistoryService().getHistoryManager();
		
		historyManager.onEvent( "_event1" );
		assertEquals( "_event1" , historyManagerObserver.eventName );
		assertEquals( 1 , createdPresenterList.size() );
		assertEquals( "event1", createdPresenterList.get(0).getNavigationEvent().getName());
		assertEquals( 4, ((SimplePresenter)createdPresenterList.get(0)).id);
		createdPresenterList.clear();
		
		historyManager.onEvent( "_event3" );
		assertEquals( "_event3" , historyManagerObserver.eventName );
		assertEquals( 1 , createdPresenterList.size() );
		assertEquals( "event3", createdPresenterList.get(0).getNavigationEvent().getName());
		assertEquals( 5, ((SimplePresenter)createdPresenterList.get(0)).id);
	}
	
	class HistoryManagerObserver implements IHistoryManagerObserver {
		
		String eventName = "";

		@Override
		public void onRegisterHistory(String currentToken, List<String> presenterHierarchy) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("current token: [" + currentToken + "]");
			System.out.println("-----------------------------------------------------------");
		}

		@Override
		public void onEvent(String eventName) {
			this.eventName = eventName;
			System.out.println("-----------------------------------------------------------");
			System.out.println("history event: [" + eventName + "]");
			System.out.println("-----------------------------------------------------------");
		}
		
	}
	
	class NavEvent extends ANavigationEvent {

		public NavEvent(String eventName) {
			super(eventName);
		}

		@Override
		public IPresenter exec(Map<String, String> queryStringMap) {
			System.out.println("Event exec() on " + getName());
			return new SimplePresenter(new NullShowViewStrategy<IView>(new NullView()), getPresenterDefinition());
		}
		
	}
	
	public static class SimplePresenter extends APresenter<IView> {

		public static int ID_COUNT = 0;
		public int id;
		
		public SimplePresenter(IShowViewStrategy<IView> showViewStrategy, IPresenterDefinition navigationEvent) {
			super(showViewStrategy, navigationEvent);
			createdPresenterList.add(this);
			id = ID_COUNT++;
			System.out.println("SimplePresenter " + id + " created");
		}

		@Override
		public void preRender() {
			System.out.println("SimplePresenter " + id + " preDisplay");
		}

		@Override
		public void init() {
			System.out.println("SimplePresenter " + id + " postDisplay");
		}
		
		@Override
		public void onGlobalEvent(String status) {
		}

		@Override
		protected void onUnloadPresenter() {
		}
		
	}
	
}
