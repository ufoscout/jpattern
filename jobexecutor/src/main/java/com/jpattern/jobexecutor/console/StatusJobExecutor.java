package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.command.StatusJobCommand;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class StatusJobExecutor implements IExecutor<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IWrappedResult _text;
	private IJobThreadPool _jobThreadPool;

	public StatusJobExecutor(IWrappedResult wrappedResult, IJobThreadPool jobThreadPool) {
		_text = wrappedResult;
		_jobThreadPool = jobThreadPool;
	}
	
	public void exec(String aString) {
		StringBuffer jobState = new StringBuffer();
		new StatusJobCommand(_jobThreadPool, aString, jobState ).exec();
		_text.add("job: " + aString + " --> status: " + jobState.toString() );
	}

}
