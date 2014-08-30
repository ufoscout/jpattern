package com.jpattern.rest;

import java.io.Serializable;
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
public interface IGetDeleteExecutor extends Serializable {

	ICommandFacadeResult<?> exec(Map<String, List<String>> resultQueryMap, String path, URLPath urlPath);
	
}
