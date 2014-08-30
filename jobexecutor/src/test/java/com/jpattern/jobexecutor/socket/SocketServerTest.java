package com.jpattern.jobexecutor.socket;

import java.util.ArrayList;
import java.util.List;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.mock.MockCommunicationClientStrategyReadList;
import com.jpattern.jobexecutor.mock.MockJobThreadPool;
import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;
import com.jpattern.jobexecutor.socket.ICommunicatorManager;
import com.jpattern.jobexecutor.socket.JobThreadPoolCommunicatorManager;
import com.jpattern.jobexecutor.socket.SocketClient;
import com.jpattern.jobexecutor.socket.SocketServer;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class SocketServerTest extends BaseTest {

	private String socketName = "socketServer";
	private int port = 12345;
	private SocketServer socketServer;
	
	protected void setUp() throws Exception {
		super.setUp();
		Thread.sleep(250);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		try {
			System.out.println("TearDown - closing connection...");
			if (socketServer != null) {
				socketServer.closeConnection();
			}
		}
		catch (Exception e) {
//			e.printStackTrace();
		}
		Thread.sleep(500);
	}
	
	public void testServerSocket1() throws Exception {
		
		
		MockJobThreadPool jobThreadPool = new MockJobThreadPool();
		ICommunicatorManager communicator = new JobThreadPoolCommunicatorManager(jobThreadPool);
		socketServer = new SocketServer(socketName, port, communicator);
		socketServer.start();
		
		socketServer.join(250);
		
		//se il socket è attivo questa chiamata va in eccezione
		SocketServer socketServer2 = new SocketServer(socketName, port, communicator);
		socketServer2.start();
		Thread.sleep(250);
		assertFalse( socketServer2.isAlive() );
		assertTrue( socketServer.isAlive() );
		
		System.out.println("Fuori dal join");
		
	}
	
	
	public void testServerSocket2() throws Exception {
		
		MockJobThreadPool jobThreadPool = new MockJobThreadPool();
		ICommunicatorManager communicator = new JobThreadPoolCommunicatorManager(jobThreadPool);
		socketServer = new SocketServer(socketName, port, communicator);
		socketServer.start();
		
		Thread.sleep(50);
		
		List<String> readList = new ArrayList<String>();
		readList.add( ICommandExecutorHandler.COMMAND_START);
		ICommunicationClientStrategy communicationClientStrategy = new MockCommunicationClientStrategyReadList( readList ); 
		SocketClient socketClient = new SocketClient("socketClient", "localhost", port, communicationClientStrategy);
		socketClient.start();
		Thread.sleep(500);
		assertEquals( "START" , jobThreadPool.lastExecuted() );
		
		
		readList.add( ICommandExecutorHandler.COMMAND_STOP);
		socketClient = new SocketClient("socketClient", "localhost", port, communicationClientStrategy);
		socketClient.start();
		Thread.sleep(500);
		assertEquals( "STOP" , jobThreadPool.lastExecuted() );
		
		readList.add( ICommandExecutorHandler.COMMAND_SHUTDOWN);
		socketClient = new SocketClient("socketClient", "localhost", port, communicationClientStrategy);
		socketClient.start();
		Thread.sleep(500);
		assertEquals( "SHUTDOWN" , jobThreadPool.lastExecuted() );
		
		socketServer.join(500);
		System.out.println("Fuori dal join");
		assertTrue( socketServer.isAlive() );
		Thread.sleep(1000);
		assertFalse( socketClient.isAlive() );
		
	}
	
	public void testServerSocket3() throws Exception {
		
		
		MockJobThreadPool jobThreadPool = new MockJobThreadPool();
		ICommunicatorManager communicator = new JobThreadPoolCommunicatorManager(jobThreadPool);
		socketServer = new SocketServer(socketName, port, communicator);
		socketServer.start();
		
		Thread.sleep(50);
		
		// ------- BEGIN:  CREO PRIMO CLIENT
		List<String> readList = new ArrayList<String>();
		readList.add( ICommandExecutorHandler.COMMAND_CLOSE_CONSOLE);
		ICommunicationClientStrategy communicationClientStrategy = new MockCommunicationClientStrategyReadList( readList ); 
		SocketClient socketClient = new SocketClient("socketClient", "localhost", port, communicationClientStrategy);
		socketClient.start();
		Thread.sleep(500);
		assertNull( jobThreadPool.lastExecuted() );
		assertFalse( socketClient.isAlive() );
		// ------- END:  CREO PRIMO CLIENT

		// ------- BEGIN:  CREO SECONDO CLIENT
		List<String> readList2 = new ArrayList<String>();
		readList2.add( ICommandExecutorHandler.COMMAND_CLOSE_CONSOLE);
		ICommunicationClientStrategy communicationClientStrategy2 = new MockCommunicationClientStrategyReadList( readList2 ); 
		SocketClient socketClient2 = new SocketClient("socketClient", "localhost", port, communicationClientStrategy2);
		socketClient2.start();
		Thread.sleep(500);
		assertFalse( socketClient2.isAlive() );
		// ------- END:  CREO SECONDO CLIENT
		
		// ------- BEGIN:  CREO TERZO CLIENT
		List<String> readList3 = new ArrayList<String>();
		readList3.add( ICommandExecutorHandler.COMMAND_CLOSE_CONSOLE);
		ICommunicationClientStrategy communicationClientStrategy3 = new MockCommunicationClientStrategyReadList( readList3 ); 
		SocketClient socketClient3 = new SocketClient("socketClient", "localhost", port, communicationClientStrategy3);
		socketClient3.start();
		Thread.sleep(500);
		assertFalse( socketClient2.isAlive() );
		assertFalse( socketClient3.isAlive() );
		// ------- END: CHIUDO CONSOLE SU SECONDO E TERZO CLIENT
		
		
		socketServer.join(500);
		System.out.println("Fuori dal join");
		assertFalse( socketClient.isAlive() );
		assertTrue( socketServer.isAlive() );
		
	}
	
}
