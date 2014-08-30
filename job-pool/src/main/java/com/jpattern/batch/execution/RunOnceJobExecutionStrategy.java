package com.jpattern.batch.execution;

import com.jpattern.batch.JobExecutionStrategy;

/**
 * 
 * Execute a job only once
 * 
 * @author Francesco Cina'
 *
 * 04/giu/2010
 */
public class RunOnceJobExecutionStrategy implements JobExecutionStrategy   {

	private static final long serialVersionUID = 1L;
	private int executedTimes = 0;

	@Override
	public boolean canExecute() {

		if (this.hasOtherExecution()) {
			//			int hello;
			//			System.out.println("----------------------------------");
			//			System.out.println("can execute TRUE!!");
			//			System.out.println("----------------------------------");
			this.executedTimes++;
			return true;
		}
		//		int hello;
		//		System.out.println("----------------------------------");
		//		System.out.println("can execute FALSE!!");
		//		System.out.println("----------------------------------");
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
		final int remainig = (this.hasOtherExecution()) ? 1 : 0;
		return this.getClass().getSimpleName() + ", remaining " + remainig + " executions";
	}

	@Override
	public boolean hasOtherExecution() {
		return this.executedTimes == 0;
	}
}
