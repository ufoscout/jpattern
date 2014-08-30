package com.jpattern.jobexecutor.socket;

import java.io.IOException;
import java.net.Socket;

import com.jpattern.jobexecutor.iostream.CommunicationClient;
import com.jpattern.jobexecutor.iostream.ICommunicationChannel;
import com.jpattern.jobexecutor.iostream.IInputReader;
import com.jpattern.jobexecutor.iostream.IOutputWriter;
import com.jpattern.jobexecutor.iostream.StreamInputReader;
import com.jpattern.jobexecutor.iostream.StreamOutputWriter;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class CommunicationSocketClient implements ICommunicationChannel, Runnable {

	private static final long serialVersionUID = 1L;
	private Socket clientSocket;
	private ICommunicationChannel communicationClient;

	public CommunicationSocketClient( Socket clientSocket, ICommunicationClientStrategy communicationClientStrategy) throws IOException {
		IInputReader inputReader = new StreamInputReader( clientSocket.getInputStream() );
		IOutputWriter outputWriter = new StreamOutputWriter( clientSocket.getOutputStream() );
		communicationClient = new CommunicationClient(inputReader, outputWriter , communicationClientStrategy );
		this.clientSocket = clientSocket;
	}
	
	public String read() throws IOException {
		return communicationClient.read();
	}

	public void write(String message) {
		communicationClient.write( message );
	}
	
	public void close() {
		//communicationClient.close();
		System.out.println("lato client: closing socket...");
		try {
			clientSocket.close();
			System.out.println("lato client: socket closed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		communicationClient.run();
		close();
	}
	
}
