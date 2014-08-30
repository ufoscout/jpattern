package com.jpattern.core.command;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina
 *
 * 20 Sep 2011
 */
public class CommandCatchFalseTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCommandExec1() throws Exception {
		boolean throwException = true;
		boolean generateError = false;
		ACommand<IProvider> command = new MockCommand(throwException, generateError);
		command.setOnExceptionStrategy(new CatchOnExceptionStrategy());
		assertFalse(command.exec(getProvider()).isValid());
		
		boolean exception = false;
		try {
			command.setOnExceptionStrategy(new ThrownOnExceptionStrategy());
			command.exec(getProvider());
		} catch (RuntimeException e) {
			exception = true;
		}
		assertTrue(exception);
	}
	
	public void testCommandRollback1() throws Exception {

		SingleCommandResult singleCommandResult = new SingleCommandResult("");
		ACommand<IProvider> command = new CommandGoodRollBackException(singleCommandResult);
		command.setOnExceptionStrategy(new CatchOnExceptionStrategy());
		assertTrue(command.exec(getProvider()).isValid());
		
		command.setOnExceptionStrategy(new ThrownOnExceptionStrategy());
		assertTrue(command.exec(getProvider()).isValid());
		
		command.setOnExceptionStrategy(new CatchOnExceptionStrategy());
		assertFalse(command.rollback(getProvider()).isValid());
		
		assertTrue(command.exec(getProvider()).isValid());
		boolean exception = false;
		try {
			command.setOnExceptionStrategy(new ThrownOnExceptionStrategy());
			command.rollback(getProvider());
		} catch (RuntimeException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	public void testCommandChainExecute1() throws Exception {
		CommandChain<IProvider> commandChain = new CommandChain<IProvider>();
		
		boolean throwException = true;
		boolean generateError = false;
		commandChain.addCommand( new MockCommand(throwException, generateError) );

		assertFalse(commandChain.exec(getProvider()).isValid());
		
		boolean exception = false;
		try {
			commandChain.setOnExceptionStrategy(new ThrownOnExceptionStrategy());
			commandChain.exec(getProvider());
		} catch (RuntimeException e) {
			exception = true;
		}
		assertTrue(exception);
	}
	
	public void testCommandChainRollback1() throws Exception {
		CommandChain<IProvider> commandChain = new CommandChain<IProvider>();
		
		commandChain.addCommand( new CommandGoodRollBackException(new SingleCommandResult("")) );
		
		commandChain.setOnExceptionStrategy(new CatchOnExceptionStrategy());
		assertTrue(commandChain.exec(getProvider()).isValid());
		
		commandChain.setOnExceptionStrategy(new ThrownOnExceptionStrategy());
		assertTrue(commandChain.exec(getProvider()).isValid());
		
		commandChain.setOnExceptionStrategy(new CatchOnExceptionStrategy());
		assertFalse(commandChain.rollback(getProvider()).isValid());
		assertTrue(commandChain.exec(getProvider()).isValid());

		boolean exception = false;
		try {
			commandChain.setOnExceptionStrategy(new ThrownOnExceptionStrategy());
			commandChain.rollback(getProvider());
		} catch (RuntimeException e) {
			exception = true;
		}
		assertTrue(exception);
	}
}
