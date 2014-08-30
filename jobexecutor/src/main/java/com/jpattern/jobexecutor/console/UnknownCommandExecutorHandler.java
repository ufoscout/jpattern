package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IWrappedResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 05/apr/2010
 */
public class UnknownCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCommandDescription() {
		return "Unknown command received from client";
	}

	public String getCommandName() {
		return ICommandExecutorHandler.COMMAND_UNKNOWN;
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommandExecution) {
		stopCommandExecution.setValue(true);
		wrappedResult.add( getCommandDescription() + ": " + commandLine);
		
		return new NullCommandExecutorHandler();
	}

}
