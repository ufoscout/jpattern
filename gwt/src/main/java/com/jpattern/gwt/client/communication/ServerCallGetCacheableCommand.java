package com.jpattern.gwt.client.communication;

import java.util.Map;

import com.jpattern.gwt.client.cache.ICache;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandCallBack;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.util.GenericWrapper;
import com.jpattern.gwt.client.util.QueryString;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * This command uses a cache to reduce the number of calls to the server. Before perform the real get call, it checks if
 * a valid result for this get call is in a global cache (using the ICacheService), if yes the cached value is returned otherwise
 * the get call is performed and the result is stored in the cache.
 * 
 * @author Francesco Cina'
 *
 * 06/mag/2011
 */
public class ServerCallGetCacheableCommand<T extends ICommandFacadeResult<?>> extends ACommand {

	private Map<String, String> keyValuesMap;
	private StringBuffer url;
	private final GenericWrapper<T> callResult;
	private final String cacheName;
	
	public ServerCallGetCacheableCommand(Map<String, String> inKeyValuesMap, StringBuffer url, GenericWrapper<T> callResult, String cacheName) {
		this.cacheName = cacheName;
		this.url = url;
		this.keyValuesMap = inKeyValuesMap;
		this.callResult = callResult;
	}

	@Override
	protected void exec(final ICommandResult commandResult) {
		waitAsyncCallback();
		ILogger logger = getProvider().getLoggerService().getLogger(this.getClass());
		logger.debug("exec", "Start command execution");
		
		final ICache cache = getProvider().getCacheService().getCache(cacheName);
		final String completeUrl = url + QueryString.toQueryString(keyValuesMap);
		T cacheResult = cache.get(completeUrl, callResult.getWrappedClass());
		
		if ( cacheResult!=null ) {
			logger.debug("exec", "result found in cache [" + cacheName + "], 'get' call will not be performed");
			callResult.setValue(cacheResult);
			callback(commandResult);
		} else {		
			logger.debug("exec", "result not found in cache [" + cacheName + "], performing 'get' call");
			
			ACommand command = new ServerCallGetCommand<T>(keyValuesMap, url, callResult);
			
			ICommandCallBack commandCallBack = new ICommandCallBack() {
				@Override
				public void callback(ICommandResult callBackCommandResult) {
					if (callBackCommandResult.getErrorMessages().isEmpty()) {
						cache.put(completeUrl, callResult.getValue());
					} else {
						commandResult.getErrorMessages().addAll(callBackCommandResult.getErrorMessages());
					}
					ServerCallGetCacheableCommand.this.callback(commandResult);
				}
			};
			command.visit(getProvider());
			command.exec(commandCallBack);
		}
	}
}
