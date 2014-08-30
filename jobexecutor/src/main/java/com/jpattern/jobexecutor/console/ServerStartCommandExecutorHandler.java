package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.command.ServerStartCommand;

public class ServerStartCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;

	public ServerStartCommandExecutorHandler(IJobThreadPool jobThreadPool) {
		this.jobThreadPool = jobThreadPool;
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommand) {
		AThreadExecutorCommand command = new ServerStartCommand(jobThreadPool);
		command.exec();
		stopCommand.setValue(true);
		
		wrappedResult.add( "================================" );
		wrappedResult.add( "Esecuzione del server avviata" );
		wrappedResult.add( "================================" );
		
		return new NullCommandExecutorHandler();
	}

	public String getCommandDescription() {
		return "Avvia il server";
	}

}
