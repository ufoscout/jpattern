package com.jpattern.jobexecutor.mock;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.jobexecutor.IExecutableJob;
import com.jpattern.jobexecutor.IExecutableJobResult;
import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Francesco Cina'
 *
 * 02/apr/2010
 */
public class MockJobThreadPool implements IJobThreadPool {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lastExecuted;
	private String name;
	
	public MockJobThreadPool() {
		this("");
	}
	
	public MockJobThreadPool(String name) {
		this.name = name;
	}

	public void addJob(IJob aJob, IJobExecutionStrategy aJobExecution) {
	}

	public void addJob(IJob aJob, IJobExecutionStrategy aJobExecution,
			IExecutableJobResult executableJobResult) {
		
	}

	public IExecutableJob getExecutableJob(String aJobName) {
		return null;
	}

	public List<String> getJobsName() {
		return new ArrayList<String>();
	}

	public boolean isRunning() {
		return false;
	}

	public boolean isStarted() {
		return false;
	}

	public void modifyJobExecution(String aJobName,
			IJobExecutionStrategy aJobExecutionStrategy) {
		lastExecuted = "MODIFYJOBEXECUTION" + aJobName;
		System.out.println(lastExecuted);
	}

	public void pauseAllJobs() {
		lastExecuted = "PAUSEALLJOBS";	
		System.out.println(lastExecuted);
	}

	public void pauseJob(String aJobName) {
		lastExecuted = "PAUSEJOB" + aJobName;
		System.out.println(lastExecuted);
	}

	public void resumeAllJobs() {
		lastExecuted = "RESUMEALLJOBS";
		System.out.println(lastExecuted);
	}

	public void resumeJob(String aJobName) {
		lastExecuted = "RESUMEJOB" + aJobName;	
		System.out.println(lastExecuted);
	}

	public void shutDown() {
		lastExecuted = "SHUTDOWN";
		System.out.println(lastExecuted);
	}

	public void start() {
		lastExecuted = "START";
		System.out.println(lastExecuted);
	}

	public void stop() {
		lastExecuted = "STOP";
		System.out.println("lastExecuted=" + lastExecuted);

	}
	
	public String lastExecuted() {
		return lastExecuted;
	}

	public String getName() {
		return name;
	}
	
}