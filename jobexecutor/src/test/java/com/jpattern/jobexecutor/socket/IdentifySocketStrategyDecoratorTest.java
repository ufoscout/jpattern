package com.jpattern.jobexecutor.socket;

import java.io.IOException;
import java.net.Socket;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;
import com.jpattern.jobexecutor.socket.IdentifySocketStrategyDecorator;
import com.jpattern.jobexecutor.socket.SocketClient;
import com.jpattern.jobexecutor.socket.SocketJobThreadPool;

/**
 * 
 * @author Francesco Cina'
 *
 * 05/apr/2010
 */
public class IdentifySocketStrategyDecoratorTest extends BaseTest {

	private int port = 12345;
	private SocketClient socketClient;
	private SocketJobThreadPool socketThreadPool;
	
	protected void setUp() throws Exception {
		super.setUp();
		Thread.sleep(250);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Thread.sleep(250);
		if ( socketThreadPool != null) {
			socketThreadPool.shutDown();
			socketThreadPool.closeAdminSocket();
		}
		Thread.sleep(250);
		
	}

	public void testIdentifyTrue() throws Exception {
		String applicationName = "applicationName";
		
		socketThreadPool = new SocketJobThreadPool( applicationName , port);
		socketThreadPool.start();
		
		Thread.sleep(200);
		
		boolean serverClosed = false;
		try {
			Socket clientTest = new Socket("localhost", port);
			clientTest.isConnected();
		} catch (IOException e) {
			serverClosed = true;
		}
		assertFalse(serverClosed);
		
		MockCommunicationClientStrategy mockCCStrategy = new MockCommunicationClientStrategy();
		ICommunicationClientStrategy communicationClientStrategy = new IdentifySocketStrategyDecorator(applicationName, mockCCStrategy );
		socketClient = new SocketClient( applicationName, "localhost", port, communicationClientStrategy);
		socketClient.start();
		
		Thread.sleep(500);
		
		serverClosed = false;
		try {
			Socket clientTest = new Socket("localhost", port);
			clientTest.isConnected();
		} catch (IOException e) {
			serverClosed = true;
		}
		assertFalse(serverClosed);
		
		assertTrue( mockCCStrategy.isReaded() );
		assertTrue( mockCCStrategy.isWrited() );
		
		socketThreadPool.shutDown();
		Thread.sleep(100);
		socketClient.closeConnection();
		System.out.println("Fuori dal join");
	}
	
	public void testIdentifyFalse() throws Exception {
		String applicationName = "applicationName";
		
		socketThreadPool = new SocketJobThreadPool( applicationName , port);
		socketThreadPool.start();
		
		Thread.sleep(200);
		
		boolean serverClosed = false;
		try {
			Socket clientTest = new Socket("localhost", port);
			clientTest.isConnected();
		} catch (IOException e) {
			serverClosed = true;
		}
		assertFalse(serverClosed);
		
		MockCommunicationClientStrategy mockCCStrategy = new MockCommunicationClientStrategy();
		ICommunicationClientStrategy communicationClientStrategy = new IdentifySocketStrategyDecorator(applicationName + "ERROR", mockCCStrategy );
		socketClient = new SocketClient( applicationName, "localhost", port, communicationClientStrategy);
		socketClient.start();
		
		Thread.sleep(500);
		
		serverClosed = false;
		try {
			Socket clientTest = new Socket("localhost", port);
			clientTest.isConnected();
		} catch (IOException e) {
			serverClosed = true;
		}
		assertFalse(serverClosed);
		
		assertFalse( mockCCStrategy.isReaded() );
		assertFalse( mockCCStrategy.isWrited() );
		
		System.out.println("Fuori dal join");
		socketClient.closeConnection();
	}
	
	
	class MockCommunicationClientStrategy implements ICommunicationClientStrategy {

		private static final long serialVersionUID = 1L;
		private boolean readed = false;
		private boolean writed = false;

		public String read() throws IOException {
			setReaded(true);
			System.out.println("read: mock read");
			return "mock read";
		}

		public void write(String message) {
			setWrited(true);
			System.out.println("write:" + message);
		}

		public boolean isReaded() {
			return readed;
		}

		public void setReaded(boolean readed) {
			this.readed = readed;
		}

		public boolean isWrited() {
			return writed;
		}

		public void setWrited(boolean writed) {
			this.writed = writed;
		}
		
		
		
	}
	
}
