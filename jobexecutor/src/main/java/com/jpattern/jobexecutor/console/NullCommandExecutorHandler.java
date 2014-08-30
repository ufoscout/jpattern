package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IWrappedResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 02/apr/2010
 */
public class NullCommandExecutorHandler implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCommandDescription() {
		return "";
	}

	public ICommandExecutorHandler exec(String aCommandLine, IWrappedResult aWrappedResult, IBooleanWrapper stopCommand) {
		return this;
	}

}
