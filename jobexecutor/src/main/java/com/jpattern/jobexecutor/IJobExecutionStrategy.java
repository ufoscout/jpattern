package com.jpattern.jobexecutor;

import java.io.Serializable;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 27/giu/09 18:30:46
 *
 * @version $Id$
 */
public interface IJobExecutionStrategy extends Serializable  {

        boolean canExecute();
        
        String asString();
}
