package com.jpattern.core.command;

import java.util.List;

import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public class MockDoNothingAsyncCommand extends ACommand<IProvider> {

	private final int doNothingForMilliseconds;
	private final String name;
	private final List<String> executions;
	private final StringBuffer buffer;
	private final int position;

	public MockDoNothingAsyncCommand(List<String> executions, String name, int doNothingForMilliseconds, StringBuffer buffer, int position) {
		this.executions = executions;
		this.name = name;
		this.doNothingForMilliseconds = doNothingForMilliseconds;
		this.buffer = buffer;
		this.position = position;
	}

	@Override
	protected void execute(ACommandResult result) {
		System.out.println(name + ": --- MockDoNothingAsyncCommand Execution Start. Do nothing for " + doNothingForMilliseconds + " milliseconds ---");
		try {
			Thread.sleep(doNothingForMilliseconds);
			executions.add("Executed");
			buffer.append(position + "_");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		System.out.println(name + ": --- MockDoNothingAsyncCommand Execution Ended ---");
	}

	@Override
	protected void rollback(ACommandResult rollBackResult) {
		System.out.println(name + ": --- MockDoNothingAsyncCommand Rollback Start. Do nothing for " + doNothingForMilliseconds + " milliseconds ---");
		try {
			Thread.sleep(doNothingForMilliseconds);
			buffer.append(position + "_rollback_");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		System.out.println(name + ": --- MockDoNothingAsyncCommand Rollback Ended ---");		
	}


}
