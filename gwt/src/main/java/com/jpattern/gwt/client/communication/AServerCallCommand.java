package com.jpattern.gwt.client.communication;

import com.jpattern.gwt.client.IErrorMessages;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.util.GenericWrapper;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/mag/2011
 */
public abstract class AServerCallCommand<T extends ICommandFacadeResult<?>> extends ACommand {

	
	
	final class ServerCommandCallBack implements ICallbackAction<T> {

		private final ICommandResult commandResult;
		private final GenericWrapper<T> callResult;
		private final ILogger logger;

		public ServerCommandCallBack(ICommandResult commandResult, GenericWrapper<T> callResult) {
			this.logger = getProvider().getLoggerService().getLogger(this.getClass());
			this.commandResult = commandResult;
			this.callResult = callResult;
		}

		@Override
		public void onSuccess(T result, int responseCode) {
			logger.debug("onSuccess", "onSuccess ServerCallCommand callback called: " + result.getReturnedObject());
			callResult.setValue(result);
			commandResult.setResponseCode(responseCode);
			if (!result.getErrorMessages().isEmpty()) {
				commandResult.getErrorMessages().addAll(result.getErrorMessages());
			}
			callback(commandResult);
		}

		@Override
		public void onError(Throwable exception, int responseCode) {
			commandResult.getErrorMessages().add(new ErrorMessage(IErrorMessages.SERVER_COMMUNICATION_ERROR, exception.getMessage()));
			commandResult.setResponseCode(responseCode);
			logger.error("onError", "Exception on ServerCallCommand callbak", exception);
			callback(commandResult);
		}
		
	}
}
