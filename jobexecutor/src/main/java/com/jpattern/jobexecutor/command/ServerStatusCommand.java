package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 05/lug/09 14:36:04
 *
 * @version $Id$
 */
public class ServerStatusCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;
    private IBooleanWrapper booleanWrapper;

    public ServerStatusCommand(IJobThreadPool jobThreadPool, IBooleanWrapper resultBooleanWrapper) {
        this.jobThreadPool = jobThreadPool;
        this.booleanWrapper = resultBooleanWrapper;
    }
    
    public void exec() {
        booleanWrapper.setValue( jobThreadPool.isStarted() );
    }
}
