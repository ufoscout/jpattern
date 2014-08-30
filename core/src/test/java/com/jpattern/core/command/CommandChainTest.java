package com.jpattern.core.command;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.shared.result.IErrorMessage;
import com.jpattern.shared.result.IResult;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class CommandChainTest extends BaseApplicationTest {

	private MockCommand myCommand1;
	private MockCommand myCommand2;
	private MockCommand myCommand3;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCommandChain0() throws Exception {
		CommandChain<IProvider> commandChain = new CommandChain<IProvider>();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(false, false);
		myCommand3 = new MockCommand(false, false);
		
		MyEvent event = new MyEvent(0, false, false, false);
		event.check( commandChain.exec(getProvider()) );
		assertTrue(event.callbackEnd);
		
	}
	
	public void testCommandChain1() throws Exception {
		CommandChain<ProviderExt> commandChain = new CommandChain<ProviderExt>();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(false, false);
		myCommand3 = new MockCommand(false, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(new MockCommandWithProviderExt(false, false));
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		
		MyEvent event = new MyEvent(0, true, true, true);
		event.check( commandChain.exec(new ProviderExt(getProvider())) );
		assertTrue(event.callbackEnd);
		
	}

	
	public void testCommandChain2() throws Exception {
		CommandChain<IProvider> commandChain = new CommandChain<IProvider>();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(true, false);
		myCommand3 = new MockCommand(false, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		
		MyEvent event = new MyEvent(1, true, true, false);
		event.check( commandChain.exec(getProvider()) );
		assertTrue(event.callbackEnd);
		
	}
	
	public void testCommandChain3() throws Exception {
		CommandChain<IProvider> commandChain = new CommandChain<IProvider>();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(false, true);
		myCommand3 = new MockCommand(true, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		
		MyEvent event = new MyEvent(1, true, true, false);
		event.check( commandChain.exec(getProvider()) );
		assertTrue(event.callbackEnd);
	}
	
	public void testCommandChain4() throws Exception {
		CommandChain<IProvider> commandChain = new CommandChain<IProvider>(new UnconditionalCommandChainStrategy());
		myCommand1 = new MockCommand("mock1", false, false);
		myCommand2 = new MockCommand("mock2", false, true);
		myCommand3 = new MockCommand("mock3", true, false);
		MockCommand myCommand4 = new MockCommand("mock4", false, false);
		
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		commandChain.addCommand(myCommand4);
		
		MyEvent event = new MyEvent(2, true, true, true);
		event.check( commandChain.exec(getProvider()) );
		assertTrue(event.callbackEnd);
		
		assertTrue( myCommand4.isExecuted() );
		assertFalse( myCommand1.isRolledBack() );
		assertFalse( myCommand2.isRolledBack() );
		assertFalse( myCommand3.isRolledBack() );
		assertFalse( myCommand4.isRolledBack() );
		
		ACommandResult rollbackResult = commandChain.rollback(getProvider());
		assertTrue(rollbackResult.isValid());
		
		assertTrue( myCommand1.isRolledBack() );
		assertFalse( myCommand2.isRolledBack() );
		assertFalse( myCommand3.isRolledBack() );
		assertTrue( myCommand4.isRolledBack() );
	}
	
	
	class MyEvent {

		private final int errorSize;
		private final boolean com1;
		private final boolean com2;
		private final boolean com3;
		boolean callbackEnd = false;

		public MyEvent(int errorSize, boolean com1, boolean com2, boolean com3) {
			this.errorSize = errorSize;
			this.com1 = com1;
			this.com2 = com2;
			this.com3 = com3;
		}

		public void check(IResult commandResult) {
			assertEquals( errorSize, commandResult.getErrorMessages().size() );
			
			assertEquals( com1 , myCommand1.isExecuted() );
			assertEquals( com2 , myCommand2.isExecuted() );
			assertEquals( com3 , myCommand3.isExecuted() );
			
			for (IErrorMessage errorMessage : commandResult.getErrorMessages()) {
				System.out.println( "found error: " + errorMessage.getMessage() );
			}
			
			callbackEnd = true;
			
		}
		
	}
	
}
