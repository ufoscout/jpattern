package com.jpattern.jobexecutor;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public interface ICommandExecutorHandler extends Serializable {

	String COMMAND_IDENTITY_APPLICATION = "IDENTIFY-APPLICATION";
	String COMMAND_START = "START";
	String COMMAND_STOP = "STOP";
	String COMMAND_SHUTDOWN = "SHUTDOWN";
	String COMMAND_SHUTDOWN_BRUTAL = "SHUTDOWN-BRUTAL";
	String COMMAND_CLOSE_CONSOLE = "CLOSE-CONSOLE";
	String COMMAND_UNKNOWN = "UNKNOWN";
	String COMMAND_CONSOLE_MANAGER = "CONSOLE-MANAGER";
	
	ICommandExecutorHandler exec(String aCommandLine, IWrappedResult aWrappedResult, IBooleanWrapper stopCommand);
    
    String getCommandDescription();
}
