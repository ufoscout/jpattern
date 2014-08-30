package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 04/lug/09 17:07:30
 *
 * @version $Id$
 */
public class ModifyJobExecutionCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
    private String jobName;
    private IJobExecutionStrategy jobExecutionStrategy;

    public ModifyJobExecutionCommand(IJobThreadPool jobThreadPool, String jobName, IJobExecutionStrategy jobExecutionStrategy) {
        this.jobThreadPool = jobThreadPool;
        this.jobName = jobName;
        this.jobExecutionStrategy = jobExecutionStrategy;
    }
    
    public void exec() {
        jobThreadPool.modifyJobExecution(jobName, jobExecutionStrategy);
    }

}
