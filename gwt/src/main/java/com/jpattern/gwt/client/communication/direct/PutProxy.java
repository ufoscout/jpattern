package com.jpattern.gwt.client.communication.direct;

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
public class PutProxy<T extends ICommandFacadeResult<?>,Z> extends AProxy<T> {

	private final String url;
	private final Z data;
	private final IServerCallPutAction serverCallPostAction;
	private final Class<T> resultClass;
	private final Class<Z> dataClass;

	public PutProxy(IServerCallPutAction serverCallPostAction, Class<T> resultClass, Class<Z> dataClass, ICallbackAction<T> callbackAction, String url, Z data, IApplicationProvider provider) {
		super(callbackAction, provider);
		this.serverCallPostAction = serverCallPostAction;
		this.resultClass = resultClass;
		this.dataClass = dataClass;
		this.url=url;
		this.data = data;
	}
	
	@Override
	protected void execute(ICallbackAction<T> callbackAction, IApplicationProvider provider) throws Exception {
		ILogger logger = provider.getLoggerService().getLogger(this.getClass());
		
		logger.info("execute", "Begin execute - call URL " + url);

		try {
			callbackAction.onSuccess( serverCallPostAction.put(resultClass, dataClass, url, data), 0);
		} catch (Exception e) {
			callbackAction.onError(e ,0);
		}
	}

}
