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
public abstract class ADeleteAction extends AAction implements IDeleteAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.jpattern.rest.action.IDeleteAction#delete(java.util.Map, java.lang.String, com.jpattern.rest.domain.URLPath)
	 */
	@Override
	public abstract ICommandFacadeResult<?> delete(Map<String, List<String>> resultQueryMap, String path, URLPath urlPath);
	
}
