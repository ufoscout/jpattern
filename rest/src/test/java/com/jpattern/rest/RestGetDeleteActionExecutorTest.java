package com.jpattern.rest;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jpattern.BaseTest;
import com.jpattern.rest.action.IGetAction;
import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.command.BaseCommandResult;
import com.jpattern.logger.SystemOutLoggerFactory;
import com.jpattern.shared.result.facade.CommandFacadeResult;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class RestGetDeleteActionExecutorTest extends BaseTest {

	HttpServletRequest request;
	HttpServletResponse response;
	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
	protected void setUp() throws Exception {
		super.setUp();
		
		request = new MockRequest();
		response = new MockResponse(outputStream );
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRestGetDeleteActionExecutor() throws Exception {
		IGetAction getAction = new MyGetAction();
		
		IGetDeleteExecutor getDeleteExecutor = new GetExecutor(getAction);
		String relativePath = "hello";
		IRestManager restManager = new GetDeleteRestManager(new SystemOutLoggerFactory(), getDeleteExecutor, relativePath, request, response);
		restManager.execute();
		
		System.out.println(outputStream.toString());
		assertTrue(  outputStream.toString().contains("HELLO REST WORLD!!"));
	}
	
	class MyGetAction implements IGetAction {

		private static final long serialVersionUID = 1L;

		@Override
		public ICommandFacadeResult<?> get( Map<String, List<String>> resultQueryMap, String path, URLPath urlPath) {
			return new CommandFacadeResult<String>(new BaseCommandResult(), "HELLO REST WORLD!!");
		}
		
	}
}
