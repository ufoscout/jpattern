package com.jpattern.jobexecutor.execution;

import java.util.Date;

import com.jpattern.jobexecutor.IJobExecutionStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/giu/2010
 */
public class SleepingIntervalJobExecutionStrategy implements IJobExecutionStrategy {

    private static final long serialVersionUID = 1L;
    private long interval;
    private long lastTimeStamp;
    private boolean executed = false;
	private boolean returned = false;

    
    public SleepingIntervalJobExecutionStrategy( long sleepMilliseconds ) {
        
        interval = sleepMilliseconds;
        
    }

    public boolean canExecute() {
    	if ( !executed ) {
    		executed = true;
    		returned = true;
//    		System.out.println("not executed");
    		return returned;
    	}
    	
    	if ( returned == false ) {
    		long now = new Date().getTime();
//    		System.out.println("now = " + now);
//    		System.out.println("lastTimeStamp + interval = " + (lastTimeStamp + interval));
            if ( now >= (lastTimeStamp + interval)) {
            	returned = true;
            } 
    	} 
    	else {
    		lastTimeStamp = new Date().getTime();
//    		System.out.println("lastTimeStamp = " + lastTimeStamp);
    		returned = false;
    	}
        
//    	System.out.println("returning = " + returned);
        return returned;
    }

    public String asString() {
        return  "SleepingIntervalJobExecution repeat every  " + interval + " milliseconds";
    }
}
