package com.jpattern.gwt.client.communication.direct;

import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public interface IServerCallPostAction extends IServerCallAction {

	public <T extends ICommandFacadeResult<?>, Z> T post(Class<T> resultClass, Class<Z> dataClass, String url, Z data);
	
}
