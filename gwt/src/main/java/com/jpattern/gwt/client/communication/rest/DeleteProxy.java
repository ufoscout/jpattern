package com.jpattern.gwt.client.communication.rest;

import java.util.Map;

import com.google.gwt.http.client.RequestBuilder;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.serializer.IObjectSerializer;
import com.jpattern.gwt.client.util.QueryString;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * @param <T>
 */
public class DeleteProxy<T extends ICommandFacadeResult<?>> extends AProxy<T> {

	private Map<String, String> keyValuesMap;
	private String url;
	private final IObjectSerializer<T> jsonBuilder;
	

	public DeleteProxy(ICallbackAction<T> callbackAction, IObjectSerializer<T> jsonBuilder, String url, Map<String, String> keyValuesMap, IApplicationProvider provider) {
		super(callbackAction, provider);
		this.jsonBuilder = jsonBuilder;
		this.url=url;
		this.keyValuesMap = keyValuesMap;
		
	}
	
	@Override
	protected void execute(ICallbackAction<T> callbackAction, IApplicationProvider provider) throws Exception {
		ILogger logger = provider.getLoggerService().getLogger(this.getClass());
		url += QueryString.toQueryString(keyValuesMap);

		logger.info("execute", "Begin execute - call URL " + url);
		
		// Send request to server and catch any errors.
		RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, QueryString.addTimestampQueryToUrl(url));
		ProxyRequestCallback<T> requestCallBack = new ProxyRequestCallback<T>( callbackAction, jsonBuilder, provider);

		builder.sendRequest(null, requestCallBack);
		logger.debug("execute", "End execute - call URL " + url);
	}
}
