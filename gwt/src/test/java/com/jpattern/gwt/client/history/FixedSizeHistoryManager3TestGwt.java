package com.jpattern.gwt.client.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.history.HistoryService;
import com.jpattern.gwt.client.history.IHistoryManager;
import com.jpattern.gwt.client.history.IHistoryManagerObserver;
import com.jpattern.gwt.client.logger.SysoutLoggerService;
import com.jpattern.gwt.client.navigationevent.ANavigationEvent;
import com.jpattern.gwt.client.navigationevent.NullNavigationEventCallback;
import com.jpattern.gwt.client.navigationevent.RootNavigationEvent;
import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.IPresenterDefinition;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullHasWidgets;
import com.jpattern.gwt.client.view.NullShowViewStrategy;
import com.jpattern.gwt.client.view.NullView;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/ago/2011
 */
public class FixedSizeHistoryManager3TestGwt extends BaseGwtTestCase {
	
	public static List<IPresenter> createdPresenterList = new ArrayList<IPresenter>();
	private static AApplication applicationProvider;
	private final HistoryManagerObserver historyManagerObserver = new HistoryManagerObserver();
	private static final List<String> calledEventList = new ArrayList<String>();
	private static String CHIL_EVENT_NAME_1 = "childEvent1";
	private static String CHIL_EVENT_NAME_2 = "childEvent2";
	
	@Override
	protected void gwtTestCaseSetUp() throws Exception {
		calledEventList.clear();
		SimplePresenter1.ID_COUNT = 0;
		SimplePresenter2.ID_COUNT = 0;
		applicationProvider = new AApplication() {
			public void stopSystem() {}
			public void startSystem() {	
				setLoggerService(new SysoutLoggerService());
				setHistoryService(new HistoryService(new GWTHistoryEngine(), historyManagerObserver,getLoggerService()));
			}
		};
		applicationProvider.startSystem();
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}
	
	public void testHistoryManager1() throws Exception {
		
		MockMainNavigationEvent mainNavigationEvent = new MockMainNavigationEvent("");
		mainNavigationEvent.registerNavigationEvent(new NavEvent1("event"), new NullHasWidgets(), new NullNavigationEventCallback());
		Map<String, String> queryString1 = new HashMap<String, String>();
		queryString1.put("key1", "value1");
		mainNavigationEvent.addInitialNavigationEvent("event", queryString1);
		
		RootNavigationEvent rootNavigationEvent = new RootNavigationEvent( mainNavigationEvent, new NullHasWidgets(), applicationProvider);
		rootNavigationEvent.startApplication();
		
		assertEquals( 2 , createdPresenterList.size() );
		
		calledEventList.clear();
		createdPresenterList.clear();
		
		System.out.println("LAUNCH HISTORY");
		IHistoryManager historyManager = applicationProvider.getHistoryService().getHistoryManager();
		
		historyManager.onEvent( "_event_" + CHIL_EVENT_NAME_2 + "?key1=value1" );
		assertEquals( "_event_" + CHIL_EVENT_NAME_2 + "?key1=value1" , historyManagerObserver.eventName );
		assertEquals( 2 , createdPresenterList.size() );
		assertEquals( "event", createdPresenterList.get(0).getNavigationEvent().getName());
		assertEquals( CHIL_EVENT_NAME_2, createdPresenterList.get(1).getNavigationEvent().getName());
		
		System.out.println("Launched events: ");
		for (String event : calledEventList) {
			System.out.println(event);
		}
		assertEquals( 2, calledEventList.size() );
		assertEquals( "event", calledEventList.get(0) );
		assertEquals( CHIL_EVENT_NAME_2, calledEventList.get(1) );
		calledEventList.clear();
		
	}
	
	
	class HistoryManagerObserver implements IHistoryManagerObserver {
		
		String eventName = "";

		@Override
		public void onRegisterHistory(String currentToken, List<String> presenterHierarchy) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("current token: [" + currentToken + "]");
			System.out.println("historyMap content:");
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
	
	static class NavEvent1 extends ANavigationEvent {

		public NavEvent1(String eventName) {
			super(eventName);
		}

		@Override
		public IPresenter exec(Map<String, String> queryStringMap) {
			calledEventList.add(getName());
			System.out.println("NavEvent1 exec() on " + getName());
			
			assertEquals( 1 , queryStringMap.size());
			assertEquals( "value1" , queryStringMap.get("key1") );
			
			return new SimplePresenter1(getPresenterDefinition());
		}
		
	}
	
	static class NavEvent2 extends ANavigationEvent {

		public NavEvent2(String eventName) {
			super(eventName);
		}

		@Override
		public IPresenter exec(Map<String, String> queryStringMap) {
			calledEventList.add(getName());
			System.out.println("NavEvent2 exec() on " + getName());
			return new SimplePresenter2(getPresenterDefinition());
		}
		
	}
	
	static class SimplePresenter1 extends APresenter<IView> {

		public static int ID_COUNT = 0;
		public int id;
		
		public SimplePresenter1(IPresenterDefinition navigationEvent) {
			super(new NullShowViewStrategy<IView>(new NullView()), navigationEvent);
			createdPresenterList.add(this);
			id = ID_COUNT++;
			System.out.println("SimplePresenter1 " + id + " created");
			HasWidgets eventTarget = new NullHasWidgets();
			registerNavigationEvent(new NavEvent2(CHIL_EVENT_NAME_1), eventTarget , new NullNavigationEventCallback());
			registerNavigationEvent(new NavEvent2(CHIL_EVENT_NAME_2), eventTarget , new NullNavigationEventCallback());
			launchNavigationEvent(CHIL_EVENT_NAME_1, false);
		}

		@Override
		public void preRender() {
			System.out.println("SimplePresenter1 " + id + " preDisplay");
		}

		@Override
		public void init() {
			System.out.println("SimplePresenter1 " + id + " postDisplay");
			ready();
		}
		
		@Override
		public void onGlobalEvent(String status) {
		}

		@Override
		protected void onUnloadPresenter() {
		}
	}
	
	static class SimplePresenter2 extends APresenter<IView> {

		public static int ID_COUNT = 0;
		public int id;
		
		public SimplePresenter2(IPresenterDefinition navigationEvent) {
			super(new NullShowViewStrategy<IView>(new NullView()), navigationEvent);
			createdPresenterList.add(this);
			id = ID_COUNT++;
			System.out.println("SimplePresenter2 " + id + " created");
		}

		@Override
		public void preRender() {
			System.out.println("SimplePresenter2 " + id + " preDisplay");
		}

		@Override
		public void init() {
			System.out.println("SimplePresenter2 " + id + " postDisplay");
			ready();
		}
		
		@Override
		public void onGlobalEvent(String status) {
		}

		@Override
		protected void onUnloadPresenter() {
		}
	}
	
}
