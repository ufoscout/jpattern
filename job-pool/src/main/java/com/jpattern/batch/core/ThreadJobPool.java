package com.jpattern.batch.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jpattern.batch.Job;
import com.jpattern.batch.JobExecutor;
import com.jpattern.batch.JobExecutionStrategy;
import com.jpattern.batch.JobPool;
import com.jpattern.batch.JobTrigger;
import com.jpattern.batch.execution.RunOnceJobExecutionStrategy;
import com.jpattern.batch.logger.JobPoolLogger;
import com.jpattern.batch.status.JobStatus;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class ThreadJobPool implements JobPool {

	private static final long serialVersionUID = 1L;
	private final Map<String, Map<String, JobTrigger>> executableJobMap = new TreeMap<String, Map<String, JobTrigger>>();
	private boolean shutdown = false;
	private final AtomicBoolean serverStarted = new AtomicBoolean( false );
	private final String name;
	private final ILogger logger = JobPoolLogger.getLogger(this.getClass());

	public ThreadJobPool(final String applicationName) {
		this.name = applicationName;
		this.logger.info("JobPool [" + applicationName + "] created");
	}

	@Override
	public void addJob(final Job job, final JobExecutionStrategy jobExecutionStrategy) {
		this.logger.info("JobPool [" + this.name + "]: adding new job; name [" + job.getName() + "] group [" + job.getGroup() + "]");
		final JobExecutorImpl jobExecutor = new JobExecutorImpl(job);
		this.registerJob( jobExecutionStrategy, jobExecutor );
	}

	private void registerJob(final JobExecutionStrategy jobExecutionStrategy, final JobExecutor jobExecutor) {
		final String jobGroup = jobExecutor.getJobStatus().getGroupName();
		final String jobName = jobExecutor.getJobStatus().getJobName();
		if (!this.executableJobMap.containsKey(jobGroup)) {
			this.executableJobMap.put(jobGroup, new TreeMap<String, JobTrigger>());
		}
		final Map<String, JobTrigger> groupMap = this.executableJobMap.get(jobGroup);
		final CallableJobTrigger trigger = new CallableJobTrigger(jobExecutor, jobExecutionStrategy, this.serverStarted);
		groupMap.put(jobName, trigger);
		this.getExecutorService().submit( trigger );
	}

	@Override
	public JobStatus getJobStatus(final String jobName, final String groupName) {
		return this.getJobExecutor(jobName, groupName).getJobStatus();
	}

	@Override
	public List<String> getJobsName(final String groupName) {
		final List<String> result = new ArrayList<String>();
		if (this.executableJobMap.containsKey(groupName)) {
			final Map<String, JobTrigger> group = this.executableJobMap.get(groupName);
			for( final Entry<String, JobTrigger> jobEntry : group.entrySet() ) {
				result.add( jobEntry.getKey() );
			}
		}
		return result;
	}

	@Override
	public List<String> getGroupsName() {
		final List<String> result = new ArrayList<String>();
		for( final Entry<String, Map<String, JobTrigger>> jobEntry : this.executableJobMap.entrySet() ) {
			result.add( jobEntry.getKey() );
		}
		return result;
	}

	@Override
	public boolean isStarted() {
		return this.serverStarted.get();
	}

	@Override
	public void modifyJobExecution(final String jobName, final String groupName, final JobExecutionStrategy jobExecutionStrategy) {
		this.logger.info("JobPool [" + this.name + "]: rescheduling job name [" + jobName + "] group [" + groupName + "]");
		final JobExecutor executor = this.removeTrigger(jobName, groupName);
		if (executor.isValid()) {
			this.registerJob( jobExecutionStrategy, executor );
		}
	}

	@Override
	public void executeJob(final String jobName, final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: execute job name [" + jobName + "] group [" + groupName + "]");
		final CallableJobTrigger trigger = new CallableJobTrigger(this.getJobExecutor(jobName, groupName), new RunOnceJobExecutionStrategy(), this.serverStarted, true);
		this.getExecutorService().submit(trigger);
	}

	@Override
	public void executeGroup(final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: execute group [" + groupName + "]");
		for( final String jobName : this.getJobsName(groupName) ) {
			this.executeJob(jobName, groupName);
		}
	}

	@Override
	public void executeAllJobs() {
		this.logger.info("JobPool [" + this.name + "]: execute all jobs");
		for( final String groupName : this.getGroupsName() ) {
			this.executeGroup(groupName);
		}
	}

	@Override
	public void pauseJob(final String jobName, final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: pause job name [" + jobName + "] group [" + groupName + "]");
		this.getJobExecutor(jobName, groupName).pause();
	}

	@Override
	public void pauseGroup(final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: pause group [" + groupName + "]");
		for( final String jobName : this.getJobsName(groupName) ) {
			this.pauseJob(jobName, groupName);
		}
	}

	@Override
	public void pauseAllJobs() {
		this.logger.info("JobPool [" + this.name + "]: pause all jobs");
		for( final String groupName : this.getGroupsName() ) {
			this.pauseGroup(groupName);
		}
	}

	@Override
	public void resumeJob(final String jobName, final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: resume job name [" + jobName + "] group [" + groupName + "]");
		this.getJobExecutor(jobName, groupName).resume();
	}

	@Override
	public void resumeGroup(final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: resume group [" + groupName + "]");
		for( final String jobName : this.getJobsName(groupName) ) {
			this.resumeJob(jobName, groupName);
		}
	}

	@Override
	public void resumeAllJobs() {
		this.logger.info("JobPool [" + this.name + "]: resume all jobs");
		for( final String groupName : this.getGroupsName() ) {
			this.resumeGroup(groupName);
		}
	}

	@Override
	public void removeJob(final String jobName, final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: remove job name [" + jobName + "] group [" + groupName + "]");
		this.removeTrigger(jobName, groupName).kill();
	}

	private JobExecutor removeTrigger(final String jobName, final String groupName) {
		if (this.executableJobMap.containsKey(groupName)) {
			final Map<String, JobTrigger> group = this.executableJobMap.get(groupName);
			if ( group.containsKey(jobName) ) {
				final JobTrigger trigger = group.get(jobName);
				final JobExecutor jobExecutor = trigger.getJobExecutor();
				group.remove(jobName);
				trigger.killTrigger();
				return jobExecutor;
			}
		}
		return new NullJobExecutor();

	}

	@Override
	public void removeGroup(final String groupName) {
		this.logger.info("JobPool [" + this.name + "]: remove group [" + groupName + "]");
		for( final String jobName : this.getJobsName(groupName) ) {
			this.removeJob(jobName, groupName);
		}
	}

	@Override
	public void removeAllJobs() {
		this.logger.info("JobPool [" + this.name + "]: remove all jobs");
		for( final String groupName : this.getGroupsName() ) {
			this.removeGroup(groupName);
		}
	}

	@Override
	public void shutdown(final boolean waitJobsExecutionEnd) {
		if (!this.shutdown){
			this.logger.info("JobPool [" + this.name +  "]: Begin engine shutdown... ");
			this.serverStarted.set(false);
			for( final String groupName : this.getGroupsName() ) {
				for( final String jobName : this.getJobsName(groupName) ) {
					this.getJobExecutor(jobName, groupName).kill();
				}
			}
			if (waitJobsExecutionEnd) {
				this.logger.info("JobPool [" + this.name +  "]: Waiting job executions end... ");
				boolean allExecutionEnded = false;
				while (!allExecutionEnded) {
					allExecutionEnded = true;
					for (final String groupName : this.getGroupsName() ) {
						for(final String jobName : this.getJobsName(groupName)) {
							if ( this.getJobStatus(jobName, groupName).isRunning()) {
								allExecutionEnded = false;
							}
						}
					}
					try {
						Thread.sleep(100);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.logger.info("JobPool [" + this.name +  "]: Job executions ended ");
			}
			this.shutdown = true;
			this.logger.info("JobPool [" + this.name +  "]: Shutdown engine finished.");
		}
	}

	@Override
	public void start() {
		if (this.shutdown) {
			throw new RuntimeException("JobPool [" + this.name +  "]: Cannot start after shutdown");
		}
		if (!this.serverStarted.get()) {
			this.logger.info("JobPool [" + this.name +  "]: starting engine... ");
			this.serverStarted.set(true);
			this.logger.info("JobPool [" + this.name +  "]: engine started.");
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean isShutdown() {
		return this.shutdown;
	}

	/**
	 * This should be changed with a pool to control the number of concurrent Threads
	 * @return
	 */
	private ExecutorService getExecutorService() {
		return Executors.newSingleThreadExecutor();
	}

	protected JobTrigger getJobTrigger(final String jobName, final String groupName) {
		if (this.executableJobMap.containsKey(groupName)) {
			final Map<String, JobTrigger> group = this.executableJobMap.get(groupName);
			if ( group.containsKey(jobName) ) {
				return group.get(jobName);
			}
		}
		return new NullJobTrigger();
	}

	private JobExecutor getJobExecutor(final String jobName, final String groupName) {
		return this.getJobTrigger(jobName, groupName).getJobExecutor();
	}
}
