package com.jpattern.gwt.client;


/**
 * 
 * @author Francesco Cina'
 *
 */
public class GwtTestSimple extends BaseGwtTestCase {

	@Override
	public String getModuleName() {
		return "com.jpattern.gwt.jpattern_tests";
	}
	
	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}
	
	public void testStart() throws Exception {
		assertTrue(true);
	}
	
}
