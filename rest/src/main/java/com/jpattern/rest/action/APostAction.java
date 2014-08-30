package com.jpattern.rest.action;

import java.util.List;
import java.util.Map;

import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public abstract class APostAction extends AAction implements IPostAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.jpattern.rest.action.IPostAction#post(java.util.Map, java.lang.String, com.jpattern.rest.domain.URLPath)
	 */
	@Override
	public abstract ICommandFacadeResult<?> post(Map<String, List<String>> parameters, String path, URLPath urlPath);
	
}
