package com.jpattern.rest;

import java.util.List;
import java.util.Map;

import com.jpattern.rest.action.IGetAction;
import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class GetExecutor implements IGetDeleteExecutor {

	private static final long serialVersionUID = 1L;
	private final IGetAction getAction;
	public GetExecutor(IGetAction getAction) {
		this.getAction = getAction;
	}
	
	@Override
	public ICommandFacadeResult<?> exec(Map<String, List<String>> resultQueryMap, String path, URLPath urlPath) {
		return getAction.get(resultQueryMap, path, urlPath);
	}

}
