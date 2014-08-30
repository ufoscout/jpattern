package com.jpattern.batch;

/**
 * 
 * @author cinafr
 *
 */
public interface JobTrigger {

	void killTrigger();

	boolean isAlive();

	JobExecutor getJobExecutor();

}
