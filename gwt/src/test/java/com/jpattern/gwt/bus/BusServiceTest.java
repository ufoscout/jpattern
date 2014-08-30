package com.jpattern.gwt.bus;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.bus.ABusService;
import com.jpattern.gwt.client.bus.IEventObserver;
import com.jpattern.gwt.client.bus.INavigationEventObserver;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.event.AEvent;
import com.jpattern.gwt.client.event.EventResult;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventResult;
import com.jpattern.gwt.client.event.NullEventCallback;
import com.jpattern.gwt.client.history.DirectHistoryEngine;
import com.jpattern.gwt.client.history.HistoryService;
import com.jpattern.gwt.client.history.NullHistoryManagerObserver;
import com.jpattern.gwt.client.logger.SysoutLoggerService;
import com.jpattern.gwt.client.navigationevent.ANavigationEvent;
import com.jpattern.gwt.client.navigationevent.NullNavigationEventCallback;
import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.NullPresenter;
import com.jpattern.gwt.client.presenter.PresenterDefinition;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullHasWidgets;
import com.jpattern.gwt.client.view.NullShowViewStrategy;
import com.jpattern.gwt.client.view.NullView;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * Dec 5, 2011
 */
public class BusServiceTest extends BaseTest {

	private static final String INNER_NAVIGATION_EVENT_NAME_1 = "innerNavigationEventName1";
	private static final String INNER_NAVIGATION_EVENT_NAME_2 = "innerNavigationEventName2";
	private AApplication applicationProvider;
	
	protected void setUp() throws Exception {
		super.setUp();
		applicationProvider = new AApplication() {
			public void stopSystem() {}
			public void startSystem() {	
				setLoggerService(new SysoutLoggerService());
				setHistoryService(new HistoryService(new DirectHistoryEngine(getLoggerService()), new NullHistoryManagerObserver(), getLoggerService()));
			}		};
		applicationProvider.startSystem();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testObserversSize() throws Exception {
		ABusService bus = applicationProvider.getBusService();
		
		int eventObservers = bus.getEventObservers().size();
		int navEventObservers = bus.getNavigationEventObservers().size();
		int statusObservers = bus.getStatusObservers().size();
		
		
		NavEvent event = new NavEvent("");
		ObserverPresenter presenter1 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		ObserverPresenter presenter2 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		
		assertEquals( eventObservers+2, bus.getEventObservers().size());
		assertEquals( navEventObservers+2, bus.getNavigationEventObservers().size());
		assertEquals( statusObservers+2, bus.getStatusObservers().size());
		
		presenter1.onUnload();
		presenter2.onUnload();
		
		assertEquals( eventObservers, bus.getEventObservers().size());
		assertEquals( navEventObservers, bus.getNavigationEventObservers().size());
		assertEquals( statusObservers, bus.getStatusObservers().size());
	}

	public void testNavigationEventObserver() throws Exception {
		NavEvent event = new NavEvent("");
		ObserverPresenter presenter1 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		ObserverPresenter presenter2 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		applicationProvider.getHistoryService().getHistoryManager().setRootPresenter(presenter1);
		
		presenter1.launchNavigationEvent(INNER_NAVIGATION_EVENT_NAME_1, false);
		
		assertEquals(INNER_NAVIGATION_EVENT_NAME_1, presenter1.lastNavigationEventName);
		assertEquals(INNER_NAVIGATION_EVENT_NAME_1, presenter2.lastNavigationEventName);
		
		presenter2.launchNavigationEvent(INNER_NAVIGATION_EVENT_NAME_2, true);
		
		assertEquals(INNER_NAVIGATION_EVENT_NAME_2, presenter1.lastNavigationEventName);
		assertEquals(INNER_NAVIGATION_EVENT_NAME_2, presenter2.lastNavigationEventName);
	}
	
	public void testEventObserver() throws Exception {
		NavEvent event = new NavEvent("");
		ObserverPresenter presenter1 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		ObserverPresenter presenter2 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		
		presenter1.event(false);
		
		assertFalse(presenter1.eventSuccess);
		assertFalse(presenter2.eventSuccess);
		assertEquals(LocalEvent.class, presenter1.lastEventStart);
		assertEquals(LocalEvent.class, presenter2.lastEventStart);
		assertEquals(LocalEvent.class, presenter1.lastEventEnd);
		assertEquals(LocalEvent.class, presenter2.lastEventEnd);
		
		presenter1.event(true);
		
		assertTrue(presenter1.eventSuccess);
		assertTrue(presenter2.eventSuccess);
	}
	
	public void testStatusObserver() throws Exception {
		NavEvent event = new NavEvent("");
		ObserverPresenter presenter1 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		ObserverPresenter presenter2 = (ObserverPresenter) event.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		
		assertEquals("", presenter1.status);
		assertEquals("", presenter2.status);
		
		ABusService bus = applicationProvider.getBusService();
		
		bus.notifyGlobalEvent("test1");
		
		assertEquals("test1", presenter1.status);
		assertEquals("test1", presenter2.status);
	
		bus.notifyGlobalEvent(null);
		
		assertEquals("test1", presenter1.status);
		assertEquals("test1", presenter2.status);
	}
	
	
	class NavEvent extends ANavigationEvent {

		public NavEvent(String eventName) {
			super(eventName);
		}

		@Override
		protected ObserverPresenter exec(Map<String, String> queryStringValues) {
			ObserverPresenter presenter = new ObserverPresenter();
			return presenter;
		}
	}
	
	class ObserverPresenter extends APresenter<IView> implements IEventObserver, INavigationEventObserver {

		Class<?> lastEventStart;
		Class<?> lastEventEnd;
		boolean eventSuccess;
		String lastNavigationEventName;
		String status = "";
		
		public ObserverPresenter() {
			super(new NullShowViewStrategy<IView>(new NullView()), new PresenterDefinition());
			registerNavigationEvent(new NavEvent(""), new NullHasWidgets(), new NullNavigationEventCallback());
			registerNavigationEvent(new NavEvent(INNER_NAVIGATION_EVENT_NAME_1), new NullHasWidgets(), new NullNavigationEventCallback());
			registerNavigationEvent(new NavEvent(INNER_NAVIGATION_EVENT_NAME_2), new NullHasWidgets(), new NullNavigationEventCallback());
		}

		@Override
		public <T> void onEventStart(Class<? extends IEvent<T>> eventClass) {
			System.out.println("onBusEventStart called by class " + eventClass);
			lastEventStart = eventClass;
		}

		@Override
		public <T> void onEventEnd(Class<? extends IEvent<T>> eventClass,
				IEventResult<T> eventResult) {
			System.out.println("onBusEventEnd called by class " + eventClass + " . Is Event valid? " + eventResult.isValid());
			lastEventEnd = eventClass;
			eventSuccess = eventResult.isValid();
		}

		@Override
		public void onNavigationEvent(String navigationEventName) {
			System.out.println("onBusNavigationEvent called with event name: " + navigationEventName);
			lastNavigationEventName = navigationEventName;			
		}

		@Override
		public void onGlobalEvent(String status) {
			this.status = status;			
		}

		@Override
		protected void init() {
			getProvider().getBusService().addNavigationEventObserver(this);
			getProvider().getBusService().addEventObserver(this);
		}

		@Override
		protected void preRender() {
		}

		@Override
		protected void onUnloadPresenter() {
			getProvider().getBusService().removeNavigationEventObserver(this);
			getProvider().getBusService().removeEventObserver(this);
		}
		
		void event(boolean valid) {
			AEvent<String> event = new LocalEvent(this, valid);
			event.launch(new NullEventCallback<String>());
		}
		
	}
	
	class LocalEvent extends AEvent<String> {

		private final boolean valid;

		public LocalEvent(IPresenter presenter, boolean valid) {
			super(presenter , applicationProvider);
			this.valid = valid;
		}

		@Override
		protected ACommand exec() {
			return new ACommand() {
				
				@Override
				protected void exec(ICommandResult commandResult) {
				}
			};
		}

		@Override
		protected IEventResult<String> afertExec(ICommandResult webResult) {
			if (!valid) {
				webResult.getErrorMessages().add(new ErrorMessage("", ""));
			}
			return new EventResult<String>(webResult, "");
		}
		
	}
}
