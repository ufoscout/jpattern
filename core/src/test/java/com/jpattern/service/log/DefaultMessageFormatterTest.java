package com.jpattern.service.log;

import java.util.Date;


import com.jpattern.core.BaseTest;
import com.jpattern.service.log.DefaultMessageFormatter;
import com.jpattern.service.log.IMessageFormatter;
import com.jpattern.service.log.event.Message;
import com.jpattern.service.log.event.MessageEx;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class DefaultMessageFormatterTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFormatted1() throws Exception {
		IMessageFormatter formatter = new DefaultMessageFormatter();
		
		Message message = new Message("class", "method", "message", new Date());
		
		String result = formatter.toString("TEST EVENT", message) ;
		System.out.println( result) ;
		
		assertTrue( result.contains("[TEST EVENT]") );
		assertTrue( result.contains("[class]") );
		assertTrue( result.contains("[method]") ); 
		assertTrue( result.contains("[message]") ); 
	}
	
	public void testFormatted2() throws Exception {
		IMessageFormatter formatter = new DefaultMessageFormatter();
		
		MessageEx message = new MessageEx("class", "method", "message", new Date(), new NullPointerException() );
		
		String result = formatter.toString("ERROR", message) ;
		System.out.println( result) ;
		
		assertTrue( result.contains("[ERROR]") );
		assertTrue( result.contains("[class]") );
		assertTrue( result.contains("[method]") ); 
		assertTrue( result.contains("[message]") );
		assertTrue( result.contains("java.lang.NullPointerException") );
		
	}
}
