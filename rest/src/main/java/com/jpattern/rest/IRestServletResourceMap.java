package com.jpattern.rest;

import java.io.Serializable;

import com.jpattern.rest.action.IAction;
import com.jpattern.rest.action.IActionWrapper;
import com.jpattern.rest.action.IDeleteAction;
import com.jpattern.rest.action.IGetAction;
import com.jpattern.rest.action.IPostAction;
import com.jpattern.rest.action.IPutAction;
import com.jpattern.logger.ILoggerFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public interface IRestServletResourceMap extends Serializable {
	
	ILoggerFactory loggerFactory();
	
	void addAction(String path, IAction action);
	
	IActionWrapper<IGetAction> getAction(String pathInfo);
	
	IActionWrapper<IDeleteAction> deleteAction(String pathInfo);
	
	IActionWrapper<IPostAction> postAction(String pathInfo);
	
	IActionWrapper<IPutAction> putAction(String pathInfo);

}
