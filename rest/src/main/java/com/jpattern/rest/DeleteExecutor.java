package com.jpattern.rest;

import java.util.List;
import java.util.Map;

import com.jpattern.rest.action.IDeleteAction;
import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class DeleteExecutor implements IGetDeleteExecutor {

	private static final long serialVersionUID = 1L;
	private final IDeleteAction deleteAction;
	public DeleteExecutor(IDeleteAction deleteAction) {
		this.deleteAction = deleteAction;
	}
	
	@Override
	public ICommandFacadeResult<?> exec(Map<String, List<String>> resultQueryMap, String path, URLPath urlPath) {
		return deleteAction.delete(resultQueryMap, path, urlPath);
	}

}
