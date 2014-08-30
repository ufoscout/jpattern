package com.jpattern.jobexecutor;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public interface IExecutableJobResult extends Serializable {
	
	void addJobResult(IJobResult jobResult, Date date);

	void addException(Exception e, Date date);
	
}
