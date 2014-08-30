package com.jpattern.jobexecutor.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpattern.jobexecutor.IExecutableJobResult;
import com.jpattern.jobexecutor.IJobResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class ExecutableJobResultMock implements IExecutableJobResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<IJobResult> jobResultList = new ArrayList<IJobResult>();
	private List<Exception> jobExceptionList = new ArrayList<Exception>();

	public void addJobResult(IJobResult jobResult, Date date) {
		jobResultList.add(jobResult);
	}

	public void addException(Exception e, Date date) {
		jobExceptionList.add(e);
	}
	
	public int getCorrectExecution() {
		return jobResultList.size();
	}

	public int getException() {
		return jobExceptionList.size();
	}


}
