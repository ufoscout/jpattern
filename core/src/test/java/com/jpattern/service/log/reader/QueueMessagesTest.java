package com.jpattern.service.log.reader;

import com.jpattern.core.BaseApplicationTest;

/**
 * 
 * @author Francesco Cina
 *
 * 31/lug/2011
 */
public class QueueMessagesTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testQueueMessages() throws Exception {
		
		int queueSize = 10;
		int gap = 50;
		
		IQueueMessages messageQueue = new QueueMessages(queueSize);
		MessageReader messageReader = new MessageReader(messageQueue);
		
		for (int i=0; i<queueSize+gap; i++) {
			messageQueue.offer("message " + i);
		}
		
		for (int i=0; i<gap; i++) {
			IQueueMessage message = messageReader.read(i);
			System.out.println("messageId: " + message.getMessageId() + " - message: " + message.getMessage());
			assertEquals( gap, message.getMessageId());
			assertEquals(  "message " + gap, message.getMessage() );
			assertTrue( message.isValid() );
		}
		
		for (int i=gap; i<queueSize+gap; i++) {
			IQueueMessage message = messageReader.read(i);
			System.out.println("messageId: " + message.getMessageId() + " - message: " + message.getMessage());
			assertEquals( i, message.getMessageId());
			assertEquals(  "message " + i, message.getMessage() );
			assertTrue( message.isValid() );
		}
		
		for (int i=queueSize+gap; i<queueSize+gap+20; i++) {
			IQueueMessage message = messageReader.read(i);
			System.out.println("messageId: " + message.getMessageId() + " - message: " + message.getMessage());
			assertEquals( i, message.getMessageId());
			assertEquals(  "", message.getMessage() );
			assertFalse( message.isValid() );
		}
		
		for (int i=gap; i<queueSize+gap; i++) {
			IQueueMessage message = messageReader.read(i);
			System.out.println("messageId: " + message.getMessageId() + " - message: " + message.getMessage());
			assertEquals( i, message.getMessageId());
			assertEquals(  "message " + i, message.getMessage() );
			assertTrue( message.isValid() );
		}
		
	}

}
