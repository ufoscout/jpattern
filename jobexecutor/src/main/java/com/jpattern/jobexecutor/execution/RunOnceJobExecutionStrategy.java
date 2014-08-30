package com.jpattern.jobexecutor.execution;

import com.jpattern.jobexecutor.IJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/giu/2010
 */
public class RunOnceJobExecutionStrategy implements IJobExecutionStrategy   {

    private static final long serialVersionUID = 1L;
    private int _executionTime;
    private int _executedTime = 0;
    
    public RunOnceJobExecutionStrategy() {
        _executionTime = 1;
    }    

    public boolean canExecute() {
        
        if (_executedTime >= _executionTime) 
            return false;

        _executedTime++;
        return true;
    }

    public String asString() {
        int remainig = (_executionTime - _executedTime) < 0 ? 0 : (_executionTime - _executedTime);
        return "RunOnceJobExecution, remaining " + remainig + " executions";
    }
}
