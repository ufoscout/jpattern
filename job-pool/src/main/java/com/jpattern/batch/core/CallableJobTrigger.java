package com.jpattern.batch.core;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jpattern.batch.Job;
import com.jpattern.batch.JobExecutionStrategy;
import com.jpattern.batch.JobExecutor;
import com.jpattern.batch.JobTrigger;
import com.jpattern.batch.logger.JobPoolLogger;
import com.jpattern.batch.status.JobStatus;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author cinafr
 *
 */
public class CallableJobTrigger implements Callable<Object>, JobTrigger {

	private JobExecutor jobExecutor;
	private boolean killed = false;
	private boolean alive = true;
	private final JobExecutionStrategy jobExecutionStrategy;
	private final AtomicBoolean serverStarted;
	private final ILogger logger = JobPoolLogger.getLogger(this.getClass());
	private final boolean ignoreJobPausedState;

	public CallableJobTrigger(final JobExecutor jobExecutor, final JobExecutionStrategy jobExecutionStrategy, final AtomicBoolean serverStarted) {
		this(jobExecutor, jobExecutionStrategy, serverStarted, false);
	}

	public CallableJobTrigger(final JobExecutor jobExecutor, final JobExecutionStrategy jobExecutionStrategy, final AtomicBoolean serverStarted, final boolean ignoreJobPausedState) {
		this.jobExecutor = jobExecutor;
		this.jobExecutionStrategy = jobExecutionStrategy;
		this.serverStarted = serverStarted;
		this.ignoreJobPausedState = ignoreJobPausedState;
	}

	@Override
	public Object call() throws Exception {
		this.startTrigger();
		return new Object();
	}

	private void startTrigger() {
		JobStatus jobStatus = this.jobExecutor.getJobStatus();
		Job job = this.jobExecutor.getJob();
		String triggerDescription = "trigger for job name [" + job.getName() + "] group [" + job.getGroup() + "] with strategy [" + this.jobExecutionStrategy.asString() + "]";
		this.logger.info("Starting " + triggerDescription);
		while(!this.killed && !this.jobExecutor.getJobStatus().isKilled() && this.jobExecutionStrategy.hasOtherExecution()) {

			boolean canExecute = this.jobExecutionStrategy.canExecute();
			if (this.serverStarted.get() && canExecute && (!jobStatus.isPaused() || this.ignoreJobPausedState ) && !jobStatus.isKilled()) {
				this.logger.debug("Launch job name [" + job.getName() + "] group [" + job.getGroup() + "]");
				this.jobExecutor.execute();
			}
			if (this.jobExecutionStrategy.hasOtherExecution()) {
				try {
					final long sleepTime = this.jobExecutionStrategy.millisecondsBeforeNextExecution();
					this.logger.debug("job name [" + job.getName() + "] group [" + job.getGroup() + "] sleeping for " + sleepTime + " ms");
					Thread.sleep(sleepTime);
				} catch (final InterruptedException e) {}
			}
		}
		this.alive = false;
		this.logger.info("Execution finished for " + triggerDescription);

	}

	@Override
	public void killTrigger() {
		this.killed = true;
		this.jobExecutor = new NullJobExecutor();
	}

	@Override
	public JobExecutor getJobExecutor() {
		return this.jobExecutor;
	}

	@Override
	public boolean isAlive() {
		return this.alive;
	}

}
