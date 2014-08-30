package com.jpattern.gwt.client.presenter;

import com.jpattern.gwt.client.navigationevent.INavigationEvent;

/**
 * Define the behavoiur of the presenter
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public interface IPresenterDefinition {

	/**
	 * The name of the presenter
	 * @return
	 */
	String getName();
	
	/**
	 * Whether the user must be logged-in to access the presenter
	 * @return
	 */
	boolean requireAuthentication();
	
	/**
	 * Return the list of allowed roles to access this presenter
	 * @return
	 */
	String[] getAllowedRoles();
	
	/**
	 * Return the navigationEvent that generated the presenter
	 * @return
	 */
	INavigationEvent getNavigationEvent();
	
}
