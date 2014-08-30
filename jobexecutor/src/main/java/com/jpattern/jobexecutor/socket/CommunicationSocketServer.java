package com.jpattern.jobexecutor.socket;

import java.io.IOException;
import java.net.Socket;

import com.jpattern.jobexecutor.iostream.CommunicationServer;
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
public class CommunicationSocketServer implements ICommunicationChannel {
	
	private static final long serialVersionUID = 1L;
	
	private Socket serverSocket;
	private ICommunicationChannel communicationServer;
	private ICommunicatorManager communicationManager;

	public CommunicationSocketServer( Socket serverSocket , ICommunicatorManager communicationManager) throws IOException {
		this.serverSocket = serverSocket;
		IInputReader inputReader = new StreamInputReader( serverSocket.getInputStream() );
		IOutputWriter outputWriter = new StreamOutputWriter( serverSocket.getOutputStream() );
		this.communicationServer = new CommunicationServer(inputReader, outputWriter);
		this.communicationManager = communicationManager;
	}

	public String read() throws IOException {
		return communicationServer.read();
	}

	public void write(String message) {
		communicationServer.write( message );
	}
	
	public void close() {
		communicationServer.close();
		System.out.println("lato server: closign socket...");
		try {
			serverSocket.close();
			System.out.println("lato server: socket closed.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		communicationManager.execute(this);
	}

}
