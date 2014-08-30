package com.jpattern.gwt.client.history;

import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.history.HistoryManager;
import com.jpattern.gwt.client.history.DirectHistoryEngine;
import com.jpattern.gwt.client.history.NullHistoryManagerObserver;
import com.jpattern.gwt.client.logger.SysoutLoggerService;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public class FixedSizeHistoryManager2TestGwt extends BaseGwtTestCase {

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}
	
	public void testTokenGenerator1() throws Exception {
		HistoryManager historyManager = new HistoryManager(new DirectHistoryEngine(new SysoutLoggerService()), new NullHistoryManagerObserver(), new SysoutLoggerService());

		String tokenString = "_";
		String[] result = historyManager.generateTokenList(tokenString);
		print(tokenString, result);
		assertEquals(0, result.length);
		
		tokenString = "__";
		result = historyManager.generateTokenList(tokenString);
		print(tokenString, result);
		assertEquals(0, result.length);
		
		tokenString = "_A_";
		result = historyManager.generateTokenList(tokenString);
		print(tokenString, result);
		assertEquals(2, result.length);
		assertEquals("", result[0]);
		assertEquals("A", result[1]);
		
		tokenString = "_A_B";
		result = historyManager.generateTokenList(tokenString);
		print(tokenString, result);
		assertEquals(3, result.length);
		assertEquals("", result[0]);
		assertEquals("A", result[1]);
		assertEquals("B", result[2]);
	}
	
	void print(String tokenString, String[] tokens) {
		System.out.println("tokenString: " + tokenString);
		System.out.println("tokens size: " + tokens.length);
		for (String token : tokens) {
			System.out.println("found token [" + token + "]");
		}
	}

	
}
