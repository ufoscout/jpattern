package com.jpattern.batch.core;

import java.util.Date;

import com.jpattern.batch.Job;
import com.jpattern.batch.JobExecutor;
import com.jpattern.batch.logger.JobPoolLogger;
import com.jpattern.batch.status.JobStatus;
import com.jpattern.batch.status.JobStatusImpl;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class JobExecutorImpl implements JobExecutor {

	private final Job job;
	private final JobStatusImpl jobStatus;
	private final ILogger logger = JobPoolLogger.getLogger(this.getClass());

	public JobExecutorImpl(final Job job) {
		this.job = job;
		this.jobStatus = new JobStatusImpl(job.getName(), job.getGroup());
	}

	@Override
	public void kill() {
		synchronized (this.jobStatus) {
			if (!this.jobStatus.isKilled()) {
				this.jobStatus.kill();
			}
		}
	}

	@Override
	public boolean execute() {
		synchronized (this.jobStatus) {
			if ( !this.jobStatus.isRunning() && !this.jobStatus.isKilled() ) {
				this.jobStatus.setRunning(true);
				this.jobStatus.executionStarted(new Date());
			} else {
				this.logger.info("Cannot start execution of job name [" + this.getJob().getName() + "] group [" + this.getJob().getGroup() + "], the job is running or it has been killed");
				return false;
			}
		}
		// The execution must not block the jobStatus object, this is why is out of the synchronized block
		this.startExecution();
		return true;
	}

	private void startExecution() {
		this.logger.info("Starting execution of job name [" + this.getJob().getName() + "] group [" + this.getJob().getGroup() + "]");
		try {
			this.getJob().execute();
			synchronized (this.jobStatus) {
				this.jobStatus.executionEnded(new Date());
				this.jobStatus.setRunning(false);
				this.logger.info("Ended execution of job name [" + this.getJob().getName() + "] group [" + this.getJob().getGroup() + "]");
			}
		} catch (final Exception e) {
			synchronized (this.jobStatus) {
				this.jobStatus.executionError(new Date());
				this.jobStatus.setRunning(false);
				this.logger.error("Error during execution of job name [" + this.getJob().getName() + "] group [" + this.getJob().getGroup() + "]", e);
			}
		}
	}

	@Override
	public JobStatus getJobStatus() {
		return this.jobStatus;
	}

	@Override
	public void pause() {
		synchronized (this.jobStatus) {
			if (!this.jobStatus.isPaused()) {
				this.jobStatus.pause();
			}
		}
	}

	@Override
	public void resume() {
		synchronized (this.jobStatus) {
			if (this.jobStatus.isPaused()) {
				this.jobStatus.resume();
			}
		}
	}

	@Override
	public Job getJob() {
		return this.job;
	}

	@Override
	public boolean isValid() {
		return true;
	}

}
