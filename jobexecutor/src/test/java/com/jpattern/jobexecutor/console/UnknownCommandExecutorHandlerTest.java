package com.jpattern.jobexecutor.console;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.console.ServerShutdownCommandExecutorHandler;
import com.jpattern.jobexecutor.console.UnknownCommandExecutorHandler;
import com.jpattern.jobexecutor.core.BooleanWrapper;
import com.jpattern.jobexecutor.mock.MockJobThreadPool;

/**
 * 
 * @author Francesco Cina'
 *
 * 05/apr/2010
 */
public class UnknownCommandExecutorHandlerTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testUnknown() throws Exception {
		IBooleanWrapper booleanWrapper = new BooleanWrapper(true);
		ICommandExecutorHandler command = new UnknownCommandExecutorHandler( );
		IWrappedResult result = new MockResult();
		command.exec("ciao", result, booleanWrapper);		
		assertEquals( command.getCommandDescription() +": ciao",  result.result() );
	}

	
	public void testUnknown3() throws Exception {
		IBooleanWrapper booleanWrapper = new BooleanWrapper(true);
		ICommandExecutorHandler command = new UnknownCommandExecutorHandler();
		command = new ServerShutdownCommandExecutorHandler(new MockJobThreadPool(), false);
		
		IWrappedResult result = new MockResult();
		
		command.exec( ICommandExecutorHandler.COMMAND_SHUTDOWN , result, booleanWrapper);		
		assertTrue( result.result().contains("Shutdown") );
	}
	
	
	class MockResult implements IWrappedResult {
		private static final long serialVersionUID = 1L;
		StringBuffer result = new StringBuffer(); 
		public String result() {
			return result.toString();
		}
		public void clean() {
		}
		public void add(String aLine) {
			result.append(aLine);
		}
	};
}
