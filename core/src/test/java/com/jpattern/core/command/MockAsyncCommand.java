package com.jpattern.core.command;

import java.util.List;

import com.jpattern.core.IProvider;


/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public class MockAsyncCommand extends ACommand<IProvider> {

	private final List<String> messageList;
	private final int howManyMessage;
	private final int sleepMilliseconds;
	private final String name;

	public MockAsyncCommand(String name, List<String> messageList, int howManyMessage, int sleepMilliseconds) {
		this.name = name;
		this.messageList = messageList;
		this.howManyMessage = howManyMessage;
		this.sleepMilliseconds = sleepMilliseconds;
	}

	@Override
	protected void execute(ACommandResult result) {
		System.out.println(name + ": --- MockAsyncCommand Execution Started ---");
		
		for (int i=0; i<howManyMessage; i++) {
			System.out.println(name + ": Adding message " + i);
			messageList.add(name + ": add " + i);
			try {
				Thread.sleep(sleepMilliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(name + ": --- MockAsyncCommand Execution Ended ---");
	}

	@Override
	protected void rollback(ACommandResult rollBackResult) {
		System.out.println(name + ": --- MockAsyncCommand RollBack Started ---");
		
//		for (int i=0; i<howManyMessage; i++) {
//			System.out.println(name + ": Adding message " + i);
//			messageList.add(name + ": add " + i);
//			try {
//				Thread.sleep(sleepMilliseconds);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
		System.out.println(name + ": --- MockAsyncCommand RollBack Ended ---");
	}


}
