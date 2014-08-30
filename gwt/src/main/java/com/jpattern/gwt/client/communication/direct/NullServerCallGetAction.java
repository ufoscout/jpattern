package com.jpattern.gwt.client.communication.direct;

import java.util.Map;

import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class NullServerCallGetAction implements IServerCallGetAction, IServerCallDeleteAction, IServerCallPutAction, IServerCallPostAction {

	@Override
	public <T extends ICommandFacadeResult<?>> T get(Class<T> resultClass,
			String url,	Map<String, String> keyValuesMap) {
		return null;
	}

	@Override
	public <T extends ICommandFacadeResult<?>> T delete(Class<T> resultClass,
			String url,	Map<String, String> keyValuesMap) {
		return null;
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> T post(Class<T> resultClass,
			Class<Z> dataClass, String url,	Z data) {
		return null;
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> T put(Class<T> resultClass,
			Class<Z> dataClass, String url,	Z data) {
		return null;
	}

}
