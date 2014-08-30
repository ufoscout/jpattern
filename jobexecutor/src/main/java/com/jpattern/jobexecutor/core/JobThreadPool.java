package com.jpattern.jobexecutor.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;

import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.IExecutableJob;
import com.jpattern.jobexecutor.IExecutableJobResult;
import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class JobThreadPool implements IJobThreadPool {
	
	private static final long serialVersionUID = 1L;
	private Map<String, IExecutableJob> executableJobMap = new HashMap<String, IExecutableJob>();
	private boolean running = true;
	private boolean started = false;
	private IBooleanWrapper booleanWrapper = new BooleanWrapper( false );
	private String name; 

	public JobThreadPool(String applicationName) {
		this.name = applicationName;
	}
	
	public void addJob(IJob job, IJobExecutionStrategy jobExecutionStrategy) {
		addJob( job , jobExecutionStrategy, new NullExecutableJobResult() );
	}
	
	public void addJob(IJob job, IJobExecutionStrategy jobExecutionStrategy, IExecutableJobResult executableJobResult) {
		IExecutableJob executableJob = new ExecutableJob(job, jobExecutionStrategy, executableJobResult , booleanWrapper );
		Executors.newSingleThreadExecutor().submit( executableJob );
		executableJobMap.put( job.getName() , executableJob );
	}

	public IExecutableJob getExecutableJob(String jobName) {
		IExecutableJob result = new NullExecutableJob();
		if ( executableJobMap.containsKey(jobName) ) {
			result = executableJobMap.get(jobName);
		}
		return result;
	}

	public List<String> getJobsName() {
		List<String> result = new ArrayList<String>();
		for( Entry<String, IExecutableJob> jobEntry : executableJobMap.entrySet() ) {
			result.add( jobEntry.getKey() );
		}
		return result;
	}

	public boolean isRunning() {
		return running;
	}

	public boolean isStarted() {
		return started;
	}

	public void modifyJobExecution(String jobName, IJobExecutionStrategy jobExecutionStrategy) {
		getExecutableJob(jobName).setExecutionStrategy(jobExecutionStrategy);
	}

	public void pauseJob(String jobName) {
		modifyJobExecution(jobName, new NullJobExecutionStrategy() );
		
	}

	public void pauseAllJobs() {
		for( String jobName : getJobsName() ) {
			pauseJob(jobName);
		}
	}
	
	public void resumeJob(String jobName) {
		getExecutableJob(jobName).resumeOriginalStrategy();
	}

	public void resumeAllJobs() {
		for( String jobName : getJobsName() ) {
			resumeJob(jobName);
		}
	}
	
	public void shutDown() {
		stop();
		for( String jobName : getJobsName() ) {
			getExecutableJob(jobName).kill();
		}
		running = false;
	}

	public void start() {
		if (started && !running)
			return;
		started = true;
		booleanWrapper.setValue(started);
	}

	public synchronized void stop() {
		started = false;
		booleanWrapper.setValue(started);
	}

	public String getName() {
		return name;
	}

}
