package com.jpattern.gwt.client.session;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public interface ISessionObserver {

	/**
	 * Callback action when the session is cleaned
	 */
	void onSessionClean();
	
	/**
	 * Callback action when a new attribute has been added
	 * @param key
	 */
	void onAttributeAdded(String key);
	
	/**
	 * Callback action when an attribute has been removed
	 * @param key
	 */
	void onAttributeRemoved(String key);
	
}
