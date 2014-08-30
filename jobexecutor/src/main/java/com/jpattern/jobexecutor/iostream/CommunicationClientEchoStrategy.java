package com.jpattern.jobexecutor.iostream;

import java.io.IOException;

import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 24/mar/2010
 */
public class CommunicationClientEchoStrategy implements ICommunicationClientStrategy {

	private static final long serialVersionUID = 1L;
	private IOutputWriter channelOutputWriter;
	private IInputReader channelInputReader;

	public CommunicationClientEchoStrategy(IInputReader channelInputReader , IOutputWriter channelOutputWriter) {
		this.channelOutputWriter = channelOutputWriter;
		this.channelInputReader = channelInputReader;
	}
	
	public String read() throws IOException {
		return channelInputReader.read();
	}

	public void write(String message) {
		channelOutputWriter.write(message);
	}

}
