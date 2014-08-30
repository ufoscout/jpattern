package com.jpattern.rest.action;

import java.util.List;
import java.util.Map;

import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public interface IDeleteAction extends IAction {

	public abstract ICommandFacadeResult<?> delete(
			Map<String, List<String>> resultQueryMap, String path,
			URLPath urlPath);

}