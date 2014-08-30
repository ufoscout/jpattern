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
public class ICommandTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCommandGood1() throws Exception {
		
		SingleCommandResult Good1CommandResult = new SingleCommandResult("Good1CommandResult");
		SingleCommandResult Good2CommandResult = new SingleCommandResult("Good2CommandResult");
		SingleCommandResult Good3CommandResult = new SingleCommandResult("Good3CommandResult");
		
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new CommandGood(Good1CommandResult) );
		command.addCommand( new CommandGood(Good2CommandResult) );
		command.addCommand( new CommandGood(Good3CommandResult) );
		ACommandResult result = command.exec(getProvider());
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertTrue( result.isValid() );
		assertTrue( result.getErrorMessages().size() == 0 );
		
		assertTrue( Good1CommandResult.isExecuted() );
		assertFalse( Good1CommandResult.isRollbackExecuted() );
		assertTrue( Good2CommandResult.isExecuted() );
		assertFalse( Good2CommandResult.isRollbackExecuted() );
		assertTrue( Good3CommandResult.isExecuted() );
		assertFalse( Good3CommandResult.isRollbackExecuted() );
		
	}
	
	public void testCommandBad1() throws Exception {
		
		SingleCommandResult Bad1CommandResult = new SingleCommandResult("Bad1CommandResult");
		
		int howManyErrors = 4;
		ACommand<IProvider> command = new CommandBad(howManyErrors, Bad1CommandResult);
		ACommandResult result = command.exec(getProvider());
		
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
		
		assertTrue( Bad1CommandResult.isExecuted() );
		assertFalse( Bad1CommandResult.isRollbackExecuted() );
		
	}

	public void testBadAndGood() throws Exception {
		
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
		
		System.out.println("result.asString():");
		System.out.println(result.asString());
		System.out.println();
		
		assertFalse( result.isValid() );
		assertTrue( result.getErrorMessages().size() == 2 );
		
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
	
}
