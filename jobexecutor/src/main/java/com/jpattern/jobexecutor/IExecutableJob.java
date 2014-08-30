package com.jpattern.jobexecutor;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 27/giu/09 17:34:18
 *
 * @version $Id$
 */
public interface IExecutableJob extends Callable<IExecutableJobResult>, Serializable   {

    void setExecutionStrategy(IJobExecutionStrategy aJobExecution);
    
    void resumeOriginalStrategy();
    
    String executionAsString();
    
    String getJobName();
    
    public boolean isWorking();
    
    public String isWorkingAsString();
    
    boolean isAlive();
    
    void kill();
}
