package com.jpattern.jobexecutor.core;

import com.jpattern.jobexecutor.IExecutableJob;
import com.jpattern.jobexecutor.IExecutableJobResult;
import com.jpattern.jobexecutor.IJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class NullExecutableJob implements IExecutableJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String executionAsString() {
		return "";
	}

	public String getJobName() {
		return "";
	}

	public boolean isAlive() {
		return false;
	}

	public void kill() {
	}

	public void resumeOriginalStrategy() {
	}

	public void setExecutionStrategy(IJobExecutionStrategy aJobExecution) {
	}

	public IExecutableJobResult call() throws Exception {
		return new NullExecutableJobResult();
	}

	public boolean isDaemon() {
		return false;
	}

	public void setDaemon(boolean isDaemon) {
	}

	public boolean isWorking() {
		return false;
	}

	public String isWorkingAsString() {
		return "The job is not currently working";
	}

}
