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
public class StressStartStopStrategyTest extends BaseTest {

	private String socketName = "socketServer";
	private int port = 12345;
	private SocketServer socketServer;
	private SocketClient socketClient;
	private String applicationName;
	
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
		
		applicationName = "appl";
		
		MockJobThreadPool jobThreadPool = new MockJobThreadPool(applicationName);
		ICommunicatorManager communicator = new JobThreadPoolCommunicatorManager(jobThreadPool);
		socketServer = new SocketServer(socketName, port, communicator);
		socketServer.start();
		Thread.sleep(200);
		
		Thread thread = null;
		for ( int i = 0; i<100 ; i++ ) {
			
			ICommunicationClientStrategy strategyStart = new GenericConsoleComunicationClientStrategy( ICommandExecutorHandler.COMMAND_START );
			ICommunicationClientStrategy strategyStop = new GenericConsoleComunicationClientStrategy( ICommandExecutorHandler.COMMAND_STOP);
			
			new Thread( new ClientTest( strategyStart ) ).start();
			thread = new Thread( new ClientTest( strategyStop ) );
			thread.start();
			Thread.sleep(20);
		}
		
		if (thread != null) {
			thread.join( );
		}
		
		Thread.sleep(500);
		//assertEquals( "STOP" , jobThreadPool.lastExecuted() );
		//assertFalse( socketClient.isAlive() );
		assertTrue( socketServer.isAlive() );
		
		socketServer.join(2000);
		System.out.println("Fuori dal join");
		
	}
	
	class ClientTest implements Runnable {
		
		private ICommunicationClientStrategy communicationClientStrategy;

		public ClientTest( ICommunicationClientStrategy communicationClientStrategy ) {
			this.communicationClientStrategy = communicationClientStrategy;
		}
		
		public void run() {
			ICommunicationClientStrategy communicationClientStrategy = new IdentifySocketStrategyDecorator(applicationName, this.communicationClientStrategy );
			SocketClient socketClient = new SocketClient("clientSocket", "localhost", port, communicationClientStrategy);
			socketClient.start();
		}
		
	}
	
}
