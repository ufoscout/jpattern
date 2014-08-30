package com.jpattern.jobexecutor.iostream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.iostream.CommunicationClient;
import com.jpattern.jobexecutor.iostream.CommunicationClientEchoStrategy;
import com.jpattern.jobexecutor.iostream.CommunicationServer;
import com.jpattern.jobexecutor.iostream.ICommunicationChannel;
import com.jpattern.jobexecutor.iostream.IInputReader;
import com.jpattern.jobexecutor.iostream.IOutputWriter;
import com.jpattern.jobexecutor.iostream.StreamInputReader;
import com.jpattern.jobexecutor.iostream.StreamOutputWriter;
import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;


/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class ICommunicationChannelTest extends BaseTest {
	
	private int serverSocketPort = 44444;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testSocketCommunication() throws Exception {
		ServerSocketMock serverSocketMock = new ServerSocketMock();
		new Thread( serverSocketMock ).start();
		
		Thread.sleep(2000);
		
		ClientSocketMock clientSocketMock = new ClientSocketMock();
		Thread clientThread = new Thread( clientSocketMock );
		clientThread.start();
		clientThread.join();
	}
	
	
	class ServerSocketMock implements Runnable {

		public void run() {
			ServerSocket server;
			try {
				server = new ServerSocket(serverSocketPort);
				Socket serverSocket = server.accept();
				System.out.println("SocketMock: Connessione accettata");
				IInputReader inputReader = new StreamInputReader( serverSocket.getInputStream() );
				IOutputWriter outputWriter = new StreamOutputWriter( serverSocket.getOutputStream() );
				ICommunicationChannel communicationChannel = new CommunicationServer( inputReader , outputWriter );
				
				communicationChannel.write("Come ti chiami?");
				String name = communicationChannel.read();
				communicationChannel.write("ciao " + name + "!");
				communicationChannel.write("ora chiudo.");
				communicationChannel.close();
				
			} catch (IOException e) {
				e.printStackTrace();
				assertTrue(false);
			}
		}
	}
	
	class ClientSocketMock implements Runnable {

		public void run() {
			Socket clientSocket;
			try {
				clientSocket = new Socket("localhost", serverSocketPort);
				IInputReader inputReader = new StreamInputReader( clientSocket.getInputStream() );
				IOutputWriter outputWriter = new StreamOutputWriter( clientSocket.getOutputStream() );
				
				ICommunicationClientStrategy communicationClientStrategy = new CommunicationClientEchoStrategy(new InputMock(), new OutputMock());
				ICommunicationChannel communicationChannel = new CommunicationClient( inputReader , outputWriter , communicationClientStrategy );
				communicationChannel.run();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				assertTrue(false);
			} catch (IOException e) {
				e.printStackTrace();
				assertTrue(false);
			}
		}
		
	}
	
	class InputMock implements IInputReader {

		private static final long serialVersionUID = 1L;
		String name = "mago";
		
		public String read() throws IOException {
			System.out.println("inviato: " + name);
			return name;
		}
	}
	
	class OutputMock implements IOutputWriter {

		private static final long serialVersionUID = 1L;
		String[] ricevuti = {"Come ti chiami?", "ciao mago!","ora chiudo."};
		int count = 0;
		
		public void write(String message) {
			System.out.println("ricevuto: " + message);
			assertEquals( ricevuti[count++] , message );
		}
	}
}
