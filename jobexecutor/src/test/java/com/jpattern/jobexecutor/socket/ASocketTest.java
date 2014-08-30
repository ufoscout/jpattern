package com.jpattern.jobexecutor.socket;

import java.io.IOException;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.iostream.ICommunicationChannel;
import com.jpattern.jobexecutor.socket.ASocket;
import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;
import com.jpattern.jobexecutor.socket.ICommunicatorManager;
import com.jpattern.jobexecutor.socket.SocketClient;
import com.jpattern.jobexecutor.socket.SocketServer;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class ASocketTest extends BaseTest {

	ASocket serverSocket;
	ASocket clientSocket;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Thread.sleep( 100 );
		try {
			clientSocket.stopSocket();
		} catch (Exception e) {}
		Thread.sleep( 100 );
		try {
			serverSocket.stopSocket();
		} catch (Exception e) {}
	}
	
	public void testServerClientSocket() throws Exception {
		int serverPort = 44445;
		
		serverSocket = new SocketServer("serverSocket", serverPort, new MockServerManager() );
		serverSocket.start();
		clientSocket = new SocketClient("serverSocket", "localhost", serverPort, new MockClientStrategy() );
		clientSocket.start();
		
		Thread.sleep(2000);
		
	}

	
	class MockClientStrategy implements ICommunicationClientStrategy {

		private static final long serialVersionUID = 1L;

		public String read() throws IOException {
			System.out.println("client -> read -> sono il client");
			return "sono il client";
		}

		public void write(String message) {
			System.out.println("client -> write -> " + message);
		}
		
	}
	
	class MockServerManager implements ICommunicatorManager {

		private static final long serialVersionUID = 1L;

		public void execute(ICommunicationChannel communicatioChannel) {

			try {
				System.out.println("Server -> write -> ciao1");
				communicatioChannel.write( "ciao1" );
				System.out.println("Server -> write -> ciao2");
				communicatioChannel.write( "ciao2" );
				System.out.println("Server -> write -> ciao hci sei?");
				communicatioChannel.write( "ciao chi sei?" );
				String read = communicatioChannel.read();
				System.out.println("Server -> read -> " + read);
				assertEquals( "sono il client", read );
				
				System.out.println("Server -> close");
				communicatioChannel.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public String getName() {
			return "";
		}
	}
}
