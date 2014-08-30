package com.jpattern.batch.status;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class RunningJobStatusStrategy implements JobStatusStrategy {

	private final JobStatus jobStatus;

	public RunningJobStatusStrategy(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	@Override
	public StatusType getStatusType() {
		if (jobStatus.isRunning()) {
			return StatusType.RUNNING;
		}
		return StatusType.WAITING;
	}

}
