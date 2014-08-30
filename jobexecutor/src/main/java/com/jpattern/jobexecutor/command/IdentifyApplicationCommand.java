package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/apr/2010
 */
public class IdentifyApplicationCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
    private StringBuffer result;

    public IdentifyApplicationCommand(IJobThreadPool jobThreadPool, StringBuffer result) {
        this.jobThreadPool = jobThreadPool;
        this.result = result;
    }
    
    public void exec() {
        result.append(jobThreadPool.getName());
    }

}
