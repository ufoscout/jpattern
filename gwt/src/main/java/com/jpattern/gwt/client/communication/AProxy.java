package com.jpattern.gwt.client.communication;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 */
public abstract class AProxy<T extends ICommandFacadeResult<?>> {

	private final ICallbackAction<T> callbackAction;
	private final IApplicationProvider provider;

	public AProxy(ICallbackAction<T> callbackAction, IApplicationProvider provider) {
		this.callbackAction = callbackAction;
		this.provider = provider;
	}
	
	public void call() throws Exception {
		execute(callbackAction, provider);
	}
	
	protected abstract void execute(ICallbackAction<T> callbackAction, IApplicationProvider provider) throws Exception;
	
}
