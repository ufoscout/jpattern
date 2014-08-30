package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 04/lug/09 17:04:44
 *
 * @version $Id$
 */
public class PauseJobCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
    private String jobName;

    public PauseJobCommand(IJobThreadPool jobThreadPool, String jobName) {
        this.jobThreadPool = jobThreadPool;
        this.jobName = jobName;
    }
    
    public void exec() {
        jobThreadPool.pauseJob(jobName);
    }

}
