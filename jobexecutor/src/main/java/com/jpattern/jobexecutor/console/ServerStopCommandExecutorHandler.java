package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.command.ServerStopCommand;

public class ServerStopCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;

	public ServerStopCommandExecutorHandler(IJobThreadPool jobThreadPool) {
		this.jobThreadPool = jobThreadPool;
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommand) {
		AThreadExecutorCommand command = new ServerStopCommand(jobThreadPool);
		command.exec();
		stopCommand.setValue(true);
		
		wrappedResult.add( "================================" );
		wrappedResult.add( "Esecuzione del server fermata" );
		wrappedResult.add( "================================" );
		
		return new NullCommandExecutorHandler();
	}

	public String getCommandDescription() {
		return "Ferma l'esecuzione dei job";
	}

}
