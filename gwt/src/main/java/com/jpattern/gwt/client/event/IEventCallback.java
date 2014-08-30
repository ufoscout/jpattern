package com.jpattern.gwt.client.event;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/mag/2011
 */
public interface IEventCallback<T> {

	/**
	 * Return the result of the event execution
	 * @param eventResult
	 */
	void callback(IEventResult<T> eventResult);
}
