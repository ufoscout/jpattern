package com.jpattern.batch.core;

import com.jpattern.batch.JobExecutor;
import com.jpattern.batch.JobTrigger;

/**
 * 
 * @author cinafr
 *
 */
public class NullJobTrigger implements JobTrigger {

	@Override
	public void killTrigger() {
	}

	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public JobExecutor getJobExecutor() {
		return new NullJobExecutor();
	}

}
