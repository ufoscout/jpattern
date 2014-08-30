package com.jpattern.gwt.client.navigationevent;

/**
 * A callback action called after the launch of an INavigationEvent.
 * this is usually used to modify something in the parent view, e.g. the active link related to the event.
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public interface INavigationEventCallback {

	void callback();
	
}
