package com.jpattern.jobexecutor.iostream;

import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public interface ICommunicationChannel extends Serializable, Runnable {
	
	String READ_COMMAND = "CommunicationChannelReadCommand";
	String WRITE_COMMAND = "CommunicationChannelWriteCommand";
	String STOP_COMMAND = "CommunicationChannelStopCommand";
	
	
	String read() throws IOException;
	
	void write(String message);
	
	void close();

	void run();
	
}
