package com.jpattern.gwt.client.bus;

/**
 * An obeserver notified on application status change
 * @author Francesco Cina'
 *
 * Dec 5, 2011
 */
public interface IGlobalEventObserver {

	void onGlobalEvent(String globalEventName);
	
}
