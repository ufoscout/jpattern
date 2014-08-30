package com.jpattern.jobexecutor.iostream;

import java.io.IOException;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class CommunicationServer implements ICommunicationChannel {
	
	private static final long serialVersionUID = 1L;
	private IInputReader inputReader;
	private IOutputWriter outputWriter;

	public CommunicationServer( IInputReader inputReader , IOutputWriter outputWriter) {
		this.outputWriter = outputWriter;
		this.inputReader = inputReader;
	}

	public String read() throws IOException {
		outputWriter.write( ICommunicationChannel.READ_COMMAND );
		return inputReader.read();
	}

	public void write(String message) {
		outputWriter.write( ICommunicationChannel.WRITE_COMMAND );
		outputWriter.write( message );
	}
	
	public void close() {
		outputWriter.write( ICommunicationChannel.STOP_COMMAND );
	}

	public void run() {
	}

}
