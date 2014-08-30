package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IWrappedResult;

public class CloseCommunicationCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCommandDescription() {
		return "Esce dalla console di amministrazione";
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommand) {
		stopCommand.setValue(true);
		return new NullCommandExecutorHandler();
	}

}
