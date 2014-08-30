package com.jpattern.batch.status;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public interface JobStatus extends Serializable {

	/**
	 * the name of the job
	 * @return
	 */
	String getJobName();

	/**
	 * the group of the job
	 * @return
	 */
	String getGroupName();

	/**
	 * the current status
	 * @return
	 */
	StatusType getStatusType();

	/**
	 * Return how many times the job was executed
	 * @return
	 */
	long getExecutionTimes();

	/**
	 * Return the number of executions in error
	 * @return
	 */
	long getExecutionInError();

	/**
	 * Return the mean duration time of the job execution
	 * @return
	 */
	long getMeanExecutionTimeMillisec();

	/**
	 * Return the last start date of the job's execution
	 * @return
	 */
	Date getLastExecutionStartDate();

	/**
	 * Return the last end date of the job's execution
	 * @return
	 */
	Date getLastExecutionEndDate();

	/**
	 * Return whether the job is currently running
	 * @return
	 */
	boolean isRunning();

	/**
	 * Return whether the job is currently paused
	 * @return
	 */
	boolean isPaused();

	/**
	 * Return whether the job is killed
	 * @return
	 */
	boolean isKilled();
}
