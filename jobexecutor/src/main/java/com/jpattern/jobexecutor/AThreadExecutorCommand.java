package com.jpattern.jobexecutor;

import java.io.Serializable;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 04/lug/09 17:01:00
 *
 * @version $Id$
 */
public abstract class AThreadExecutorCommand implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static boolean FORCE_SHUTDOWN_WITH_SYSTEMEXIT0 = true;

    public abstract void exec();
}
