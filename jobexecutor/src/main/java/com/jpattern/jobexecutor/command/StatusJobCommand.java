package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IExecutableJob;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 05/lug/09 17:53:59
 *
 * @version $Id$
 */
public class StatusJobCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
    private String jobName;
    private StringBuffer result;

    public StatusJobCommand(IJobThreadPool jobThreadPool, String jobName, StringBuffer result) {
        this.jobThreadPool = jobThreadPool;
        this.jobName = jobName;
        this.result = result;
    }
    
    public void exec() {
        IExecutableJob job = jobThreadPool.getExecutableJob(jobName);
		result.append(job.executionAsString() + " [" + job.isWorkingAsString() + "]");
    }

}
