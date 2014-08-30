package com.jpattern.jobexecutor.execution;

import com.jpattern.jobexecutor.IJobExecutionStrategy;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 27/giu/09 18:33:24
 *
 * @version $Id$
 */
public class TimedJobExecutionStrategy implements IJobExecutionStrategy   {

    private static final long serialVersionUID = 1L;
    private int _executionTime;
    private int _executedTime = 0;
    private static final int INFINITE = -1;
    
    public TimedJobExecutionStrategy(int aExecutionTime) {
        _executionTime = aExecutionTime;
    }    

    public boolean canExecute() {
        
        if (_executionTime == INFINITE)
            return true;
        
        if (_executedTime >= _executionTime) 
            return false;

        _executedTime++;
        return true;
    }

    public String asString() {
        int remainig = (_executionTime - _executedTime) < 0 ? 0 : (_executionTime - _executedTime);
        return "TimedJobExecution execute fixed time, remaining " + remainig;
    }
}
