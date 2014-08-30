package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 04/lug/09 17:03:07
 *
 * @version $Id$
 */
public class ServerStopCommand extends AThreadExecutorCommand {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;

    public ServerStopCommand(IJobThreadPool jobThreadPool) {
        this.jobThreadPool = jobThreadPool;
    }
    
    public void exec() {
    	jobThreadPool.stop();
    }

}
