package com.jpattern.jobexecutor.iostream;

import java.io.IOException;

import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class CommunicationClient implements ICommunicationChannel, Runnable {

	private static final long serialVersionUID = 1L;
	IInputReader channelInputReader;
	IOutputWriter channelOutputWriter;
	boolean loop;
	private ICommunicationClientStrategy communicationClientStrategy;

	public CommunicationClient( IInputReader channelInputReader , IOutputWriter channelOutputWriter, ICommunicationClientStrategy communicationClientStrategy) {
		this.channelOutputWriter = channelOutputWriter;
		this.channelInputReader = channelInputReader;
		this.communicationClientStrategy = communicationClientStrategy;
	}
	
	public String read() throws IOException {
		return communicationClientStrategy.read();
	}

	public void write(String message) {
		communicationClientStrategy.write( message );
	}
	
	public void identify() {
	}
	
	public void close() {
		loop = false;
	}

	public void run() {
		loop = true;
		while ( loop ) {
			String command;
			try {
				command = channelInputReader.read();

				if ( command.equals( ICommunicationChannel.READ_COMMAND )) {
					channelOutputWriter.write( read() );
				}
				else if ( command.equals( ICommunicationChannel.WRITE_COMMAND )) {
					write( channelInputReader.read() );
				}
				else if ( command.equals( ICommunicationChannel.STOP_COMMAND )) {
//					System.err.println("ricevuto Stop Command");
					close();
				}

			} catch (Exception e) {
				close();
			}
		}
	}
	
}
