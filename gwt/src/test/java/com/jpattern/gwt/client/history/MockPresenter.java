package com.jpattern.gwt.client.history;

import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.IPresenterDefinition;
import com.jpattern.gwt.client.view.INotificationArea;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullNotificationArea;
import com.jpattern.gwt.client.view.NullShowViewStrategy;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public class MockPresenter extends APresenter<IView> {

	String viewEvent = "";
	
	public MockPresenter(IPresenterDefinition navigationEvent) {
		super(new NullShowViewStrategy<IView>(new View()), navigationEvent);
	}

	@Override
	public void preRender() {
	}

	@Override
	public void init() {
		ready();
		getLogger().info("init", "Presenter ready");
		System.out.println("--- INIT --- MockPresenter ready");
	}
	
	static class View implements IView {

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
			System.out.println("View onNavigationEvent [" + eventName + "]");
			((MockPresenter) getPresenter()).viewEvent = eventName;			
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
		}

		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public void onGlobalEvent(String status) {
	}

	@Override
	protected void onUnloadPresenter() {
	}

}
