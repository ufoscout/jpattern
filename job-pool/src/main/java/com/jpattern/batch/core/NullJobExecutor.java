package com.jpattern.batch.core;

import com.jpattern.batch.Job;
import com.jpattern.batch.JobExecutor;
import com.jpattern.batch.status.JobStatus;
import com.jpattern.batch.status.NullJobStatus;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class NullJobExecutor implements JobExecutor {

	@Override
	public void kill() {
	}

	@Override
	public JobStatus getJobStatus() {
		return new NullJobStatus();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public Job getJob() {
		return new NullJob();
	}

	@Override
	public boolean isValid() {
		return false;
	}

}
