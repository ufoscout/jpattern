package com.jpattern.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jpattern.rest.action.IActionWrapper;
import com.jpattern.rest.action.IDeleteAction;
import com.jpattern.rest.action.IGetAction;
import com.jpattern.shared.util.ValueUtil;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public abstract class RestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IRestServletResourceMap configuration; 
	
	protected abstract IRestServletResourceMap configure();
	
	@Override
	public final void doGet(HttpServletRequest request, HttpServletResponse response) {
		IActionWrapper<IGetAction> actionWrapper = getConfiguration().getAction( ValueUtil.stringNotNull(request.getPathInfo(),""));
		IRestManager restManager = new GetDeleteRestManager(getConfiguration().loggerFactory(), new GetExecutor(actionWrapper.action()), actionWrapper.relativePath(), request, response);
		restManager.execute();
	}

	@Override
	public final void doDelete(HttpServletRequest request, HttpServletResponse response) {
		IActionWrapper<IDeleteAction> actionWrapper = getConfiguration().deleteAction( ValueUtil.stringNotNull(request.getPathInfo(),""));
		IRestManager restManager = new GetDeleteRestManager(getConfiguration().loggerFactory(), new DeleteExecutor(actionWrapper.action()), actionWrapper.relativePath(), request, response);
		restManager.execute();
	}
	
	@Override
	public final void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	@Override
	public final void doPut(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected IRestServletResourceMap getConfiguration() {
		if (configuration == null) {
			configuration = configure();
		}
		return configuration;
	}
}
