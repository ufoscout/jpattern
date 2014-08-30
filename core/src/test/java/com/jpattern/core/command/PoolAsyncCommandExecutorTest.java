package com.jpattern.core.command;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.service.log.ILoggerService;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public class PoolAsyncCommandExecutorTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testQueue() throws Exception {
		List<String> executions = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		StringBuffer expectedExecOrder = new StringBuffer();
		
		int howMany = 5;
		
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		for (int i=0; i<howMany; i++) {
			command.addCommand( new MockDoNothingAsyncCommand(executions, "doNothing" + (i+1), new Random().nextInt(500), buffer, i));
			expectedExecOrder.append(i + "_");
		}
		
		int maxWaitingCommands = 5;
		int maxConcurrentThreads = 5;
		ILoggerService loggerService = getProvider().getLoggerService();
		PoolAsyncCommandExecutor pool = new PoolAsyncCommandExecutor("pool1", maxWaitingCommands, maxConcurrentThreads, loggerService);
		pool.startPool();
		ACommandResult result = command.exec(getProvider(), pool);
//		Thread.sleep(100);
//		pool.stopPool();
		result.waitExecutionEnd();
		assertTrue(result.isValid());
		assertEquals(howMany, executions.size());
		assertTrue(result.isExecutionEnd());
		System.out.println("Execution order: " + buffer.toString());
		assertFalse( buffer.toString().isEmpty() );
		assertEquals( expectedExecOrder.toString() , buffer.toString() );
		
	}
	
	public void testQueueCuncurrent() throws Exception {
		List<String> executions = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		StringBuffer expectedExecOrder = new StringBuffer();
		
		int howMany = 50;
		
		CommandChain<IProvider> command = new CommandChain<IProvider>(new ConcurrentCommandChainStrategy());
		for (int i=0; i<howMany; i++) {
			command.addCommand( new MockDoNothingAsyncCommand(executions, "doNothing" + (i+1), new Random().nextInt(500), buffer, i));
			expectedExecOrder.append(i + "_");
		}
		
		int maxWaitingCommands = 5;
		int maxConcurrentThreads = 5;
		ILoggerService loggerService = getProvider().getLoggerService();
		PoolAsyncCommandExecutor pool = new PoolAsyncCommandExecutor("pool1", maxWaitingCommands, maxConcurrentThreads, loggerService);
		pool.startPool();
		ACommandResult result = command.exec(getProvider(), pool);
		result.waitExecutionEnd();
		assertTrue(result.isValid());
		assertEquals(howMany, executions.size());
		assertTrue(result.isExecutionEnd());
		
		System.out.println("Execution order: " + buffer.toString());
		assertFalse( buffer.toString().isEmpty() );
		for (int i=0 ; i<howMany; i++) {
			assertTrue( buffer.toString().contains( i + "_" ) );
		}
		assertFalse( expectedExecOrder.toString().equals(buffer.toString()) );
	
		ACommandResult rollbackResult = command.rollback(getProvider(), pool);
		rollbackResult.waitExecutionEnd();
		assertTrue(rollbackResult.isValid());
		assertEquals(howMany, executions.size());
		assertTrue(rollbackResult.isExecutionEnd());
		
		for (int i=0 ; i<howMany; i++) {
			assertTrue( buffer.toString().contains( i + "_rollback_" ) );
		}
		
	}
	
	public void testQueueCuncurrentWithNullProvider() throws Exception {
		List<String> executions = new Vector<String>();
		StringBuffer buffer = new StringBuffer();
		
		int howMany = 25;
		
		CommandChain<IProvider> command = new CommandChain<IProvider>(new ConcurrentCommandChainStrategy());
		for (int i=0; i<howMany; i++) {
			command.addCommand( new MockDoNothingAsyncCommand(executions, "doNothing" + (i+1), new Random().nextInt(500), buffer, i ) );
		}
		
		int maxWaitingCommands = 5;
		int maxConcurrentThreads = 5;
		ILoggerService loggerService = getProvider().getLoggerService();
		PoolAsyncCommandExecutor pool = new PoolAsyncCommandExecutor("pool1", maxWaitingCommands, maxConcurrentThreads, loggerService);
		pool.startPool();
		
		//Execution of the command chain without IProvider. The command chain must throws exception but the threads must not be stuck
		ACommandResult result = command.exec(null, pool);
//		Thread.sleep(100);
//		pool.stopPool();
		result.waitExecutionEnd();
		assertFalse(result.isValid());
		assertEquals(0, executions.size());
		assertEquals(buffer.length(), executions.size());
		assertTrue(result.isExecutionEnd());
		
	}

}
