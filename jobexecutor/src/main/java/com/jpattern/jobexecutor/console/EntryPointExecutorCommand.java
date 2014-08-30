package com.jpattern.jobexecutor.console;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;

public class EntryPointExecutorCommand implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, ICommandExecutorHandler> commandMap = new HashMap<String, ICommandExecutorHandler>();

	public EntryPointExecutorCommand(IJobThreadPool jobThreadPool) {
		commandMap.put( ICommandExecutorHandler.COMMAND_SHUTDOWN_BRUTAL, new ServerShutdownBrutalCommandExecutorHandler(jobThreadPool, AThreadExecutorCommand.FORCE_SHUTDOWN_WITH_SYSTEMEXIT0));
		commandMap.put( ICommandExecutorHandler.COMMAND_IDENTITY_APPLICATION, new IdentifyApplicationCommandExecutorHandler(this, jobThreadPool));
		commandMap.put( ICommandExecutorHandler.COMMAND_SHUTDOWN, new ServerShutdownCommandExecutorHandler(jobThreadPool, AThreadExecutorCommand.FORCE_SHUTDOWN_WITH_SYSTEMEXIT0));
		commandMap.put( ICommandExecutorHandler.COMMAND_START, new ServerStartCommandExecutorHandler(jobThreadPool));
		commandMap.put( ICommandExecutorHandler.COMMAND_STOP,  new ServerStopCommandExecutorHandler(jobThreadPool));
		commandMap.put( ICommandExecutorHandler.COMMAND_CLOSE_CONSOLE, new CloseCommunicationCommandExecutorHandler());
		commandMap.put( ICommandExecutorHandler.COMMAND_CONSOLE_MANAGER, new ConsoleManagerExecutorCommand(jobThreadPool));
	}

	public String getCommandDescription() {
		return "Entry point of administration tool";
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommandExecution) {
		if (commandMap.containsKey(commandLine)) {
			return commandMap.get(commandLine).exec(commandLine, wrappedResult, stopCommandExecution);
		}
		return new UnknownCommandExecutorHandler();
	}

}
