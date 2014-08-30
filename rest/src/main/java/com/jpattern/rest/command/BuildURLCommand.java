package com.jpattern.rest.command;

import javax.servlet.http.HttpServletRequest;

import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.command.IBaseCommand;
import com.jpattern.shared.command.IBaseCommandResult;
import com.jpattern.shared.command.NullBaseCommand;
import com.jpattern.shared.util.ValueUtil;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class BuildURLCommand extends ARestCommand {

	private static final long serialVersionUID = 1L;
	private final HttpServletRequest request;
	private final URLPath urlPath;

	public BuildURLCommand(HttpServletRequest request, URLPath urlPath) {
		this(request, urlPath, new NullBaseCommand());
	}
			
	public BuildURLCommand(HttpServletRequest request, URLPath urlPath, IBaseCommand previousCommand) {
		super(previousCommand);
		this.request = request;
		this.urlPath = urlPath;
	}

	@Override
	protected void result(IBaseCommandResult result) {
		urlPath.setScheme( ValueUtil.stringNotNull( request.getScheme() ,"" ) );
		urlPath.setServerName( ValueUtil.stringNotNull( request.getServerName() ,"" ) );
		urlPath.setServerPort( request.getServerPort() );
		urlPath.setContextPath( ValueUtil.stringNotNull( request.getContextPath() ,"" ) );
		urlPath.setServletPath( ValueUtil.stringNotNull( request.getServletPath() ,"" ) );
		urlPath.setPathInfo( ValueUtil.stringNotNull( request.getPathInfo() ,"" ) );
		urlPath.setQueryString( ValueUtil.stringNotNull( request.getQueryString() ,"" ) );
	}

	@Override
	protected void internalRollBack(IBaseCommandResult result) {
	}

}
