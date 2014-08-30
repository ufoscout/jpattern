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
public abstract class AGetAction extends AAction implements IGetAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.jpattern.rest.action.IGetAction#get(java.util.Map, java.lang.String, com.jpattern.rest.domain.URLPath)
	 */
	@Override
	public abstract ICommandFacadeResult<?> get(Map<String, List<String>> resultQueryMap, String path, URLPath urlPath);
	
}
