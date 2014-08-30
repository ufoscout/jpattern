package com.jpattern.core.command;

import java.util.List;
import java.util.Vector;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public class AAsyncCommandTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAsync1() throws Exception {
		List<String> messageList = new Vector<String>();
		int howManyMessage = 30;
		ACommand<IProvider> asyncCommand = new MockAsyncCommand("thread1", messageList, howManyMessage, 5);
		ACommandResult asyncResult = asyncCommand.exec( getProvider(), new AsyncCommandExecutor() );
		
		while (!asyncResult.isExecutionEnd()) {
			Thread.sleep(5);
			System.out.println("asyncCommand is working...");
		}
		
		System.out.println("asyncCommand job is done!");
		
		assertTrue( asyncResult.isValid() );
		assertTrue( asyncResult.isExecutionEnd() );
		assertEquals(howManyMessage, messageList.size());
		
	}
	
	
	public void testAsyncWaitCommandResult() throws Exception {
		List<String> messageList = new Vector<String>();
		int howManyMessage = 30;
		ACommand<IProvider> asyncCommand = new MockAsyncCommand("thread1", messageList, howManyMessage, 10);
		ACommandResult asyncResult = asyncCommand.exec(getProvider(), new AsyncCommandExecutor());
		asyncResult.waitExecutionEnd();
		
		assertTrue( asyncResult.isValid() );
		assertTrue( asyncResult.isExecutionEnd() );
		assertEquals(howManyMessage, messageList.size());
	}
	
	public void testAsyncCommandChain() throws Exception {
		List<String> messageList1 = new Vector<String>();
		List<String> messageList2 = new Vector<String>();
		int howManyMessage1 = 50;
		int howManyMessage2 = 25;
		CommandChain<IProvider> asyncCommands = new CommandChain<IProvider>(); 
		asyncCommands.addCommand( new MockAsyncCommand("thread1", messageList1, howManyMessage1, 10) );
		asyncCommands.addCommand( new MockAsyncCommand("thread2", messageList2, howManyMessage2, 10) );
		ACommandResult asyncResult = asyncCommands.exec(getProvider(), new AsyncCommandExecutor());
		asyncResult.waitExecutionEnd();
		
		assertTrue( asyncResult.isValid() );
		assertTrue( asyncResult.isExecutionEnd() );
		assertEquals(howManyMessage1, messageList1.size());
		assertEquals(howManyMessage2, messageList2.size());
	}

}
