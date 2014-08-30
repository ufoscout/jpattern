package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * @author Francesco Cina'
 *
 * 14 Apr 2011
 */
public interface IView {
	
	/**
	 * This method is called before the visualisation of the view and after the initialitasion of the presenter. Here the provider is available.
	 */
	void init();

	void visit(IPresenter presenter);
	
	IPresenter getPresenter();
	
	void render(HasWidgets container);

	INotificationArea getNotificationArea();
	
	void onNavigationEvent(String eventName);
	
	/**
	 * The widget will be hidden if the userSession doesn't belong to one of the roles allowedRoles 
	 * @param widget
	 * @param allowedRoles
	 */
	void hideIfUserNotInRole(HasVisibility widget, String[] allowedRoles);
	
	/**
	 * The widget will be hidden if the userSession is not valid 
	 * @param widget
	 */
	void hideIfUserIsNotValid(HasVisibility widget);
	
	/**
	 * The widget will be hidden if the userSession is not valid 
	 * @param widget
	 */
	void hideIfUserHasNoRightsForNavigationEvent(HasVisibility widget, String navigationEventName);
	

	void setApplicationProvider(IApplicationProvider provider);
}
