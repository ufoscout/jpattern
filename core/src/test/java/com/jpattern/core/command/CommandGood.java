package com.jpattern.core.command;

import java.util.Random;

import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 * 
 * This is a command which execute without problem
 */
public class CommandGood extends ACommand<IProvider> {

	private static Integer COUNT = 0;
	private final int number;
	private SingleCommandResult singleCommandResult;
	public static int RANDOM_SLEEP = 10;
	
	public CommandGood(SingleCommandResult singleCommandResult) {
		this.singleCommandResult = singleCommandResult;
		synchronized (COUNT) {
			number = COUNT++;
		}
	}

	@Override
	protected void rollback(ACommandResult result) {
		System.out.println("start rollback - " + number + " - " + this);
		try {
			Thread.sleep(new Random().nextInt(RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		singleCommandResult.setRollbackExecuted(true);
		try {
			Thread.sleep(new Random().nextInt(RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		System.out.println("end rollback - " + number + " - " + this);
	}

	@Override
	protected void execute(ACommandResult result) {
		System.out.println("start execution - " + number + " - " + this);
		try {
			Thread.sleep(new Random().nextInt(RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		singleCommandResult.setExecuted(true);
		try {
			Thread.sleep(new Random().nextInt(RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		System.out.println("end execution - " + number + " - " + this);
	}

}
