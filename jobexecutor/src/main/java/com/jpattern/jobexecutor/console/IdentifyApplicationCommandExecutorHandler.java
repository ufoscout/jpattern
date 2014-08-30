package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.command.IdentifyApplicationCommand;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/apr/2010
 */
public class IdentifyApplicationCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
	private ICommandExecutorHandler mainCommand;

	public IdentifyApplicationCommandExecutorHandler(ICommandExecutorHandler mainCommand, IJobThreadPool jobThreadPool) {
		this.jobThreadPool = jobThreadPool;
		this.mainCommand = mainCommand;
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommand) {
		StringBuffer result = new StringBuffer();
		AThreadExecutorCommand command = new IdentifyApplicationCommand(jobThreadPool, result);
		command.exec();
		
		wrappedResult.add( result.toString() );
		return mainCommand;
		
	}

	public String getCommandDescription() {
		return "Ritorna il nome del jobThreadPool";
	}

	public String getCommandName() {
		return ICommandExecutorHandler.COMMAND_IDENTITY_APPLICATION;
	}

}
