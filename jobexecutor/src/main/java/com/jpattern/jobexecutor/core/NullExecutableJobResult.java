package com.jpattern.jobexecutor.core;

import java.util.Date;

import com.jpattern.jobexecutor.IExecutableJobResult;
import com.jpattern.jobexecutor.IJobResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class NullExecutableJobResult implements IExecutableJobResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addJobResult(IJobResult jobResult, Date date) {
	}

	public void addException(Exception e, Date date) {
	}

}
