package com.jpattern.gwt.client.event;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/mag/2011
 */
public interface IEvent<T> {

	void launch(IEventCallback<T> eventCallback);

}