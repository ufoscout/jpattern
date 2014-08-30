package com.jpattern.gwt.client.communication.direct;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.gwt.client.AServerCallService;
import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.communication.NullProxy;
import com.jpattern.gwt.client.serializer.IObjectSerializer;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class DirectServerCallService extends AServerCallService {

	Map<String, IServerCallPostAction> postMap = new HashMap<String, IServerCallPostAction>();
	Map<String, IServerCallGetAction> getMap = new HashMap<String, IServerCallGetAction>();
	Map<String, IServerCallPutAction> putMap = new HashMap<String, IServerCallPutAction>();
	Map<String, IServerCallDeleteAction> deleteMap = new HashMap<String, IServerCallDeleteAction>();
	
	@Override
	public <T extends ICommandFacadeResult<?>> AProxy<T> get(IObjectSerializer<T> resultClassSerializer, ICallbackAction<T> callbackAction, String url, Map<String, String> keyValuesMap) {
		String urlWithoutQueryString = removeQueryString(url);
		String bestKey = getBestKey(urlWithoutQueryString, getMap);
		if (getMap.containsKey(bestKey)) {
			return new GetProxy<T>(getMap.get(bestKey), callbackAction, resultClassSerializer.getSerializerClass(), urlWithoutQueryString, keyValuesMap, getProvider());
		}
		return new NullProxy<T>(callbackAction, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>> AProxy<T> delete(IObjectSerializer<T> resultClassSerializer, ICallbackAction<T> callbackAction, String url, Map<String, String> keyValuesMap) {
		String urlWithoutQueryString = removeQueryString(url);
		String bestKey = getBestKey(urlWithoutQueryString, deleteMap);
		if (deleteMap.containsKey(bestKey)) {
			return new DeleteProxy<T>(deleteMap.get(bestKey), callbackAction, resultClassSerializer.getSerializerClass(), urlWithoutQueryString, keyValuesMap, getProvider());
		}
		return new NullProxy<T>(callbackAction, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> AProxy<T> post(IObjectSerializer<T> resultClassSerializer, IObjectSerializer<Z> dataClassSerializer, ICallbackAction<T> callbackAction, String url, Z data) {
		String urlWithoutQueryString = removeQueryString(url);
		String bestKey = getBestKey(urlWithoutQueryString, postMap);
		if (postMap.containsKey(bestKey)) {
			return new PostProxy<T, Z>(postMap.get(bestKey), resultClassSerializer.getSerializerClass(), dataClassSerializer.getSerializerClass(), callbackAction, urlWithoutQueryString, data, getProvider());
		}
		return new NullProxy<T>(callbackAction, getProvider());
	}

	@Override
	public <T extends ICommandFacadeResult<?>, Z> AProxy<T> put(IObjectSerializer<T> resultClassSerializer, IObjectSerializer<Z> dataClassSerializer, ICallbackAction<T> callbackAction, String url, Z data) {
		String urlWithoutQueryString = removeQueryString(url);
		String bestKey = getBestKey(urlWithoutQueryString, putMap);
		if (putMap.containsKey(bestKey)) {
			return new PutProxy<T, Z>(putMap.get(bestKey), resultClassSerializer.getSerializerClass(), dataClassSerializer.getSerializerClass(), callbackAction, urlWithoutQueryString, data, getProvider());
		}
		return new NullProxy<T>(callbackAction, getProvider());
	}

	public void addPost(String path, IServerCallPostAction serverCallAction) {
		postMap.put(path, serverCallAction);
	}
	
	public void addPut(String path, IServerCallPutAction serverCallAction) {
		putMap.put(path, serverCallAction);
	}
	
	public void addGet(String path, IServerCallGetAction serverCallAction) {
		getMap.put(path, serverCallAction);
	}
	
	public void addDelete(String path, IServerCallDeleteAction serverCallAction) {
		deleteMap.put(path, serverCallAction);
	}
	
	protected String getBestKey(String url, Map<String, ?> map) {
		String temp = url;
		while (!temp.isEmpty()) {
			if ( map.containsKey(temp) ) {
				return temp;
			}
			int index = temp.lastIndexOf("/");
			if (index>=0) {
				temp = temp.substring(0, index);
			}
		}
		return temp;
	}
	
	private String removeQueryString(String url) {
		int questionMarkIndex = url.lastIndexOf("?");
		if (questionMarkIndex>0) {
			return url.substring(0, questionMarkIndex);
		}
		return url;
	}
}
