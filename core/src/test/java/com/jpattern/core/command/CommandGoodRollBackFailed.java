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
public class CommandGoodRollBackFailed extends ACommand<IProvider> {

	private final SingleCommandResult singleCommandResult;
	private int howManyRollbackProblems;
	
	public CommandGoodRollBackFailed(int howManyRollbackProblems, SingleCommandResult singleCommandResult) {
		this.singleCommandResult = singleCommandResult;
		if (howManyRollbackProblems>0) {
			this.howManyRollbackProblems = howManyRollbackProblems;
		}
	}

	@Override
	protected void rollback(ACommandResult result) {
		try {
			Thread.sleep(new Random().nextInt(CommandGood.RANDOM_SLEEP ));
		} catch (InterruptedException e) {}
		
		singleCommandResult.setRollbackExecuted(true);
		for (int i=0; i<howManyRollbackProblems; i++) {
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
