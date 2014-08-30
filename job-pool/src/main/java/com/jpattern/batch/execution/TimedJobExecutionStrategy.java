package com.jpattern.batch.execution;

import com.jpattern.batch.JobExecutionStrategy;

/**
 * 
 * Execute a job a predetermined number of times consecutively
 * 
 * @author Francesco Cina'
 *
 * Apr 4, 2012
 */
public class TimedJobExecutionStrategy implements JobExecutionStrategy   {

	private static final long serialVersionUID = 1L;
	private final int maxExecutionTimes;
	private int executedTimes = 0;
	public static final int INFINITE = -1;

	public TimedJobExecutionStrategy(final int maxExecutionTimes) {
		this.maxExecutionTimes = maxExecutionTimes;
	}

	@Override
	public boolean canExecute() {

		if (this.hasOtherExecution()) {
			this.executedTimes++;
			return true;
		}

		return false;
	}

	@Override
	public long millisecondsBeforeNextExecution() {
		if (this.hasOtherExecution()) {
			return 0l;
		}
		return Long.MAX_VALUE;
	}

	@Override
	public String asString() {
		if (this.maxExecutionTimes == INFINITE) {
			return this.getClass().getSimpleName() + " executed " + this.executedTimes +  " times, remaining infinite executions";
		}
		final int remainig = (this.maxExecutionTimes - this.executedTimes) < 0 ? 0 : (this.maxExecutionTimes - this.executedTimes);
		return this.getClass().getSimpleName() + " executed " + this.executedTimes +  " times, remaining " + remainig;
	}

	@Override
	public boolean hasOtherExecution() {
		return (this.maxExecutionTimes == INFINITE) || (this.executedTimes < this.maxExecutionTimes);
	}
}
