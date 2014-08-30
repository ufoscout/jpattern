package com.jpattern.gwt.client.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * A Chain of commands. The commands are executed in a asynchronous way. This means that if a command perform asynchronous
 * action then to execution of the commands in the chain go further without wait the end of the asynchronous action.
 * The command chain however ends only after the ends of all the commands in the chain itself no matter if synchronous or
 * asynchronous.
 * This chain is indicated to perform more call to the server in the same time in cases when the result of the n-1 commands
 * in the chain is not needed by the 'n' command.
 * the entire chain is executed even if errors happen in some command execution. The final errors list will contains all the errors arisen in the commands execution.
 * 
 * @author Francesco Cina'
 *
 * 07/set/2011
 */
public class CommandChainAsynch extends ACommand {

	
	List<ACommand> commands = new ArrayList<ACommand>();
	private Iterator<ACommand> commandIterator;
	private ICommandResult globalResult;
	private int callBackPerformed = 0;
	
	@Override
	public void exec(ICommandResult commandResult) {
		waitAsyncCallback();
		globalResult = commandResult;
		commandIterator = commands.iterator();
		
		if (commands.size()>0) {
			while(commandIterator.hasNext()) {
				ACommand nextCommand = commandIterator.next();
				nextCommand.visit(getProvider());
				nextCommand.exec(new CommandChainCallBack());
			}
		} else {
			callback(commandResult);
		}
	}
	
	protected void callback(ICommandResult commandResult) {
		globalResult.getErrorMessages().addAll(commandResult.getErrorMessages());
		if (commands.size()==callBackPerformed) {
			getCommandCallBack().callback(globalResult);
		}
	}
	
	public void addCommand(ACommand command) {
		commands.add(command);
	}
	
	
	class CommandChainCallBack implements ICommandCallBack {

		
		CommandChainCallBack() {
		}
		
		@Override
		public void callback(ICommandResult commandResult) {
			callBackPerformed++;
			CommandChainAsynch.this.callback(commandResult);
		}

	}
}
