package com.jpattern.gwt.client.communication.rest;

import com.google.gwt.core.client.GWT;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.serializer.IBeanFactory;
import com.jpattern.gwt.shared.ITestBeanFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class GenericsObjectSerializerTestGwt extends BaseGwtTestCase {

	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	@Override
	public String getModuleName() {
		return "com.jpattern.gwt.jpattern_tests";
	}
	
	public void testGenericsObjectWrapper() throws Exception {
		IBeanFactory beanFactory = GWT.create(IBeanFactory.class);
		assertNotNull(beanFactory);
		ITestBeanFactory testBeanFactory = GWT.create(ITestBeanFactory.class);
		assertNotNull(testBeanFactory);
	}

}
