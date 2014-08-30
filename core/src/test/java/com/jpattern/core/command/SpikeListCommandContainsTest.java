package com.jpattern.core.command;

import java.util.List;
import java.util.Vector;

import com.jpattern.core.BaseTest;
import com.jpattern.core.IProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public class SpikeListCommandContainsTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testContains() throws Exception {
		List<String> messageList = new Vector<String>();
		int howManyMessage = 25;
		ACommand<IProvider> asyncCommand1 = new MockAsyncCommand("thread1", messageList, howManyMessage, 10);
		ACommand<IProvider> asyncCommand2 = new MockAsyncCommand("thread2", messageList, howManyMessage, 10);
		ACommand<IProvider> asyncCommand3 = new MockAsyncCommand("thread2", messageList, howManyMessage, 10);
		ACommand<IProvider> asyncCommand4 = new MockAsyncCommand("thread2", messageList, howManyMessage, 10);
		
		List<ACommand<IProvider>> commandList = new Vector<ACommand<IProvider>>();
		commandList.add(asyncCommand1);
		commandList.add(asyncCommand2);
		commandList.add(asyncCommand3);
		commandList.add(asyncCommand4);
		
		assertTrue( commandList.contains(asyncCommand1) );
		assertTrue( commandList.contains(asyncCommand2) );
		assertTrue( commandList.contains(asyncCommand3) );
		assertTrue( commandList.contains(asyncCommand4) );
		
		commandList.remove( asyncCommand1 );
		commandList.remove( asyncCommand2 );
		
		assertFalse( commandList.contains(asyncCommand1) );
		assertFalse( commandList.contains(asyncCommand2) );
		assertTrue( commandList.contains(asyncCommand3) );
		assertTrue( commandList.contains(asyncCommand4) );
	}
}
