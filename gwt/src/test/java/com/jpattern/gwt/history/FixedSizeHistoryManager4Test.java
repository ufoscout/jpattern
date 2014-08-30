package com.jpattern.gwt.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.history.HistoryService;
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
import com.jpattern.gwt.client.view.INotificationArea;
import com.jpattern.gwt.client.view.IShowViewStrategy;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullNotificationArea;
import com.jpattern.gwt.client.view.NullHasWidgets;
import com.jpattern.gwt.client.view.NullShowViewStrategy;
import com.jpattern.gwt.client.view.NullView;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/ago/2011
 */
public class FixedSizeHistoryManager4Test extends BaseTest {

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
//		rootNavigationEvent.addInitialNavigationEvent("event1");
		RootNavigationEvent rootNavigationEvent = new RootNavigationEvent( mainNavigationEvent, new NullHasWidgets(),applicationProvider);
		rootNavigationEvent.startApplication();
		System.out.println("App started");
		
		IPresenter rootPresenter = mainNavigationEvent.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		rootPresenter.launchNavigationEvent("event1", true);
		
		assertEquals( "_event1" , historyManagerObserver.eventName );
		assertEquals( 1 , createdPresenterList.size() );
		assertEquals( "event1", createdPresenterList.get(0).getNavigationEvent().getName());
		
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
	
	static class SimplePresenter extends APresenter<IView> {

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
	
	class SimpleView implements IView {

		private IPresenter presenter;

		@Override
		public void visit(IPresenter presenter) {
			this.presenter = presenter;
		}

		@Override
		public IPresenter getPresenter() {
			return presenter;
		}

		@Override
		public void render(HasWidgets container) {
		}

		@Override
		public INotificationArea getNotificationArea() {
			return new NullNotificationArea();
		}

		@Override
		public void onNavigationEvent(String eventName) {
		}

		@Override
		public void hideIfUserNotInRole(HasVisibility widget,
				String[] allowedRoles) {
		}

		@Override
		public void hideIfUserIsNotValid(HasVisibility widget) {
		}

		@Override
		public void setApplicationProvider(IApplicationProvider provider) {
		}

		@Override
		public void hideIfUserHasNoRightsForNavigationEvent(
				HasVisibility widget, String navigationEventName) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
