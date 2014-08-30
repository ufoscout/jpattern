package com.jpattern.gwt.client.communication;

/**
 * 
 * @author Francesco Cina'
 *
 * 11 Apr 2011
 */
public interface ICallbackAction<T> {

	void onSuccess(T result, int responseCode);
	
	void onError(Throwable exception, int responseCode);
	
}
