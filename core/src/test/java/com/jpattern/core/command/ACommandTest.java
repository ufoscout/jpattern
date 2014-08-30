package com.jpattern.core.command;


import java.util.ArrayList;
import java.util.List;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ACommandTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCommandGood1() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		ACommand<IProvider> command = new CommandGood(Good1CommandResult);
		ACommandResult result = command.exec(getProvider());
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertTrue( result.isValid() );
		assertTrue( result.getErrorMessages().size() == 0 );
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertFalse( Good1CommandResult.isRollbackExecuted() );
		
		assertTrue(command.rollback(getProvider()).isValid());
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertTrue( Good1CommandResult.isRollbackExecuted() );
	}
	
	public void testCommandGood2() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		ACommand<IProvider> command = new CommandGood(Good1CommandResult);
		ACommandResult result = command.exec(getProvider());
		result.waitExecutionEnd();
		assertTrue(result.isExecutionEnd());
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertTrue( result.isValid() );
		assertTrue( result.getErrorMessages().size() == 0 );
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertFalse( Good1CommandResult.isRollbackExecuted() );
		
		ACommandResult rollbackResult = command.rollback(getProvider());
		rollbackResult.waitExecutionEnd();
		assertTrue( rollbackResult.isExecutionEnd());
		assertTrue(	rollbackResult.isValid());
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertTrue( Good1CommandResult.isRollbackExecuted() );
	}
	
	public void testCommandGoodAsynch1() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		ACommand<IProvider> command = new CommandGood(Good1CommandResult);
		ACommandResult result = command.exec(getProvider(), new AsyncCommandExecutor());
		result.waitExecutionEnd();
		assertTrue(result.isExecutionEnd());
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertTrue( result.isValid() );
		assertTrue( result.getErrorMessages().size() == 0 );
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertFalse( Good1CommandResult.isRollbackExecuted() );
		
		ACommandResult rollbackResult = command.rollback(getProvider(), new AsyncCommandExecutor());
		rollbackResult.waitExecutionEnd();
		assertTrue( rollbackResult.isExecutionEnd());
		assertTrue(	rollbackResult.isValid());
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertTrue( Good1CommandResult.isRollbackExecuted() );
	}
	
	public void testCommandGoodAsynch2() throws Exception {
		
		List<String> executions = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		ACommand<IProvider> command = new MockDoNothingAsyncCommand(executions, "", 500, buffer, 1);
		ACommandResult result = command.exec(getProvider(), new AsyncCommandExecutor());
		result.waitExecutionEnd();
		assertTrue(result.isExecutionEnd());
		assertTrue( buffer.toString().equals("1_") );
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertTrue( result.isValid() );
		assertTrue( result.getErrorMessages().size() == 0 );
		
		ACommandResult rollbackResult = command.rollback(getProvider(), new AsyncCommandExecutor());
		rollbackResult.waitExecutionEnd();
		assertTrue(	rollbackResult.isValid());
		assertTrue(	rollbackResult.isExecutionEnd());
		
		assertTrue( buffer.toString().equals("1_1_rollback_") );
	}
	
	public void testCommandBad1() throws Exception {
		
		SingleCommandResult bad1CommandResult = new SingleCommandResult("Bad1CommandResult");
		
		int howManyErrors = 4;
		ACommand<IProvider> command = new CommandBad(howManyErrors, bad1CommandResult);
		ACommandResult result = command.exec(getProvider());
		result.waitExecutionEnd();
		assertTrue(result.isExecutionEnd());
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertFalse( result.isValid() );
		assertTrue( result.getErrorMessages().size() == howManyErrors );
		
		for (IErrorMessage errorMessage : result.getErrorMessages()) {
			System.out.println( "error name:    " + errorMessage.getName() );
			System.out.println( "error message: " + errorMessage.getMessage() );
			
			for ( String parameter : errorMessage.getParameters() ) {
				System.out.println( "error parameter: " + parameter );
			}
		}
		
		assertTrue( bad1CommandResult.isExecuted() );
		assertFalse( bad1CommandResult.isRollbackExecuted() );
		
		ACommandResult rollbackResult = command.rollback(getProvider());
		rollbackResult.waitExecutionEnd();
		assertTrue(	rollbackResult.isValid());
		assertTrue(	rollbackResult.isExecutionEnd());
		
		assertTrue( bad1CommandResult.isExecuted() );
		assertFalse( bad1CommandResult.isRollbackExecuted() );
	}

	public void testCommandBadAsynch() throws Exception {
		
		SingleCommandResult bad1CommandResult = new SingleCommandResult("Bad1CommandResult");
		
		int howManyErrors = 4;
		ACommand<IProvider> command = new CommandBad(howManyErrors, bad1CommandResult);
		ACommandResult result = command.exec(getProvider(), new AsyncCommandExecutor());
		result.waitExecutionEnd();
		assertTrue(result.isExecutionEnd());
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertFalse( result.isValid() );
		assertTrue( result.getErrorMessages().size() == howManyErrors );
		
		for (IErrorMessage errorMessage : result.getErrorMessages()) {
			System.out.println( "error name:    " + errorMessage.getName() );
			System.out.println( "error message: " + errorMessage.getMessage() );
			
			for ( String parameter : errorMessage.getParameters() ) {
				System.out.println( "error parameter: " + parameter );
			}
		}
		
		assertTrue( bad1CommandResult.isExecuted() );
		assertFalse( bad1CommandResult.isRollbackExecuted() );
		
		ACommandResult rollbackResult = command.rollback(getProvider(), new AsyncCommandExecutor());
		rollbackResult.waitExecutionEnd();
		assertTrue(	rollbackResult.isValid());
		assertTrue(	rollbackResult.isExecutionEnd());
		
		assertTrue( bad1CommandResult.isExecuted() );
		assertFalse( bad1CommandResult.isRollbackExecuted() );
	}
	
}
