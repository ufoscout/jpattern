package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.command.ServerShutDownCommand;

public class ServerShutdownCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
	private boolean canCallSystemExit0;
	
	public ServerShutdownCommandExecutorHandler(IJobThreadPool jobThreadPool, boolean canCallSystemExit0) {
		this.jobThreadPool = jobThreadPool;
		this.canCallSystemExit0 = canCallSystemExit0;
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommand) {
		AThreadExecutorCommand command = new ServerShutDownCommand(jobThreadPool, canCallSystemExit0);
		command.exec();
		
		wrappedResult.add( "================================" );
		wrappedResult.add( "Shutdown del server eseguito" );
		wrappedResult.add( "esco..." );
		wrappedResult.add( "================================" );
		
		stopCommand.setValue(true);
		return new NullCommandExecutorHandler();
	}

	public String getCommandDescription() {
		return "Spegne il server.";
	}

}
