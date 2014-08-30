package com.jpattern.gwt.client.communication;

import java.util.Map;

import com.jpattern.gwt.client.IErrorMessages;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.serializer.IObjectSerializer;
import com.jpattern.gwt.client.util.GenericWrapper;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/mag/2011
 */
public class ServerCallDeleteCommand<T extends ICommandFacadeResult<?>> extends AServerCallCommand<T> {

	private final Map<String, String> keyValuesMap;
	private final StringBuffer url;
	private final GenericWrapper<T> callResult;
	
	public ServerCallDeleteCommand(Map<String, String> keyValuesMap, StringBuffer url, GenericWrapper<T> callResult) {
		this.url = url;
		this.keyValuesMap = keyValuesMap;
		this.callResult = callResult;
	}

	@Override
	protected void exec(ICommandResult commandResult) {
		waitAsyncCallback();
		ILogger logger = getProvider().getLoggerService().getLogger(this.getClass());
		logger.debug("exec", "Start command execution");
		ServerCommandCallBack commandCallBack = new ServerCommandCallBack(commandResult, callResult);
		IObjectSerializer<T> resultSerializer = getProvider().getSerializerService().getObjectSerializer(callResult.getWrappedClass());
		AProxy<T> proxy = getProvider().getServerCallService().delete(resultSerializer, commandCallBack, url.toString(), keyValuesMap);	
		try {
			proxy.call();
		} catch (Exception exception) {
			commandResult.getErrorMessages().add(new ErrorMessage(IErrorMessages.SERVER_RESPONSE_READING_ERROR, exception.getMessage()));
			logger.error("exec", "Exception on Delete Server call", exception);
		}
	}

}
