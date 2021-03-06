package com.jpattern.gwt.command;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.command.CommandChainAsynch;
import com.jpattern.gwt.client.command.ICommandCallBack;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class CommandChainAsynchTest extends BaseTest {

	private MockCommand myCommand1;
	private MockCommand myCommand2;
	private MockCommand myCommand3;

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCommandChainAsynch0() throws Exception {
		CommandChainAsynch commandChain = new CommandChainAsynch();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(false, false);
		myCommand3 = new MockCommand(false, false);
		
		commandChain.visit(getProvider());
		
		MyEvent event = new MyEvent(0, false, false, false);
		commandChain.exec(event);
		assertTrue(event.callbackEnd);
		
	}
	
	public void testCommandChainAsynch1() throws Exception {
		CommandChainAsynch commandChain = new CommandChainAsynch();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(false, false);
		myCommand3 = new MockCommand(false, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		
		commandChain.visit(getProvider());
		
		MyEvent event = new MyEvent(0, true, true, true);
		commandChain.exec(event);
		assertTrue(event.callbackEnd);
		
	}

	
	public void testCommandChainAsynch2() throws Exception {
		CommandChainAsynch commandChain = new CommandChainAsynch();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(true, false);
		myCommand3 = new MockCommand(false, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		
		commandChain.visit(getProvider());
		MyEvent event = new MyEvent(1, true, true, true);
		commandChain.exec(event);
		assertTrue(event.callbackEnd);
		
	}
	
	public void testCommandChainAsynch3() throws Exception {
		CommandChainAsynch commandChain = new CommandChainAsynch();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(false, true);
		myCommand3 = new MockCommand(true, false);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		
		commandChain.visit(getProvider());
		MyEvent event = new MyEvent(2, true, true, true);
		commandChain.exec(event);
		assertTrue(event.callbackEnd);
		
	}
	
	public void testCommandChainAsynch4() throws Exception {
		CommandChainAsynch commandChain = new CommandChainAsynch();
		myCommand1 = new MockCommand(false, false);
		myCommand2 = new MockCommand(false, true);
		myCommand3 = new MockCommand(false, true);
		commandChain.addCommand(myCommand1);
		commandChain.addCommand(myCommand2);
		commandChain.addCommand(myCommand3);
		
		commandChain.visit(getProvider());
		MyEvent event = new MyEvent(2, true, true, true);
		commandChain.exec(event);
		assertTrue(event.callbackEnd);
	}
	
	class MyEvent implements ICommandCallBack {

		boolean callbackEnd = false;
		private final int errorSize;
		private final boolean com1;
		private final boolean com2;
		private final boolean com3;

		public MyEvent(int errorSize, boolean com1, boolean com2, boolean com3) {
			this.errorSize = errorSize;
			this.com1 = com1;
			this.com2 = com2;
			this.com3 = com3;
		}

		@Override
		public void callback(ICommandResult commandResult) {
			
			for (IErrorMessage errorMessage : commandResult.getErrorMessages()) {
				System.out.println( "found error: [" + errorMessage.getName() + "] - [" + errorMessage.getMessage() + "]");
			}
			
			assertEquals( errorSize, commandResult.getErrorMessages().size() );
			
			assertEquals( com1 , myCommand1.isExecuted() );
			assertEquals( com2 , myCommand2.isExecuted() );
			assertEquals( com3 , myCommand3.isExecuted() );
			
			callbackEnd = true;
			
		}
		
	}
	
}
