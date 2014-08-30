package com.jpattern.jobexecutor;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public interface IJobThreadPool extends Serializable {

	void addJob(IJob aJob, IJobExecutionStrategy aJobExecution);
	
    void addJob(IJob aJob, IJobExecutionStrategy aJobExecution, IExecutableJobResult executableJobResult);
    
    String getName();
    
    List<String> getJobsName();

    IExecutableJob getExecutableJob(String aJobName);
    
    void pauseJob(String aJobName);
    
    void pauseAllJobs();

    void resumeJob(String aJobName);
    
    void resumeAllJobs();

    void modifyJobExecution(String aJobName, IJobExecutionStrategy aJobExecutionStrategy);

    void start();
    
    void stop();

    boolean isRunning();

    boolean isStarted();

    void shutDown();
}
