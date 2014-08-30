package com.jpattern.batch.execution;

import com.jpattern.batch.JobExecutionStrategy;

/**
 * 
 * A job with this strategy is never executed
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class NullJobExecutionStrategy implements JobExecutionStrategy {

	private static final long serialVersionUID = 1L;

	@Override
	public String asString() {
		return "Paused";
	}

	@Override
	public boolean canExecute() {
		return false;
	}

	@Override
	public long millisecondsBeforeNextExecution() {
		return Long.MAX_VALUE;
	}

	@Override
	public boolean hasOtherExecution() {
		return false;
	}

}
