package com.jpattern.jobexecutor.execution;

import java.util.Date;

import com.jpattern.jobexecutor.IJobExecutionStrategy;
/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class IntervalJobExecutionStrategy implements IJobExecutionStrategy {

    private static final long serialVersionUID = 1L;
    private long interval;
    private long lastTimeStamp;

    
    public IntervalJobExecutionStrategy( long repeatEveryMilliseconds ) {
        
        interval = repeatEveryMilliseconds;
        lastTimeStamp = new Date().getTime();
        
    }

    public boolean canExecute() {
        long now = new Date().getTime();
        if ( now >= (lastTimeStamp + interval)) {
            lastTimeStamp = now;
           return true;
        } 
        
        return false;
    }

    public String asString() {
        return  "IntervalJobExecution repeat every  " + interval + " milliseconds";
    }
}
