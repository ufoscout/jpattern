package com.jpattern.jobexecutor.command;

import java.util.List;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 05/lug/09 15:14:36
 *
 * @version $Id$
 */
public class ServerShutDownCommand  extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
	private boolean canCallSystemExit0;

    public ServerShutDownCommand(IJobThreadPool jodThreadPool, boolean canCallSystemExit0) {
        this.jobThreadPool = jodThreadPool;
        this.canCallSystemExit0 = canCallSystemExit0;
    }
    
    public void exec() {
        List<String> jobNames = jobThreadPool.getJobsName();
        jobThreadPool.shutDown();
        
        boolean allExecutionEnded = false;
        
        while (!allExecutionEnded) {
        	allExecutionEnded = true;
        	for(String jobName : jobNames) {
        		if ( jobThreadPool.getExecutableJob(jobName).isWorking()) {
        			allExecutionEnded = false;
        		}
        	}
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        if (canCallSystemExit0) {
        	System.exit(0);
        }
    }
}
