package com.jpattern.core.command;

import java.util.Random;

import com.jpattern.core.IProvider;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 * 
 * This is a command which execute with problems
 */
public class CommandBad extends ACommand<IProvider> {

	private static Integer COUNT = 0;
	private final int number;
	private int howManyProblems = 1;
	private final SingleCommandResult singleCommandResult;
	
	public CommandBad(int howManyProblems, SingleCommandResult singleCommandResult) {
		this.singleCommandResult = singleCommandResult;
		if (howManyProblems>0) {
			this.howManyProblems = howManyProblems;
		}
		synchronized (COUNT) {
			number = COUNT++;
		}
	}

	@Override
	protected void rollback(ACommandResult result) {
		System.out.println("start rollback - " + number + " - " + this);
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		
		singleCommandResult.setRollbackExecuted(true);
		
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		
		System.out.println("end rollback - " + number + " - " + this);

	}

	@Override
	protected void execute(ACommandResult result) {
		System.out.println("start execute - " + number + " - " + this);

		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		
		singleCommandResult.setExecuted(true);

		for (int i=0; i<howManyProblems; i++) {
			String aPropertyName = "property-" + i;
			String aPropertyMessage = "message-" + i;
			String[] aParameters = new String[i];
			for (int j=0; j<i; j++) {
				aParameters[j] = "parameters-" + i + "-" + j;
			}
			IErrorMessage aMessageInvalidate = new ErrorMessage(aPropertyName, aPropertyMessage, aParameters  );
			result.addErrorMessage(aMessageInvalidate );
		}
		
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		
		System.out.println("end execute - " + number + " - " + this);
	}

}
