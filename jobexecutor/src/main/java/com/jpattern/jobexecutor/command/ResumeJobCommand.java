package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 04/lug/09 17:06:16
 *
 * @version $Id$
 */
public class ResumeJobCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
    private String jobName;

    public ResumeJobCommand(IJobThreadPool jobThreadPool, String jobName) {
        this.jobThreadPool = jobThreadPool;
        this.jobName = jobName;
    }
    
    public void exec() {
        jobThreadPool.resumeJob(jobName);
    }

}
