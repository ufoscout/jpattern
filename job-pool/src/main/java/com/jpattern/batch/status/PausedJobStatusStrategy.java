package com.jpattern.batch.status;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class PausedJobStatusStrategy implements JobStatusStrategy {

	private final JobStatus jobStatus;

	public PausedJobStatusStrategy(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Override
	public StatusType getStatusType() {
		if (jobStatus.isRunning()) {
			return StatusType.PAUSED_AFTER_EXECUTION;
		}
		return StatusType.PAUSED;
	}

}
