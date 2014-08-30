package com.jpattern.gwt.client;

import java.util.Map;

import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.communication.NullProxy;
import com.jpattern.gwt.client.serializer.IObjectSerializer;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/mag/2011
 */
public class NullServerCallService extends AServerCallService {

	@Override
	public <T extends ICommandFacadeResult<?>> AProxy<T> get(IObjectSerializer<T> resultClassSerializer,
			ICallbackAction<T> callbackAction, String url,
			Map<String, String> keyValuesMap) {
		return new NullProxy<T>(callbackAction, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>> AProxy<T> delete(IObjectSerializer<T> resultClassSerializer,
			ICallbackAction<T> callbackAction, String url,
			Map<String, String> keyValuesMap) {
		return new NullProxy<T>(callbackAction, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> AProxy<T> post(IObjectSerializer<T> resultClassSerializer,
			IObjectSerializer<Z> dataClassSerializer, ICallbackAction<T> callbackAction, String url,
			Z data) {
		return new NullProxy<T>(callbackAction, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> AProxy<T> put(IObjectSerializer<T> resultClassSerializer,
			IObjectSerializer<Z> dataClassSerializer, ICallbackAction<T> callbackAction, String url,
			Z data) {
		return new NullProxy<T>(callbackAction, getProvider());
	}


}
