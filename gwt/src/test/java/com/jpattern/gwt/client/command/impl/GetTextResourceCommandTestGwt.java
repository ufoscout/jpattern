package com.jpattern.gwt.client.command.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandCallBack;
import com.jpattern.gwt.client.command.ICommandResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2011
 */
public class GetTextResourceCommandTestGwt extends BaseGwtTestCase {

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
	
	public void testRead1() throws Exception {
		System.out.println("GWT.getModuleBaseURL() = " + GWT.getModuleBaseURL());
		System.out.println("GWT.getModuleName() = " + GWT.getModuleName());
		System.out.println("GWT.getModuleBaseURL().replace(GWT.getModuleName()... ) = " + GWT.getModuleBaseURL().replace(GWT.getModuleName() + "/", ""));
		GWT.getModuleBaseURL().replace(GWT.getModuleName() + "/", ""); 
		
		
		Timer timer = new Timer() {
			public void run() {
				StringBuffer url = new StringBuffer("/jpattern-gwt.properties");
				final StringBuffer fileContent = new StringBuffer();
				ACommand command = new GetTextResourceCommand(url, fileContent);
				ICommandCallBack eventCallBack = new ICommandCallBack() {
					@Override
					public void callback(ICommandResult webResult) {
						assertTrue(webResult.getErrorMessages().isEmpty());
						System.out.println("fileContent: " + fileContent);
						assertEquals("ciao" , fileContent.toString());
						finishTest();
					}
				};
				command.exec(eventCallBack);
		    }
		  };
		  delayTestFinish(10000);
		  timer.schedule(10);
	}
}
