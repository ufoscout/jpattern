package com.jpattern.jobexecutor.socket;

import java.util.ArrayList;
import java.util.List;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.iostream.ICommunicationChannel;
import com.jpattern.jobexecutor.mock.MockCommunicationChannelReadList;
import com.jpattern.jobexecutor.mock.MockJobThreadPool;
import com.jpattern.jobexecutor.socket.ICommunicatorManager;
import com.jpattern.jobexecutor.socket.JobThreadPoolCommunicatorManager;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class JobThreadPoolCommunicatorManagerTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
		Thread.sleep(250);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Thread.sleep(250);
	}
	
	public void testCommunicator1() throws Exception {
		MockJobThreadPool mockPool = new MockJobThreadPool();
		ICommunicatorManager communicationManager = new JobThreadPoolCommunicatorManager(mockPool);
		
		
		List<String> commands = new ArrayList<String>();
		commands.add( ICommandExecutorHandler.COMMAND_START );
		commands.add( ICommandExecutorHandler.COMMAND_CLOSE_CONSOLE );
		
		ICommunicationChannel communicationChannel = new MockCommunicationChannelReadList(commands);
		communicationManager.execute(communicationChannel);

		assertEquals( "START" , mockPool.lastExecuted() );
		
	}

	public void testCommunicator2() throws Exception {
		MockJobThreadPool mockPool = new MockJobThreadPool();
		ICommunicatorManager communicationManager = new JobThreadPoolCommunicatorManager(mockPool);
		
		
		List<String> commands = new ArrayList<String>();
		commands.add( ICommandExecutorHandler.COMMAND_START );
		commands.add( ICommandExecutorHandler.COMMAND_SHUTDOWN );
		
		ICommunicationChannel communicationChannel = new MockCommunicationChannelReadList(commands);
		communicationManager.execute(communicationChannel);
		
		Thread.sleep(50);
		assertEquals( "START" , mockPool.lastExecuted() );
		
	}
	
	public void testCommunicator3() throws Exception {
		MockJobThreadPool mockPool = new MockJobThreadPool();
		ICommunicatorManager communicationManager = new JobThreadPoolCommunicatorManager(mockPool);
		
		
		List<String> commands = new ArrayList<String>();
		commands.add( ICommandExecutorHandler.COMMAND_IDENTITY_APPLICATION);
		commands.add( ICommandExecutorHandler.COMMAND_SHUTDOWN );
		
		ICommunicationChannel communicationChannel = new MockCommunicationChannelReadList(commands);
		communicationManager.execute(communicationChannel);
		
		Thread.sleep(50);
		assertEquals( "SHUTDOWN" , mockPool.lastExecuted() );
		
	}
}
