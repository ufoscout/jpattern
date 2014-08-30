package com.jpattern.jobexecutor.socket;

import java.net.*;
import java.io.*;

import com.jpattern.jobexecutor.iostream.ICommunicationChannel;


/**
 * 
 * @author Francesco Cina'
 * 
 *         06/feb/2010
 */

public class SocketClient extends ASocket {

	private static final long serialVersionUID = 1L;
	
	private Socket clientSocket;

	private String address;

	private int port;

	private ICommunicationClientStrategy communicationClientStrategy;

	public SocketClient(String socketName, String address, int port, ICommunicationClientStrategy communicationClientStrategy) {
		super(socketName);
		this.address = address;
		this.port = port;
		this.communicationClientStrategy = communicationClientStrategy;
	} 

	public void closeConnection() {
		try {
			if( !clientSocket.isClosed() ) {
				clientSocket.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		try {
			clientSocket = new Socket(address, port);
			
//			IInputReader inputReader = new StreamInputReader( clientSocket.getInputStream() );
//			IOutputWriter outputWriter = new StreamOutputWriter( clientSocket.getOutputStream() );
			
			ICommunicationChannel communicationChannel = new CommunicationSocketClient( clientSocket , communicationClientStrategy );
			communicationChannel.run();
			closeConnection();
			
		} catch (IOException e) {
			System.out.println("Server is not running.");
		} 		
	}
	
	
}
