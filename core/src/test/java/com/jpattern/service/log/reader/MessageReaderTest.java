package com.jpattern.service.log.reader;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.service.log.reader.IQueueMessages;
import com.jpattern.service.log.reader.MessageReader;
import com.jpattern.service.log.reader.QueueMessages;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class MessageReaderTest extends BaseApplicationTest {
	
	public void testMessageReader1() throws Exception {
		
		IQueueMessages messageQueue = new QueueMessages(50);
		MessageReader messageReader = new MessageReader(messageQueue);
		MessageFilteredReader messageStringNullFilteredReader = new MessageFilteredReader(messageReader, new NullMessageFilter());
		MessageFilteredReader messageStringEmptyFilteredReader = new MessageFilteredReader(messageReader, new MessageFilter(""));
		MessageFilteredReader messageStringFilteredReader = new MessageFilteredReader(messageReader, new MessageFilter("2"));
		
		messageQueue.offer("messaggio 1");
		messageQueue.offer("messaggio 2");
		messageQueue.offer("messaggio 3");
		
		assertEquals( "messaggio 1", messageReader.read().getMessage());
		assertEquals( "messaggio 1", messageReader.read(0).getMessage());
		assertEquals( "messaggio 2", messageReader.read(1).getMessage());
		assertEquals( "messaggio 3", messageReader.read(2).getMessage());
		
		assertEquals( "messaggio 1", messageStringNullFilteredReader.read().getMessage());
		assertEquals( "messaggio 1", messageStringNullFilteredReader.read(0).getMessage());
		assertEquals( "messaggio 2", messageStringNullFilteredReader.read(1).getMessage());
		assertEquals( "messaggio 3", messageStringNullFilteredReader.read(2).getMessage());
		
		assertEquals( "messaggio 1", messageStringEmptyFilteredReader.read().getMessage());
		assertEquals( "messaggio 1", messageStringEmptyFilteredReader.read(0).getMessage());
		assertEquals( "messaggio 2", messageStringEmptyFilteredReader.read(1).getMessage());
		assertEquals( "messaggio 3", messageStringEmptyFilteredReader.read(2).getMessage());
		
		assertEquals( "", messageStringFilteredReader.read().getMessage());
		assertEquals( "", messageStringFilteredReader.read(0).getMessage());
		assertEquals( "messaggio 2", messageStringFilteredReader.read(1).getMessage());
		assertEquals( "", messageStringFilteredReader.read(2).getMessage());
	}
	
}
