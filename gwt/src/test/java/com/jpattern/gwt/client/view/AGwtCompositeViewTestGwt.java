package com.jpattern.gwt.client.view;

import java.util.Map;

import com.google.gwt.user.client.ui.HasVisibility;
import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.history.DirectHistoryEngine;
import com.jpattern.gwt.client.history.HistoryService;
import com.jpattern.gwt.client.history.NullHistoryManagerObserver;
import com.jpattern.gwt.client.logger.SysoutLoggerService;
import com.jpattern.gwt.client.navigationevent.ANavigationEvent;
import com.jpattern.gwt.client.navigationevent.NullNavigationEventCallback;
import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.IPresenterDefinition;
import com.jpattern.gwt.client.presenter.NullPresenter;
import com.jpattern.gwt.client.presenter.PresenterDefinition;
import com.jpattern.gwt.client.session.UserData;
import com.jpattern.gwt.client.view.AGwtCompositeView;
import com.jpattern.gwt.client.view.INotificationArea;
import com.jpattern.gwt.client.view.NullNotificationArea;
import com.jpattern.gwt.client.view.NullHasWidgets;
import com.jpattern.gwt.client.view.NullShowViewStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public class AGwtCompositeViewTestGwt extends BaseGwtTestCase {

	private String event1Name = "event1Name";
	private String[] event1Roles = new String[]{"event1Role"};
	private String event2Name = "event2Name";
	private String[] event2Roles = new String[]{"event2Role"};
	private AApplication provider;

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
		provider = new AApplication() {
			public void stopSystem() {}
			public void startSystem() {
				setLoggerService(new SysoutLoggerService());
				setHistoryService(new HistoryService(new DirectHistoryEngine(getLoggerService()), new NullHistoryManagerObserver(), getLoggerService()));				
			}
		};
		provider.startSystem();
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}
	
	public void testHide0() throws Exception {
		View view = new View();
		Presenter presenter = new Presenter(view, new PresenterDefinition("", false, new String[0]));
		presenter.setProvider(provider);
		presenter.initPresenter();
		
		assertFalse( view.event1HasVisibility.isVisible() );
		assertFalse( view.event2HasVisibility.isVisible() );
		
	}
	
	public void testHide1() throws Exception {
		
		provider.getSession().login(new UserData("", true, event1Roles));
		
		View view = new View();
		Presenter presenter = new Presenter(view, new PresenterDefinition("", false, new String[0]));
		presenter.setProvider(provider);
		presenter.initPresenter();
		
		assertTrue( view.event1HasVisibility.isVisible() );
		assertFalse( view.event2HasVisibility.isVisible() );
		
	}
	
	class View extends AGwtCompositeView<IPresenter> {

		final HasVisibility event1HasVisibility = new Visible();
		final HasVisibility event2HasVisibility = new Visible();
		
		@Override
		public void init() {
			hideIfUserHasNoRightsForNavigationEvent(event1HasVisibility, event1Name);
			hideIfUserHasNoRightsForNavigationEvent(event2HasVisibility, event2Name);
		}
		
		@Override
		public INotificationArea getNotificationArea() {
			return new NullNotificationArea();
		}

		@Override
		public void onNavigationEvent(String eventName) {
		}

		@Override
		protected void onUnloadView() {
		}
		
	}
	
	class Presenter extends APresenter<View> {

		public Presenter(View view, IPresenterDefinition presenterDefinition) {
			super(new NullShowViewStrategy<View>(view), presenterDefinition);
			
			registerNavigationEvent(new NavigationEvent(event1Name, true, event1Roles), new NullHasWidgets(), new NullNavigationEventCallback());
			registerNavigationEvent(new NavigationEvent(event2Name, false, event2Roles), new NullHasWidgets(), new NullNavigationEventCallback());
			
		}

		@Override
		protected void init() {
		}

		@Override
		protected void preRender() {
		}

		@Override
		public void onGlobalEvent(String status) {
		}

		@Override
		protected void onUnloadPresenter() {
		}
		
	}
	
	class NavigationEvent extends ANavigationEvent {

		public NavigationEvent(String eventName, boolean requiredAuthentication, String[] rolesAllowed) {
			super(eventName, requiredAuthentication, rolesAllowed);
		}

		@Override
		protected IPresenter exec(Map<String, String> queryStringValues) {
			return new NullPresenter(new NullApplicationProvider());
		}
		
	}

}
