package com.jpattern.batch.status;

import java.util.Date;

/**
 * 
 * @author cinafr
 *
 */
public class NullJobStatus implements JobStatus {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJobName() {
		return "";
	}

	@Override
	public String getGroupName() {
		return "";
	}

	@Override
	public StatusType getStatusType() {
		return StatusType.KILLED;
	}

	@Override
	public long getExecutionTimes() {
		return 0;
	}

	@Override
	public long getExecutionInError() {
		return 0;
	}

	@Override
	public long getMeanExecutionTimeMillisec() {
		return 0;
	}

	@Override
	public Date getLastExecutionStartDate() {
		return new Date(0);
	}

	@Override
	public Date getLastExecutionEndDate() {
		return new Date(0);
	}

	@Override
	public boolean isRunning() {
		return false;
	}

	@Override
	public boolean isPaused() {
		return false;
	}

	@Override
	public boolean isKilled() {
		return true;
	}

}
