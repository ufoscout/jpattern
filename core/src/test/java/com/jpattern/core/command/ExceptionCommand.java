package com.jpattern.core.command;

import java.util.Random;

import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/apr/2011
 */
public class ExceptionCommand extends ACommand<IProvider> {

	private final SingleCommandResult singleCommandResult;
	private final boolean exceptionRollback;
	private final boolean exceptionExec;

	public ExceptionCommand(SingleCommandResult singleCommandResult, boolean exceptionExec, boolean exceptionRollback) {
		this.singleCommandResult = singleCommandResult;
		this.exceptionExec = exceptionExec;
		this.exceptionRollback = exceptionRollback;
	}

	@Override
	protected void execute(ACommandResult result) {
		try {
			Thread.sleep(new Random().nextInt( CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		singleCommandResult.setExecuted(true);
		if (exceptionExec) {
			System.out.println("--- ExceptionCommand - Exec - Throwing NullPointerException() ---");
			throw new NullPointerException("Exception thrown for test purpose");
		}
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
	}

	@Override
	protected void rollback(ACommandResult result) {
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		singleCommandResult.setRollbackExecuted(true);
		if (exceptionRollback) {
			System.out.println("--- ExceptionCommand - Rollback - Throwing NullPointerException() ---");
			throw new NullPointerException();
		}
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
	}


}
