package com.jpattern.jobexecutor.socket;

import java.io.IOException;

import com.jpattern.jobexecutor.ICommandExecutorHandler;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class IdentifySocketStrategyDecorator implements ICommunicationClientStrategy {

	private static final long serialVersionUID = 1L;
	private String readMessage = ICommandExecutorHandler.COMMAND_IDENTITY_APPLICATION;
	private ICommunicationClientStrategy communicationClientStrategy;
	private String applicationName;
	private boolean identified = false;
	
	public IdentifySocketStrategyDecorator(String applicationName, ICommunicationClientStrategy communicationClientStrategy) {
		this.communicationClientStrategy = communicationClientStrategy;
		this.applicationName = applicationName;
	}
	
	public String read() throws IOException {
		if ( !identified ) {
			return readMessage;
		}
		return communicationClientStrategy.read();
	}

	public void write(String message) {
		
		if (!identified) {
			if ( applicationName.equals(message) ) {
				identified = true;
				System.out.println("Applicazione identificata sulla porta socket: " + message);
			}
			else {
				System.out.println("La porta socket indicata e' occupata da un'altra applicazione!. Chiudo la connessione.");
				readMessage = ICommandExecutorHandler.COMMAND_CLOSE_CONSOLE;
			}
			return;
		}
		
		communicationClientStrategy.write(message); 
		
	}
	
}
