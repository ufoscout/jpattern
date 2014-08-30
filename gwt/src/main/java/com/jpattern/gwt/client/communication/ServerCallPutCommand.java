package com.jpattern.gwt.client.communication;

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
public class ServerCallPutCommand<T extends ICommandFacadeResult<?>, Z> extends AServerCallCommand<T> {

	private final StringBuffer url;
	private final GenericWrapper<T> callResult;
	private final GenericWrapper<Z> dataInput;
	
	public ServerCallPutCommand(GenericWrapper<Z> dataInput, StringBuffer url, GenericWrapper<T> callResult) {
		this.url = url;
		this.callResult = callResult;
		this.dataInput = dataInput;
	}

	@Override
	protected void exec(ICommandResult commandResult) {
		waitAsyncCallback();
		ILogger logger = getProvider().getLoggerService().getLogger(this.getClass());
		logger.debug("exec", "Start command execution");
		ServerCommandCallBack commandCallBack = new ServerCommandCallBack(commandResult, callResult);
		IObjectSerializer<T> resultSerializer = getProvider().getSerializerService().getObjectSerializer(callResult.getWrappedClass());
		IObjectSerializer<Z> dataSerializer = getProvider().getSerializerService().getObjectSerializer(dataInput.getWrappedClass());
		AProxy<T> proxy = getProvider().getServerCallService().put(resultSerializer, dataSerializer, commandCallBack, url.toString(), dataInput.getValue());
		try {
			proxy.call();
		} catch (Exception exception) {
			commandResult.getErrorMessages().add(new ErrorMessage(IErrorMessages.SERVER_RESPONSE_READING_ERROR, exception.getMessage()));
			logger.error("exec", "Exception on Put Server call", exception);
		}
	}

}
