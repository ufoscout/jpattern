package com.jpattern.jobexecutor.socket;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.mock.MockJobThreadPool;
import com.jpattern.jobexecutor.socket.GenericConsoleComunicationClientStrategy;
import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;
import com.jpattern.jobexecutor.socket.ICommunicatorManager;
import com.jpattern.jobexecutor.socket.IdentifySocketStrategyDecorator;
import com.jpattern.jobexecutor.socket.JobThreadPoolCommunicatorManager;
import com.jpattern.jobexecutor.socket.SocketClient;
import com.jpattern.jobexecutor.socket.SocketServer;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class StopStrategyTest extends BaseTest {

	private String socketName = "socketServer";
	private int port = 12345;
	private SocketServer socketServer;
	private SocketClient socketClient;
	
	protected void setUp() throws Exception {
		super.setUp();
		Thread.sleep(250);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
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
		
		String applicationName = "appl";
		
		MockJobThreadPool jobThreadPool = new MockJobThreadPool(applicationName);
		ICommunicatorManager communicator = new JobThreadPoolCommunicatorManager(jobThreadPool);
		socketServer = new SocketServer(socketName, port, communicator);
		socketServer.start();
		Thread.sleep(200);
		
		ICommunicationClientStrategy strategy = new GenericConsoleComunicationClientStrategy( ICommandExecutorHandler.COMMAND_STOP);
		ICommunicationClientStrategy communicationClientStrategy = new IdentifySocketStrategyDecorator(applicationName, strategy  );
		socketClient = new SocketClient("clientSocket", "localhost", port, communicationClientStrategy);
		socketClient.start();
		
		Thread.sleep(500);
		assertEquals( "STOP" , jobThreadPool.lastExecuted() );
		assertFalse( socketClient.isAlive() );
		assertTrue( socketServer.isAlive() );
		
		socketServer.join(2000);
		System.out.println("Fuori dal join");
		
	}
	
}
