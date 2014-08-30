package com.jpattern.gwt.event;

import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.view.INotificationArea;
import com.jpattern.gwt.client.view.IView;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class MockView implements IView {
	
	private MockErrorArea mockErrorArea;
	private IPresenter presenter;

	public MockView() {
		mockErrorArea = new MockErrorArea();
		setErrorArea(mockErrorArea);
	}
	
	@Override
	public MockErrorArea getNotificationArea() {
		return (MockErrorArea) mockErrorArea;
	}

	@Override
	public void render(HasWidgets container) {
	}
	
	@Override
	public final void visit(IPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public final IPresenter getPresenter() {
		return presenter;
	}

	public void setErrorArea(INotificationArea errorArea) {
	}
//
//	@Override
//	public HasWidgets getNavigationTarget(String name) {
//		return new NullHasWidgets();
//	}
//
//	@Override
//	public void registerDefaultEventTarget(HasWidgets hasWidgets) {
//	}
//
//	@Override
//	public void registerEventTarget(String navigationEventName, HasWidgets hasWidgets) {
//	}

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
		// TODO Auto-generated method stub
		
	}

}
