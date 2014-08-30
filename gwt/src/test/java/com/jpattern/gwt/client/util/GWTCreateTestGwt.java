package com.jpattern.gwt.client.util;

import com.google.gwt.core.client.GWT;
import com.jpattern.gwt.client.BaseGwtTestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 13/mag/2011
 */
public class GWTCreateTestGwt extends BaseGwtTestCase {

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}
	
	public void testCreate1() throws Exception {
		MyClass myClass = GWT.create(MyClass.class);
		myClass.sayHello();
	}
	
	static class MyClass {
		void sayHello() {
			System.out.println("Hello!!");
		}
	}
}
