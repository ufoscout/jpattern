package com.jpattern.rest.action;

import java.util.List;
import java.util.Map;

import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.result.NullResult;
import com.jpattern.shared.result.facade.CommandFacadeResult;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class NullAction implements IGetAction, IDeleteAction, IPostAction, IPutAction {

	private static final long serialVersionUID = 1L;

	@Override
	public ICommandFacadeResult<String> get(Map<String, List<String>> parameters, String path, URLPath urlPath) {
		return new CommandFacadeResult<String>(new NullResult(), "");
	}

	@Override
	public ICommandFacadeResult<String> put(Map<String, List<String>> parameters, String path, URLPath urlPath) {
		return new CommandFacadeResult<String>(new NullResult(), "");
	}

	@Override
	public ICommandFacadeResult<String> post(Map<String, List<String>> parameters, String path, URLPath urlPath) {
		return new CommandFacadeResult<String>(new NullResult(), "");
	}

	@Override
	public ICommandFacadeResult<String> delete(Map<String, List<String>> parameters,	String path, URLPath urlPath) {
		return new CommandFacadeResult<String>(new NullResult(), "");
	}

}
