package com.jpattern.jobexecutor.socket;

import java.io.IOException;
import java.net.Socket;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.mock.MockJobThreadPool;
import com.jpattern.jobexecutor.socket.GenericConsoleComunicationClientStrategy;
import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;
import com.jpattern.jobexecutor.socket.ICommunicatorManager;
import com.jpattern.jobexecutor.socket.JobThreadPoolCommunicatorManager;
import com.jpattern.jobexecutor.socket.SocketClient;
import com.jpattern.jobexecutor.socket.SocketJobThreadPool;
import com.jpattern.jobexecutor.socket.SocketServer;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class ShutdownStrategyTest extends BaseTest {

	private String socketName = "socketServer";
	private int port = 12345;
	private SocketServer socketServer;
	private SocketClient socketClient;
	private SocketJobThreadPool socketThreadPool;
	
	protected void setUp() throws Exception {
		super.setUp();
		Thread.sleep(250);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		if (socketThreadPool != null) {
			socketThreadPool.shutDown();
			socketThreadPool.closeAdminSocket();
		}
		
		try {
			System.out.println("TearDown - closing server connection...");
			if (socketServer != null) {
				socketServer.closeConnection();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("TearDown - closing client connection...");
			if (socketClient != null) {
				socketClient.closeConnection();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(500);
	}
	
	public void testStartStrategy() throws Exception {
		
		MockJobThreadPool jobThreadPool = new MockJobThreadPool();
		ICommunicatorManager communicator = new JobThreadPoolCommunicatorManager(jobThreadPool);
		socketServer = new SocketServer(socketName, port, communicator);
		socketServer.start();
		
		Thread.sleep(500);
		
		ICommunicationClientStrategy communicationClientStrategy = new GenericConsoleComunicationClientStrategy( ICommandExecutorHandler.COMMAND_SHUTDOWN );
		socketClient = new SocketClient("clientSocket", "localhost", port, communicationClientStrategy);
		socketClient.start();
		
		Thread.sleep(500);
		assertEquals( "SHUTDOWN" , jobThreadPool.lastExecuted() );
		Thread.sleep(2000);
		assertFalse( socketClient.isAlive() );
		assertTrue( socketServer.isAlive() );
		
		socketServer.join(2000);
		System.out.println("Fuori dal join");
	}
	
	
	public void testStartStrategy2() throws Exception {
		
		socketThreadPool = new SocketJobThreadPool("" , port);
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
		
		ICommunicationClientStrategy communicationClientStrategy = new GenericConsoleComunicationClientStrategy( ICommandExecutorHandler.COMMAND_SHUTDOWN );
		socketClient = new SocketClient("clientSocket", "localhost", port, communicationClientStrategy);
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
		
		System.out.println("Fuori dal join");
		
	}
}
