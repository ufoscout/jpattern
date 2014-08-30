package com.jpattern.jobexecutor.core;

import com.jpattern.jobexecutor.IJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class NullJobExecutionStrategy implements IJobExecutionStrategy {

	private static final long serialVersionUID = 1L;

	public String asString() {
		return "Paused";
	}

	public boolean canExecute() {
		return false;
	}

}
