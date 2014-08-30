package com.jpattern.batch;

import com.jpattern.batch.status.JobStatus;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 4, 2012
 */
public interface JobExecutor {

	JobStatus getJobStatus();

	Job getJob();

	boolean execute();

	void pause();

	void resume();

	void kill();

	boolean isValid();
}
