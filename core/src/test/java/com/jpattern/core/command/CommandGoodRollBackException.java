package com.jpattern.core.command;

import java.util.Random;

import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 * 
 * This is a command which execute with problems
 */
public class CommandGoodRollBackException extends ACommand<IProvider> {

	private final SingleCommandResult singleCommandResult;
	
	public CommandGoodRollBackException(SingleCommandResult singleCommandResult) {
		this.singleCommandResult = singleCommandResult;
	}

	@Override
	protected void rollback(ACommandResult result) {
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		
		throw new RuntimeException("Mock exception");
	}

	@Override
	protected void execute(ACommandResult result) {
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		
		singleCommandResult.setExecuted(true);
		
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
	}

}
