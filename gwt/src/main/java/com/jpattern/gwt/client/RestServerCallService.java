package com.jpattern.gwt.client;

import java.util.Map;

import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.communication.rest.DeleteProxy;
import com.jpattern.gwt.client.communication.rest.GetProxy;
import com.jpattern.gwt.client.communication.rest.PostProxy;
import com.jpattern.gwt.client.communication.rest.PutProxy;
import com.jpattern.gwt.client.serializer.IObjectSerializer;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/mag/2011
 */
public class RestServerCallService extends AServerCallService {
	
	public RestServerCallService() {
	}

	@Override
	public <T extends ICommandFacadeResult<?>> AProxy<T> get(IObjectSerializer<T> resultClassSerializer,
			ICallbackAction<T> callbackAction, String url,
			Map<String, String> keyValuesMap) {
		return new GetProxy<T>(callbackAction, resultClassSerializer, url, keyValuesMap, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>> AProxy<T> delete(IObjectSerializer<T> resultClassSerializer,
			ICallbackAction<T> callbackAction, String url,
			Map<String, String> keyValuesMap) {
		return new DeleteProxy<T>(callbackAction, resultClassSerializer, url, keyValuesMap, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> AProxy<T> post(IObjectSerializer<T> resultClassSerializer, IObjectSerializer<Z> dataClassSerializer,
			ICallbackAction<T> callbackAction, String url, Z data) {
		return new PostProxy<T, Z>(callbackAction, resultClassSerializer, url, dataClassSerializer, data, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> AProxy<T> put( IObjectSerializer<T> resultClassSerializer, IObjectSerializer<Z> dataClassSerializer,
			ICallbackAction<T> callbackAction, String url, Z data) {
		return new PutProxy<T, Z>( callbackAction, resultClassSerializer, url, dataClassSerializer, data, getProvider());
	}

}
