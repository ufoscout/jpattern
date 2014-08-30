package com.jpattern.gwt.client.communication.rest;

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
public class PostProxy<T extends ICommandFacadeResult<?>,Z> extends AProxy<T> {

	private final String url;
	private final Z data;
	private final IObjectSerializer<Z> sendJsonBuilder;
	private final IObjectSerializer<T> resultJsonBuilder;

	public PostProxy(ICallbackAction<T> callbackAction, IObjectSerializer<T> resultJsonBuilder, String url, IObjectSerializer<Z> sendJsonBuilder, Z data, IApplicationProvider provider) {
		super(callbackAction, provider);
		this.resultJsonBuilder = resultJsonBuilder;
		this.url=url;
		this.sendJsonBuilder = sendJsonBuilder;
		this.data = data;
	}
	
	@Override
	protected void execute(ICallbackAction<T> callbackAction, IApplicationProvider provider) throws Exception {
		ILogger logger = provider.getLoggerService().getLogger(this.getClass());
		
		logger.info("execute", "Begin execute - call URL " + url);
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, QueryString.addTimestampQueryToUrl(url));
		builder.setHeader("Content-type", "application/json");
		
		String json = sendJsonBuilder.serialize(data);
		logger.debug("execute", "Json object to send to server: [" + json + "]");
		ProxyRequestCallback<T> requestCallBack = new ProxyRequestCallback<T>(callbackAction, resultJsonBuilder, provider);
		builder.sendRequest(json, requestCallBack);
		logger.debug("execute", "End execute - call URL " + url);
	}

}
