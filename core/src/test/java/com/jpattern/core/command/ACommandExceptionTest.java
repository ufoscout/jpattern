package com.jpattern.core.command;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/apr/2011
 */
public class ACommandExceptionTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testExecException() throws Exception {
		SingleCommandResult goodCommandResult1 = new SingleCommandResult("good1");
		SingleCommandResult goodCommandResult2 = new SingleCommandResult("good2");
		SingleCommandResult exceptionCommandResult = new SingleCommandResult("exception");
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(goodCommandResult1) );
		command.addCommand( new ExceptionCommand(exceptionCommandResult, true, false) );
		command.addCommand( new CommandGood(goodCommandResult2) );
		ACommandResult commandResult = command.exec(getProvider());
		assertFalse(commandResult.isValid());
		
		assertTrue(goodCommandResult1.isExecuted());
		assertTrue(goodCommandResult1.isRollbackExecuted());
		assertTrue(exceptionCommandResult.isExecuted());
		assertFalse(exceptionCommandResult.isRollbackExecuted());
		assertFalse(goodCommandResult2.isExecuted());
		assertFalse(goodCommandResult2.isRollbackExecuted());
	}
	
	public void testRollBackExeception() throws Exception {
		SingleCommandResult goodCommandResult1 = new SingleCommandResult("good1");
		SingleCommandResult goodCommandResult2 = new SingleCommandResult("good2");
		SingleCommandResult exceptionExecCommandResult = new SingleCommandResult("exec");
		SingleCommandResult exceptionRollBackCommandResult = new SingleCommandResult("rollback");
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(goodCommandResult1) );
		command.addCommand( new ExceptionCommand(exceptionRollBackCommandResult, false, true) );
		command.addCommand( new ExceptionCommand(exceptionExecCommandResult, true, false) );
		command.addCommand( new CommandGood(goodCommandResult2) );
		ACommandResult commandResult = command.exec(getProvider());
		assertFalse(commandResult.isValid());
		
		assertTrue(goodCommandResult1.isExecuted());
		assertTrue(goodCommandResult1.isRollbackExecuted());
		assertTrue(exceptionRollBackCommandResult.isExecuted());
		assertTrue(exceptionRollBackCommandResult.isRollbackExecuted());
		assertTrue(exceptionExecCommandResult.isExecuted());
		assertFalse(exceptionExecCommandResult.isRollbackExecuted());
		assertFalse(goodCommandResult2.isExecuted());
		assertFalse(goodCommandResult2.isRollbackExecuted());
	}
	
	
	public void testExecExeceptionAsync() throws Exception {
		SingleCommandResult goodCommandResult1 = new SingleCommandResult("good1");
		SingleCommandResult goodCommandResult2 = new SingleCommandResult("good2");
		SingleCommandResult exceptionCommandResult = new SingleCommandResult("exception");
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(goodCommandResult1) );
		command.addCommand( new ExceptionCommand(exceptionCommandResult, true, false) );
		command.addCommand( new CommandGood(goodCommandResult2) );
		ACommandResult commandResult = command.exec(getProvider(), new AsyncCommandExecutor());
		commandResult.waitExecutionEnd();
		assertFalse(commandResult.isValid());
		
		assertTrue(goodCommandResult1.isExecuted());
		assertTrue(goodCommandResult1.isRollbackExecuted());
		assertTrue(exceptionCommandResult.isExecuted());
		assertFalse(exceptionCommandResult.isRollbackExecuted());
		assertFalse(goodCommandResult2.isExecuted());
		assertFalse(goodCommandResult2.isRollbackExecuted());
	}
	
	public void testRollBackExeceptionAsync() throws Exception {
		SingleCommandResult goodCommandResult1 = new SingleCommandResult("good1");
		SingleCommandResult goodCommandResult2 = new SingleCommandResult("good2");
		SingleCommandResult exceptionExecCommandResult = new SingleCommandResult("exec");
		SingleCommandResult exceptionRollBackCommandResult = new SingleCommandResult("rollback");
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(goodCommandResult1) );
		command.addCommand( new ExceptionCommand(exceptionRollBackCommandResult, true, true) );
		command.addCommand( new ExceptionCommand(exceptionExecCommandResult, true, false) );
		command.addCommand( new CommandGood(goodCommandResult2) );
		ACommandResult commandResult = command.exec( getProvider(), new AsyncCommandExecutor() );
		commandResult.waitExecutionEnd();
		assertFalse(commandResult.isValid());

		Thread.sleep(500);
		
		assertTrue(goodCommandResult1.isExecuted());
		assertTrue(goodCommandResult1.isRollbackExecuted());
		assertTrue(exceptionRollBackCommandResult.isExecuted());
		assertFalse(exceptionRollBackCommandResult.isRollbackExecuted());
		assertFalse(exceptionExecCommandResult.isExecuted());
		assertFalse(exceptionExecCommandResult.isRollbackExecuted());
		assertFalse(goodCommandResult2.isExecuted());
		assertFalse(goodCommandResult2.isRollbackExecuted());
	}

	
	public void testExecExeceptionAsyncCuncurrent() throws Exception {
		SingleCommandResult goodCommandResult1 = new SingleCommandResult("good1");
		SingleCommandResult goodCommandResult2 = new SingleCommandResult("good2");
		SingleCommandResult exceptionCommandResult = new SingleCommandResult("exception");
		CommandChain<IProvider> command = new CommandChain<IProvider>(new ConcurrentCommandChainStrategy());
		command.addCommand( new CommandGood(goodCommandResult1));
		command.addCommand( new ExceptionCommand(exceptionCommandResult, true, false));
		command.addCommand( new CommandGood(goodCommandResult2));
		ACommandResult commandResult = command.exec(getProvider(), new AsyncCommandExecutor());
		commandResult.waitExecutionEnd();
		assertFalse(commandResult.isValid());
		
		assertTrue(goodCommandResult1.isExecuted());
		assertFalse(goodCommandResult1.isRollbackExecuted());
		assertTrue(exceptionCommandResult.isExecuted());
		assertFalse(exceptionCommandResult.isRollbackExecuted());
		assertTrue(goodCommandResult2.isExecuted());
		assertFalse(goodCommandResult2.isRollbackExecuted());
	}
	
	public void testRollBackExeceptionAsyncConcurrent() throws Exception {
		SingleCommandResult goodCommandResult1 = new SingleCommandResult("good1");
		SingleCommandResult goodCommandResult2 = new SingleCommandResult("good2");
		SingleCommandResult exceptionExecCommandResult = new SingleCommandResult("exec");
		SingleCommandResult exceptionRollBackCommandResult = new SingleCommandResult("rollback");
		CommandChain<IProvider> command = new CommandChain<IProvider>(new ConcurrentCommandChainStrategy());
		command.addCommand( new CommandGood(goodCommandResult1));
		command.addCommand( new ExceptionCommand(exceptionRollBackCommandResult, true, true));
		command.addCommand( new ExceptionCommand(exceptionExecCommandResult, true, false));
		command.addCommand( new CommandGood(goodCommandResult2));
		ACommandResult commandResult = command.exec( getProvider(), new AsyncCommandExecutor() );
		commandResult.waitExecutionEnd();
		assertFalse(commandResult.isValid());

		Thread.sleep(500);
		
		assertTrue(goodCommandResult1.isExecuted());
		assertFalse(goodCommandResult1.isRollbackExecuted());
		assertTrue(exceptionRollBackCommandResult.isExecuted());
		assertFalse(exceptionRollBackCommandResult.isRollbackExecuted());
		assertTrue(exceptionExecCommandResult.isExecuted());
		assertFalse(exceptionExecCommandResult.isRollbackExecuted());
		assertTrue(goodCommandResult2.isExecuted());
		assertFalse(goodCommandResult2.isRollbackExecuted());
	}
}
