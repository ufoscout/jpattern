package com.jpattern.gwt.client.communication;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/mag/2011
 */
public class NullProxy<T extends ICommandFacadeResult<?>> extends AProxy<T> {

	public NullProxy(ICallbackAction<T> callbackAction, IApplicationProvider provider) {
		super(callbackAction, provider);
	}

	@Override
	protected void execute(ICallbackAction<T> callbackAction, IApplicationProvider provider) throws Exception {
	}

}
