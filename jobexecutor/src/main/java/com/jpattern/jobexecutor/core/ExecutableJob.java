package com.jpattern.jobexecutor.core;

import java.util.Date;

import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.IExecutableJob;
import com.jpattern.jobexecutor.IExecutableJobResult;
import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.IJobResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class ExecutableJob implements IExecutableJob {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJob job;
	private IJobExecutionStrategy currentJobExecutionStrategy;
	private IJobExecutionStrategy originalJobExecutionStrategy;
	private boolean killed = false;
	private IExecutableJobResult executableJobResult;
	private IBooleanWrapper booleanWrapper;
	private boolean isWorking = false;

	public ExecutableJob(IJob job, IJobExecutionStrategy jobExecutionStrategy, IExecutableJobResult executableJobResult, IBooleanWrapper booleanWrapper) {
		this.job = job;
		this.originalJobExecutionStrategy = jobExecutionStrategy;
		this.executableJobResult = executableJobResult;
		this.booleanWrapper = booleanWrapper;
		setExecutionStrategy(jobExecutionStrategy);
	}
	
	public String executionAsString() {
		return currentJobExecutionStrategy.asString();
	}

	public void setExecutionStrategy(IJobExecutionStrategy aJobExecution) {
		this.currentJobExecutionStrategy = aJobExecution;
	}

	public void kill() {
		killed  = true;
	}
	
	public boolean isAlive() {
		return !killed;
	}

	public String getJobName() {
		return this.job.getName();		
	}

	public void resumeOriginalStrategy() {
		currentJobExecutionStrategy = originalJobExecutionStrategy;
	}

	public IExecutableJobResult call() throws Exception {
		//System.out.println("partito!!");
		while(!killed) {
			checkInterruptStatus();
			//System.out.println("pre-eseguito");
			if (booleanWrapper.getValue()) {
				//System.err.println("ESEGUITO!!!!");
				isWorking = true;
				execute();
				isWorking = false;
			}
			new PauseThread().pauseThread();
		}
		return executableJobResult;
	}

	private void execute() {
		if ( currentJobExecutionStrategy.canExecute() ) {
			try {
				IJobResult jobResult = job.execute();
				if ( jobResult == null) {
					jobResult = new NullJobResult();
				}
				executableJobResult.addJobResult( jobResult , new Date() );
			} catch (Exception e) {
				executableJobResult.addException( e , new Date() );
			}
		}
	}
	
	private void checkInterruptStatus() throws InterruptedException {
	    if (Thread.interrupted()) {
	      throw new InterruptedException("Thread was interrupted");
	    }
	}
	
	public boolean isWorking() {
		return isWorking;
	}
	
	public String isWorkingAsString() {
		if (isWorking()) {
			return "The job is currently working";
		}
		return "The job is NOT working";
	}

}
