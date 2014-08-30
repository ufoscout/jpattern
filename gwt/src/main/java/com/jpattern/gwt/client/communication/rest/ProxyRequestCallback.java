package com.jpattern.gwt.client.communication.rest;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.serializer.IObjectSerializer;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class ProxyRequestCallback<T extends ICommandFacadeResult<?>> implements RequestCallback {

	private final ICallbackAction<T> callbackAction;
	private final IObjectSerializer<T> jsonBuilder;
	private final ILogger logger;

	public ProxyRequestCallback(ICallbackAction<T> callbackAction, IObjectSerializer<T> jsonBuilder, IApplicationProvider provider) {
		this.callbackAction = callbackAction;
		this.jsonBuilder = jsonBuilder;
		logger = provider.getLoggerService().getLogger(this.getClass());
	}
	
	@Override
	public void onError(Request request, Throwable exception) {
		logger.error("onError", "RequestCallback error", exception);
		callbackAction.onError(exception, 0);
	}

	@Override
	public void onResponseReceived(Request request, Response response) {
		int responseCode = response.getStatusCode();
		logger.debug("onResponseReceived", "RequestCallBack OK. StatusCode: [" + responseCode + "] StatusText: [" + response.getStatusText() + "]");
		logger.debug("onResponseReceived", "Code received:");
		logger.debug("onResponseReceived", response.getText());
		T result;
		try {
			result = jsonBuilder.deserialize( response.getText());
			if (result!=null) {
				callbackAction.onSuccess( result , responseCode ); 
			} else {
				callbackAction.onError(new Exception("Server call result is null") , responseCode);
			}
		} catch (Exception e) {
			callbackAction.onError(e , responseCode);
		}
	}
}
