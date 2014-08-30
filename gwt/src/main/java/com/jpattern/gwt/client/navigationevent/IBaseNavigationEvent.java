package com.jpattern.gwt.client.navigationevent;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public interface IBaseNavigationEvent {

	/**
	 * Return the name of the NavigationEvent
	 * @return
	 */
	String getName();
	
	/**
	 * return a list of all the roles allowed to access this INavigationEvent
	 * @return
	 */
	String[] getAllowedRole();

	boolean isRequireAuthentication();
	
}
