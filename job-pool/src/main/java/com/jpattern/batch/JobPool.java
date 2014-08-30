package com.jpattern.batch;

import java.io.Serializable;
import java.util.List;

import com.jpattern.batch.status.JobStatus;


/**
 * 
 * @author Francesco Cina'
 *
 * Apr 4, 2012
 */
public interface JobPool extends Serializable {

	void addJob(Job aJob, JobExecutionStrategy aJobExecution);

	String getName();

	List<String> getGroupsName();

	List<String> getJobsName(String groupName);

	JobStatus getJobStatus(String jobName, String groupName);

	void pauseJob(String jobName, String groupName);

	void pauseGroup(String groupName);

	void pauseAllJobs();

	void resumeJob(String jobName, String groupName);

	void resumeGroup(String groupName);

	void resumeAllJobs();

	void modifyJobExecution(String jobName, String groupName, JobExecutionStrategy aJobExecutionStrategy);

	void start();

	boolean isStarted();

	boolean isShutdown();

	void shutdown(final boolean waitJobsExecutionEnd);

	void executeJob(String jobName, String groupName);

	void executeGroup(String groupName);

	void executeAllJobs();

	void removeJob(String jobName, String groupName);

	void removeGroup(String groupName);

	void removeAllJobs();

}
