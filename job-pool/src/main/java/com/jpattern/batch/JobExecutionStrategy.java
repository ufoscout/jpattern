package com.jpattern.batch;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 4, 2012
 */
public interface JobExecutionStrategy extends Serializable  {

	/**
	 * return true if 'now' verify the conditions to execute the job
	 * @return
	 */
	boolean canExecute();

	/**
	 * return the amount of milliseconds to wait before the next execution
	 * @return
	 */
	long millisecondsBeforeNextExecution();

	/**
	 * true if the job have to be executed again
	 * @return
	 */
	boolean hasOtherExecution();

	String asString();
}
