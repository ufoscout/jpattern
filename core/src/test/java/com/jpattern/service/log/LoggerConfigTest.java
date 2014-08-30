package com.jpattern.service.log;

import java.util.List;


import com.jpattern.core.BaseTest;
import com.jpattern.service.log.LoggerConfig;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class LoggerConfigTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConfig() throws Exception {
		LoggerConfig config = new LoggerConfig();
		config.setMailTo(" mario@poste.it;carlo@mario.com  ;  gino@gino.deu;  ");
		List<String> mails = config.getMailToList();
		
		assertEquals( 3 , mails.size() );
		assertEquals( "mario@poste.it" , mails.get(0) );
		assertEquals( "carlo@mario.com" , mails.get(1) );
		assertEquals( "gino@gino.deu" , mails.get(2) );
		
	}
	
}
