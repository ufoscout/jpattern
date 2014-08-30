package com.jpattern.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jpattern.rest.command.BuildQueryMapCommand;
import com.jpattern.rest.command.BuildURLCommand;
import com.jpattern.rest.command.IRestCommand;
import com.jpattern.rest.command.ObjectToJsonStreamCommand;
import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.command.IBaseCommandResult;
import com.jpattern.logger.ILoggerFactory;
import com.jpattern.shared.result.facade.CommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 * 
 * An Executor which executes ACommandFacade objects and automatically 
 * convert the input data and the result from and to json format.
 * The result is streamed through the response output stream.
 */
public class GetDeleteRestManager implements IRestManager {

	private static final long serialVersionUID = 1L;
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final IGetDeleteExecutor getDeleteExecutor;
	private final String relativePath;
	private final ILoggerFactory loggerFactory;

	public GetDeleteRestManager(ILoggerFactory provider, IGetDeleteExecutor getDeleteExecutor, String relativePath, HttpServletRequest request, HttpServletResponse response) {
		this.loggerFactory = provider;
		this.getDeleteExecutor = getDeleteExecutor;
		this.relativePath = relativePath;
		this.request = request;
		this.response = response;
	}

	/* (non-Javadoc)
	 * @see com.jpattern.rest.IRestManager#execute()
	 */
	@Override
	public void execute() {
		try {
			Object result = new String();
			URLPath urlPath = new URLPath();
			Map<String, List<String>> resultQueryMap = new HashMap<String, List<String>>();
			IRestCommand prepareCommand = new BuildURLCommand(request, urlPath);
			prepareCommand = new BuildQueryMapCommand(urlPath, resultQueryMap, prepareCommand);
			prepareCommand.logger(loggerFactory);
			IBaseCommandResult prepareCommandResult = prepareCommand.exec();
			
			if ( prepareCommandResult.isValid()) {
				result = getDeleteExecutor.exec(resultQueryMap, relativePath, urlPath);
			} else {
				result = new CommandFacadeResult<String>(prepareCommandResult, "");
			}
		
			IRestCommand postCommand = new ObjectToJsonStreamCommand(result, response.getOutputStream());
			postCommand.logger(loggerFactory);
			postCommand.exec();
			
		} catch (Exception e) {
			loggerFactory.logger(this.getClass()).error("Error generating Rest Answer", e.getMessage(), e);
		}
		
	}

}
