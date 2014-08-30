package com.jpattern.jobexecutor.command;

import com.jpattern.jobexecutor.AThreadExecutorCommand;
import com.jpattern.jobexecutor.IJobThreadPool;

/**
 * 
 * @author Francesco Cina'
 *
 * 02/apr/2010
 */
public class ServerShutDownBrutalCommand  extends AThreadExecutorCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean canCallSystemExit0;

	public ServerShutDownBrutalCommand(IJobThreadPool jobThreadPool, boolean canCallSystemExit0) {
		this.canCallSystemExit0 = canCallSystemExit0;
    }
    
    public void exec() {
        if (canCallSystemExit0) {
        	System.exit(0);
        }
    }
}
