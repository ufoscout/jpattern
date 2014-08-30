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
public interface IPutAction extends IAction {

	public abstract ICommandFacadeResult<?> put(
			Map<String, List<String>> parameters, String path, URLPath urlPath);

}