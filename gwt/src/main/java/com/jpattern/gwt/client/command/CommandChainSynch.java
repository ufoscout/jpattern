package com.jpattern.gwt.client.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * A Chain of commands. The commands are executed one after the other in a similar synchronous way even
 * if some specific commands in the chain perform asynchronous call to the server.
 * The command in position 'i' in the chain is executed only after the execution of the 'i-1' previous commands.
 * If the command 'i' returns error during the execution, then the following commands are not executed and the 
 * execution of the chain is blocked.
 * 
 * @author Francesco Cina'
 *
 * 07/set/2011
 */
public class CommandChainSynch extends ACommand {

	List<ACommand> commands = new ArrayList<ACommand>();
	private Iterator<ACommand> commandIterator;
	
	@Override
	public void exec(ICommandResult commandResult) {
		commandIterator = commands.iterator();
		callback(commandResult);
	}
	
	protected void callback(ICommandResult commandResult) {
		waitAsyncCallback();
		if (commandIterator.hasNext() && commandResult.getErrorMessages().isEmpty()) {
			ACommand nextCommand = commandIterator.next();
			nextCommand.visit(getProvider());
			nextCommand.exec(new CommandChainCallBack());
		} else {
			getCommandCallBack().callback(commandResult);
		}
	}
	
	public void addCommand(ACommand command) {
		commands.add(command);
	}
	
	
	class CommandChainCallBack implements ICommandCallBack {
		
		@Override
		public void callback(ICommandResult commandResult) {
			CommandChainSynch.this.callback(commandResult);
		}

	}
}
