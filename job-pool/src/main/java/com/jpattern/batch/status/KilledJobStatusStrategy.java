package com.jpattern.batch.status;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class KilledJobStatusStrategy implements JobStatusStrategy {

	private final JobStatus jobStatus;

	public KilledJobStatusStrategy(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Override
	public StatusType getStatusType() {
		if (jobStatus.isRunning()) {
			return StatusType.KILLED_AFTER_EXECUTION;
		}
		return StatusType.KILLED;
	}

}
