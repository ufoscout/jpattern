package com.jpattern.jobexecutor.socket;

import java.net.*;
import java.io.*;



/**
 * 
 * @author Francesco Cina'
 * 
 *         06/feb/2010
 */

public class SocketServer extends ASocket {

	private static final long serialVersionUID = 1L;
	
	private int port;
	private ICommunicatorManager communicator;

	private ServerSocket server;

	private Socket serverSocket;

	public SocketServer(String socketName, int port, ICommunicatorManager communicator) {
		super(socketName);
		this.port = port;
		this.communicator = communicator;
	} 


	public void closeConnection() {
		if (server != null && !server.isClosed()) {
			try {
				server.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void initialize() {
		try {
			System.out.println("Server attivo sulla porta: " + port + " attendo connessioni");
			server = new ServerSocket(port);
			while (!stopSocket) {	
				serverSocket = server.accept();
				System.out.println("Connessione accettata");
				new Thread( new CommunicationSocketServer(serverSocket , communicator ) ).start();
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
