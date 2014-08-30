package com.jpattern.core.command;

import com.jpattern.shared.result.IErrorMessage;
import com.jpattern.shared.result.IResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public abstract class ACommandResult implements IResult {
	
	private static final long serialVersionUID = 1L;

	public abstract boolean isValid();
	
	public abstract void addErrorMessage(IErrorMessage errorMessage);
    
	public abstract String asString();
	
	public abstract boolean isExecutionEnd();
	
	public abstract void waitExecutionEnd() throws InterruptedException;
	
	abstract void setExecutionEnd(ACommand<?> command);

	abstract void setExecutionStart(ACommand<?> command);

}
