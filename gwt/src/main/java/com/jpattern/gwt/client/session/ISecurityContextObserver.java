package com.jpattern.gwt.client.session;

/**
 * 
 * @author cinafr
 *
 */
public interface ISecurityContextObserver {

	/**
	 * Callback action when a new IUserData is put in the SecurityContext 
	 * @param userData the user that performed the login
	 */
	void onUserLogin(IUserData userData);
	
	/**
	 * Callback action on user logout
	 * @param userData the user that performed the logout
	 */
	void onUserLogout(IUserData userData);
}
