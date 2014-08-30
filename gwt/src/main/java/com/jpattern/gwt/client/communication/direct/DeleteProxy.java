package com.jpattern.gwt.client.communication.direct;

import java.util.Map;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.logger.ILogger;
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
	private final IServerCallDeleteAction serverCallAction;
	private final Class<T> resultClass;
	

	public DeleteProxy(IServerCallDeleteAction serverCallAction, ICallbackAction<T> callbackAction, Class<T> resultClass, String url, Map<String, String> keyValuesMap, IApplicationProvider provider) {
		super(callbackAction, provider);
		this.serverCallAction = serverCallAction;
		this.resultClass = resultClass;
		this.url=url;
		this.keyValuesMap = keyValuesMap;
	}
	
	@Override
	protected void execute(ICallbackAction<T> callbackAction, IApplicationProvider provider) throws Exception {
		ILogger logger = provider.getLoggerService().getLogger(this.getClass());

		logger.info("execute", "Begin execute - call URL " + url);
		try {
			callbackAction.onSuccess( serverCallAction.delete(resultClass, url, keyValuesMap) , 0 );
		} catch (Exception e) {
			callbackAction.onError(e , 0);
		}
	}
}
