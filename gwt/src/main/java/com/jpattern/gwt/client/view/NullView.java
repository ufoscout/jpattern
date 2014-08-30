package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.NullPresenter;

/**
 * 
 * @author Francesco Cina
 *
 * 01/ago/2011
 */
public class NullView implements IView {

	private IPresenter presenter = new NullPresenter(new NullApplicationProvider());

	@Override
	public void visit(IPresenter presenter) {
		this.presenter  = presenter;
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
	public void hideIfUserNotInRole(HasVisibility widget, String[] allowedRoles) {
	}

	@Override
	public void hideIfUserIsNotValid(HasVisibility widget) {
	}

	@Override
	public void setApplicationProvider(IApplicationProvider provider) {
	}

	@Override
	public void hideIfUserHasNoRightsForNavigationEvent(HasVisibility widget,
			String navigationEventName) {
	}

	@Override
	public void init() {
	}

}
