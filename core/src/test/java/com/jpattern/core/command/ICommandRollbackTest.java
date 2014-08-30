package com.jpattern.core.command;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ICommandRollbackTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAutoRollBack() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		SingleCommandResult Good2CommandResult = new SingleCommandResult("Good2CommandResult");
		SingleCommandResult Bad1CommandResult = new SingleCommandResult("Bad1CommandResult");
		SingleCommandResult Bad2CommandResult = new SingleCommandResult("Bad2CommandResult");
		
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(Good1CommandResult) );
		command.addCommand( new CommandBad(2, Bad1CommandResult) );
		command.addCommand( new CommandGood(Good2CommandResult) );
		command.addCommand( new CommandBad(3, Bad2CommandResult) );
		ACommandResult result = command.exec(getProvider());
		result.waitExecutionEnd();
		assertTrue( result.isExecutionEnd() );
		
//		ACommandResult autoRollbackResult = command.rollback(result);
//		assertTrue( autoRollbackResult.isExecutionEnd() );
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertFalse( result.isValid() );
//		assertTrue( autoRollbackResult.isValid() );
		assertEquals( 2 , result.getErrorMessages().size() );
		
		for (IErrorMessage errorMessage : result.getErrorMessages()) {
			System.out.println( "error name:    " + errorMessage.getName() );
			System.out.println( "error message: " + errorMessage.getName() );
			
			for ( String parameter : errorMessage.getParameters() ) {
				System.out.println( "error parameter: " + parameter );
			}
		}
		
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertTrue( Good1CommandResult.isRollbackExecuted() );
		assertTrue( Bad1CommandResult.isExecuted() );
		assertFalse( Bad1CommandResult.isRollbackExecuted() );
		assertFalse( Good2CommandResult.isExecuted() );
		assertFalse( Good2CommandResult.isRollbackExecuted() );
		assertFalse( Bad2CommandResult.isExecuted() );
		assertFalse( Bad2CommandResult.isRollbackExecuted() );
		
	}
	
	
	public void testAutoRollBackFailed() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		SingleCommandResult Good2CommandResult = new SingleCommandResult("Good2CommandResult");
		SingleCommandResult Good3CommandResult = new SingleCommandResult("Good3CommandResult");
		SingleCommandResult Good4CommandResult = new SingleCommandResult("Good4CommandResult");
		SingleCommandResult Good5CommandResult = new SingleCommandResult("Good5CommandResult");
		SingleCommandResult Bad1CommandResult = new SingleCommandResult("Bad1CommandResult");
		
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(Good1CommandResult) );
		command.addCommand( new CommandGood(Good2CommandResult) );
		command.addCommand( new CommandGoodRollBackFailed(2, Good3CommandResult) );
		command.addCommand( new CommandGood(Good4CommandResult) );
		command.addCommand( new CommandGoodRollBackFailed(3, Good5CommandResult) );
		command.addCommand( new CommandBad(3, Bad1CommandResult) );
		ACommandResult result = command.exec(getProvider());
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertFalse( result.isValid() );
		assertEquals( 8 , result.getErrorMessages().size() );
		
		for (IErrorMessage errorMessage : result.getErrorMessages()) {
			System.out.println( "error name:    " + errorMessage.getName() );
			System.out.println( "error message: " + errorMessage.getName() );
			
			for ( String parameter : errorMessage.getParameters() ) {
				System.out.println( "error parameter: " + parameter );
			}
		}
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertTrue( Good1CommandResult.isRollbackExecuted() );
		assertTrue( Good2CommandResult.isExecuted() );
		assertTrue( Good2CommandResult.isRollbackExecuted() );
		assertTrue( Good3CommandResult.isExecuted() );
		assertTrue( Good3CommandResult.isRollbackExecuted() );
		assertTrue( Good4CommandResult.isExecuted() );
		assertTrue( Good4CommandResult.isRollbackExecuted() );
		assertTrue( Good5CommandResult.isExecuted() );
		assertTrue( Good5CommandResult.isRollbackExecuted() );
		assertTrue( Bad1CommandResult.isExecuted() );
		assertFalse( Bad1CommandResult.isRollbackExecuted() );
		
	}
	  
	
	public void testAutoRollBack2() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		SingleCommandResult Good2CommandResult = new SingleCommandResult("Good2CommandResult");
		
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(Good1CommandResult) );
		command.addCommand( new CommandGood(Good2CommandResult) );
		ACommandResult result = command.exec(getProvider());
		result.waitExecutionEnd();
		assertTrue( result.isExecutionEnd() );
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertTrue( result.isValid() );
		
		for (IErrorMessage errorMessage : result.getErrorMessages()) {
			System.out.println( "error name:    " + errorMessage.getName() );
			System.out.println( "error message: " + errorMessage.getName() );
			
			for ( String parameter : errorMessage.getParameters() ) {
				System.out.println( "error parameter: " + parameter );
			}
		}
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertFalse( Good1CommandResult.isRollbackExecuted() );
		assertTrue( Good2CommandResult.isExecuted() );
		assertFalse( Good2CommandResult.isRollbackExecuted() );
		
		assertTrue( command.rollback(getProvider()).isValid() );
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertTrue( Good1CommandResult.isRollbackExecuted() );
		assertTrue( Good2CommandResult.isExecuted() );
		assertTrue( Good2CommandResult.isRollbackExecuted() );
	}
	
	
	public void testRollBack1() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		SingleCommandResult Good2CommandResult = new SingleCommandResult("Good2CommandResult");
		SingleCommandResult Good3CommandResult = new SingleCommandResult("Good3CommandResult");
		SingleCommandResult Good4CommandResult = new SingleCommandResult("Good4CommandResult");
		SingleCommandResult Good5CommandResult = new SingleCommandResult("Good5CommandResult");
		
//		RTETCommandChain<IProvider> command = new RTETCommandChain<IProvider>(new ConcurrentCommandChainStrategy());
		CommandChain<IProvider> command = new CommandChain<IProvider>(new ConcurrentCommandChainStrategy());
		command.addCommand( new CommandGood(Good1CommandResult) );
		command.addCommand( new CommandGood(Good2CommandResult) );
		command.addCommand( new CommandGood(Good3CommandResult) );
		command.addCommand( new CommandGood(Good4CommandResult) );
		command.addCommand( new CommandGood(Good5CommandResult) );
		ACommandResult result = command.exec(getProvider(), new AsyncCommandExecutor());
		result.waitExecutionEnd();
		
		Thread.sleep(200);
		
		assertTrue( result.isExecutionEnd() );
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertTrue( result.isValid() );
		assertEquals( 0 , result.getErrorMessages().size() );
		
		for (IErrorMessage errorMessage : result.getErrorMessages()) {
			System.out.println( "error name:    " + errorMessage.getName() );
			System.out.println( "error message: " + errorMessage.getName() );
			
			for ( String parameter : errorMessage.getParameters() ) {
				System.out.println( "error parameter: " + parameter );
			}
		}
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertFalse( Good1CommandResult.isRollbackExecuted() );
		assertTrue( Good2CommandResult.isExecuted() );
		assertFalse( Good2CommandResult.isRollbackExecuted() );
		assertTrue( Good3CommandResult.isExecuted() );
		assertFalse( Good3CommandResult.isRollbackExecuted() );
		assertTrue( Good4CommandResult.isExecuted() );
		assertFalse( Good4CommandResult.isRollbackExecuted() );
		assertTrue( Good5CommandResult.isExecuted() );
		assertFalse( Good5CommandResult.isRollbackExecuted() );
		
		ACommandResult rollbackResult = command.rollback(getProvider(), new AsyncCommandExecutor());
		rollbackResult.waitExecutionEnd();
		assertTrue( rollbackResult.isExecutionEnd() );
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertTrue( Good1CommandResult.isRollbackExecuted() );
		assertTrue( Good2CommandResult.isExecuted() );
		assertTrue( Good2CommandResult.isRollbackExecuted() );
		assertTrue( Good3CommandResult.isExecuted() );
		assertTrue( Good3CommandResult.isRollbackExecuted() );
		assertTrue( Good4CommandResult.isExecuted() );
		assertTrue( Good4CommandResult.isRollbackExecuted() );
		assertTrue( Good5CommandResult.isExecuted() );
		assertTrue( Good5CommandResult.isRollbackExecuted() );
		
	}
}
