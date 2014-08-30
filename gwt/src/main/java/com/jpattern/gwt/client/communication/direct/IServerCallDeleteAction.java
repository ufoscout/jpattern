package com.jpattern.gwt.client.communication.direct;

import java.util.Map;

import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public interface IServerCallDeleteAction extends IServerCallAction {

	public <T extends ICommandFacadeResult<?>> T delete(Class<T> resultClass, String url, Map<String, String> keyValuesMap);

}
