package com.jpattern.jobexecutor.command;

import java.util.List;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 05/lug/09 14:33:29
 *
 * @version $Id$
 */
public class ListJobsCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
    private List<String> _list;

    public ListJobsCommand(IJobThreadPool jobThreadPool, List<String> resultJobsName) {
        this.jobThreadPool = jobThreadPool;
        _list = resultJobsName;
    }
    
    public void exec() {
        _list.addAll(  jobThreadPool.getJobsName() );
    }
    
}