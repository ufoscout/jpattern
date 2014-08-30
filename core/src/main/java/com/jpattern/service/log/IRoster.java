package com.jpattern.service.log;

import java.io.Serializable;

import com.jpattern.service.log.event.IEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public interface IRoster extends Serializable {

	void addEvent(IEvent traceEvent);
	
	void reinstateEvent(String eventName);

	void suspendEvent(String eventName);
	
}
