package com.jpattern.gwt.client.bus;

import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventResult;

/**
 * 
 * @author Francesco Cina'
 *
 * Dec 5, 2011
 */
public interface IEventObserver {

	<T> void onEventStart(Class<? extends IEvent<T>> eventClass);
	
	<T> void onEventEnd(Class<? extends IEvent<T>> eventClass, IEventResult<T> eventResult);
	
}
