package com.jpattern.gwt.client.session;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public interface ISession {

	/**
	 * Create a new security context for the logged user
	 * @param userData
	 */
	void login(IUserData userData);
	
	/**
	 * clean session and userData
	 */
	void logout();

	/**
	 * clean the session data (the userData is not cleaned)
	 */
	void cleanSession();
	
	/**
	 * Return the current security context
	 * @return
	 */
	ISecurityContext getSecurityContext();
	
	/**
	 * Retrieve an attribute from the session
	 * @param <T>
	 * @param aClass
	 * @param key
	 * @return
	 */
	<T> T getAttribute(String key, Class<T> aClass);
	
	/**
	 * Set a new attribute in session
	 * @param key
	 * @param value
	 */
	void addAttribute(String key, Object value);
	
	/**
	 * Remove the attribute
	 * @param key
	 */
	void removeAttribute(String key);
	
	void addObserver(ISecurityContextObserver observer);
	
	void removeObserver(ISecurityContextObserver observer);

	void addObserver(ISessionObserver observer);
	
	void removeObserver(ISessionObserver observer);
	
}
