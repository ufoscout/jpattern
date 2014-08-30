package com.jpattern.jobexecutor;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public interface IJob extends Serializable {

    String getName();
    
    IJobResult execute() throws Exception;
}
