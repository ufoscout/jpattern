package com.jpattern.gwt.client.command;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.IErrorMessages;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public abstract class ACommand {

	private IApplicationProvider provider;
	private ICommandCallBack commandCallBack;
	private boolean waitAsyncCallback = false;

	public final void exec(ICommandCallBack commandCallBack) {
		waitAsyncCallback = false;
		this.commandCallBack = commandCallBack;
		ILogger logger = getProvider().getLoggerService().getLogger(this.getClass());
		logger.debug("exec", "start execution");
		ICommandResult commandResult = new CommandResult();
		try{
			exec(commandResult);
			if (!waitAsyncCallback) {
				doCallback(commandResult);
			}
		} catch (Exception e) {
			commandResult.getErrorMessages().add(new ErrorMessage(IErrorMessages.COMMAND_GENERIC_ERROR, e.getMessage()));
			logger.error("exec", "ACommand.generic.error", e);
			doCallback(commandResult);
		}
	}
	
	protected final void waitAsyncCallback() {
		waitAsyncCallback  = true;
	}
	
	protected void callback(ICommandResult commandResult) {
		if (waitAsyncCallback) {
			doCallback(commandResult);
		}
	}
	
	private final void doCallback(ICommandResult commandResult) {
		getCommandCallBack().callback(commandResult);
	}
	
	protected final IApplicationProvider getProvider() {
		if (provider == null) {
			visit( new NullApplicationProvider());
		}		
		return provider;
	}
	
	public final void visit(IApplicationProvider provider) {
		this.provider = provider;
	}
	
	protected abstract void exec(ICommandResult commandResult);

	protected final ICommandCallBack getCommandCallBack() {
		if (commandCallBack==null) {
			commandCallBack = new NullCommandCallBack();
		}
		getProvider().getLoggerService().getLogger(this.getClass()).debug("getCommandCallBack", "getCommandCallback " + commandCallBack);
		return commandCallBack;
	}
}
