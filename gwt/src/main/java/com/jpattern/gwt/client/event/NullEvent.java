package com.jpattern.gwt.client.event;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/mag/2011
 */
public class NullEvent<T> implements IEvent<T> {

	@Override
	public void launch(IEventCallback<T> eventCallBack) {
	}

}
