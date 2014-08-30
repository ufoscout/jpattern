package com.jpattern.batch.status;

import java.util.Date;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class JobStatusImpl implements JobStatus {

	private static final long serialVersionUID = 1L;
	private final String jobName;
	private final String groupName;
	private JobStatusStrategy jobStatusStrategy = new RunningJobStatusStrategy(this);
	private boolean running = false;
	private boolean killed = false;
	private boolean paused = false;
	private long executionTimes = 0;
	private long executionInError = 0;
	private long meanExecutionTimeMillisec = 0;
	private Date lastExecutionStartDate = new Date(0);
	private Date lastExecutionEndDate = new Date(0);

	public JobStatusImpl(final String jobName, final String groupName) {
		this.jobName = jobName;
		this.groupName = groupName;
	}

	@Override
	public String getJobName() {
		return this.jobName;
	}

	@Override
	public String getGroupName() {
		return this.groupName;
	}

	@Override
	public boolean isRunning() {
		return this.running;
	}

	@Override
	public boolean isKilled() {
		return this.killed;
	}
	/**
	 * @param running the running to set
	 */
	public void setRunning(final boolean running) {
		this.running = running;
	}
	/**
	 * @param alive the alive to set
	 */
	public void kill() {
		this.jobStatusStrategy = new KilledJobStatusStrategy(this);
		this.killed = true;
	}

	@Override
	public boolean isPaused() {
		return this.paused;
	}

	public void pause() {
		this.jobStatusStrategy = new PausedJobStatusStrategy(this);
		this.paused = true;
	}

	public void resume() {
		this.jobStatusStrategy = new RunningJobStatusStrategy(this);
		this.paused = false;
	}

	@Override
	public StatusType getStatusType() {
		return this.jobStatusStrategy.getStatusType();
	}

	@Override
	public long getExecutionTimes() {
		return this.executionTimes;
	}

	@Override
	public long getExecutionInError() {
		return this.executionInError;
	}

	@Override
	public long getMeanExecutionTimeMillisec() {
		return this.meanExecutionTimeMillisec;
	}

	@Override
	public Date getLastExecutionStartDate() {
		return this.lastExecutionStartDate;
	}

	@Override
	public Date getLastExecutionEndDate() {
		return this.lastExecutionEndDate;
	}

	public void executionStarted(final Date startDate) {
		this.lastExecutionStartDate = startDate;
	}

	public void executionEnded(final Date endDate) {
		this.lastExecutionEndDate = endDate;
		this.executionTimes++;
		final long lastExecutionDuration = endDate.getTime() - this.lastExecutionStartDate.getTime();
		this.updateMeanExecutionTimeMillisec(lastExecutionDuration);
	}

	public void executionError(final Date endDate) {
		this.executionInError++;
		this.executionEnded(endDate);
	}

	protected void updateMeanExecutionTimeMillisec(final long lastExecutionDuration) {
		if (this.executionTimes==1) {
			this.meanExecutionTimeMillisec = lastExecutionDuration;
		} else {
			this.meanExecutionTimeMillisec = ( (this.meanExecutionTimeMillisec*(this.executionTimes-1)) + lastExecutionDuration) / this.executionTimes;
		}

	}
}
