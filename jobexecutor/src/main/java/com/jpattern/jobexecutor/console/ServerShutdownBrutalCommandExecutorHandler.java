package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.command.ServerShutDownBrutalCommand;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class ServerShutdownBrutalCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
	private boolean canCallSystemExit0;
	
	public ServerShutdownBrutalCommandExecutorHandler(IJobThreadPool jobThreadPool, boolean canCallSystemExit0) {
		this.jobThreadPool = jobThreadPool;
		this.canCallSystemExit0 = canCallSystemExit0;
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommand) {
		AThreadExecutorCommand command = new ServerShutDownBrutalCommand(jobThreadPool, canCallSystemExit0);
		stopCommand.setValue(true);
		
		wrappedResult.add( "================================" );
		wrappedResult.add( "Shutdown-Brute del server in esecuzione" );
		wrappedResult.add( "esco..." );
		wrappedResult.add( "================================" );
		
		command.exec();
		
		return new NullCommandExecutorHandler();		
	}

	public String getCommandDescription() {
		return "Spegne il server.";
	}

}
