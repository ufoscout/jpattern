package com.jpattern.rest.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.BaseTest;
import com.jpattern.rest.domain.URLPath;
import com.jpattern.logger.SystemOutLoggerFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class BuildQueryMapCommandTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testQueryString1() throws Exception {
		URLPath urlPath = new URLPath();
		urlPath.setQueryString("a=1");
		Map<String, List<String>> resultQueryMap = new HashMap<String, List<String>>();
		
		IRestCommand command = new BuildQueryMapCommand(urlPath, resultQueryMap);
		command.logger(new SystemOutLoggerFactory());
		assertTrue( command.exec().isValid() );
		
		assertEquals( 1 , resultQueryMap.size() );
		assertTrue( resultQueryMap.containsKey("a") );
		assertEquals( 1, resultQueryMap.get("a").size() );
		assertEquals( "1", resultQueryMap.get("a").get(0) );
	}
	
	public void testQueryString2() throws Exception {
		URLPath urlPath = new URLPath();
		urlPath.setQueryString("a=1&b=23&b=12");
		Map<String, List<String>> resultQueryMap = new HashMap<String, List<String>>();
		
		IRestCommand command = new BuildQueryMapCommand(urlPath, resultQueryMap);
		command.logger(new SystemOutLoggerFactory());
		assertTrue( command.exec().isValid() );
		
		assertEquals( 2 , resultQueryMap.size() );
		assertTrue( resultQueryMap.containsKey("a") );
		assertEquals( 1, resultQueryMap.get("a").size() );
		assertEquals( "1", resultQueryMap.get("a").get(0) );
		assertTrue( resultQueryMap.containsKey("b") );
		assertEquals( 2, resultQueryMap.get("b").size() );
		assertTrue( resultQueryMap.get("b").contains("23") );
		assertTrue( resultQueryMap.get("b").contains("12") );
	}
	
	public void testQueryString3() throws Exception {
		URLPath urlPath = new URLPath();
		Map<String, List<String>> resultQueryMap = new HashMap<String, List<String>>();
		
		IRestCommand command = new BuildQueryMapCommand(urlPath, resultQueryMap);
		command.logger(new SystemOutLoggerFactory());
		assertTrue( command.exec().isValid() );
		
		assertEquals( 0 , resultQueryMap.size() );
		assertFalse( resultQueryMap.containsKey("a") );
	}
	
	public void testQueryString4() throws Exception {
		URLPath urlPath = new URLPath();
		urlPath.setQueryString("lastLogger=%5BDEBUG%5D%20%5B13/05/2011%2009.56.07%20889%5D%20%5BThread.id-31%5D%20%5Bcom.jpattern.service.transaction.TransactionBeginCommand%5D%20%5Bresult%5D%20%5BStarting%20transaction%20with%20transactionManager:%20portalTransactionManager%5D&filter=");
		Map<String, List<String>> resultQueryMap = new HashMap<String, List<String>>();
		
		IRestCommand command = new BuildQueryMapCommand(urlPath, resultQueryMap);
		command.logger(new SystemOutLoggerFactory());
		assertTrue( command.exec().isValid() );
		
		assertEquals( 2 , resultQueryMap.size() );
		assertTrue( resultQueryMap.containsKey("lastLogger") );
		assertEquals( 1, resultQueryMap.get("lastLogger").size() );
		
		System.out.println(resultQueryMap.get("lastLogger").get(0));
		
		assertEquals( "[DEBUG] [13/05/2011 09.56.07 889] [Thread.id-31] [com.jpattern.service.transaction.TransactionBeginCommand] [result] [Starting transaction with transactionManager: portalTransactionManager]", resultQueryMap.get("lastLogger").get(0) );
		assertEquals( "", resultQueryMap.get("filter").get(0) );
	}
	
	

}
