package com.jpattern.jobexecutor.socket;

import java.util.List;

import com.jpattern.jobexecutor.IExecutableJob;
import com.jpattern.jobexecutor.IExecutableJobResult;
import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.core.JobThreadPool;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/apr/2010
 */
public class SocketJobThreadPool implements IJobThreadPool {
	
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
	private SocketServer socketServer;
	private boolean socketStarted = false;

	public SocketJobThreadPool(String applicationName , int adminPort) {
		this.jobThreadPool = new JobThreadPool(applicationName);
		
		ICommunicatorManager communicator = new JobThreadPoolCommunicatorManager(this);
		socketServer = new SocketServer(applicationName, adminPort, communicator);
	}

	public void addJob(IJob aJob, IJobExecutionStrategy aJobExecution) {
		jobThreadPool.addJob(aJob, aJobExecution);
	}

	public void addJob(IJob aJob, IJobExecutionStrategy aJobExecution, IExecutableJobResult executableJobResult) {
		jobThreadPool.addJob(aJob, aJobExecution, executableJobResult);
	}

	public IExecutableJob getExecutableJob(String aJobName) {
		return jobThreadPool.getExecutableJob(aJobName);
	}

	public List<String> getJobsName() {
		return jobThreadPool.getJobsName();
	}

	public String getName() {
		return jobThreadPool.getName();
	}

	public boolean isRunning() {
		return jobThreadPool.isRunning();
	}

	public boolean isStarted() {
		return jobThreadPool.isStarted();
	}

	public void modifyJobExecution(String aJobName, IJobExecutionStrategy aJobExecutionStrategy) {
		jobThreadPool.modifyJobExecution(aJobName, aJobExecutionStrategy);
	}

	public void pauseAllJobs() {
		jobThreadPool.pauseAllJobs();
	}

	public void pauseJob(String aJobName) {
		jobThreadPool.pauseJob(aJobName);
	}

	public void resumeAllJobs() {
		jobThreadPool.resumeAllJobs();
	}

	public void resumeJob(String aJobName) {
		jobThreadPool.resumeJob(aJobName);
	}

	public void shutDown() {
		jobThreadPool.shutDown();
//		socketServer.stopSocket();
	}

	public void start() {
		if(!socketStarted) {
			socketStarted = true;
			socketServer.start();
		}
		jobThreadPool.start();
	}

	public void stop() {
		jobThreadPool.stop();
	}

	public void closeAdminSocket() {
		socketServer.stopSocket();
	}
}
