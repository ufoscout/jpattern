package com.jpattern.gwt.shared;

import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/mag/2011
 */
public interface IWebResultString extends ICommandFacadeResult<String>  {

	@Override
	String getReturnedObject();
	
}
