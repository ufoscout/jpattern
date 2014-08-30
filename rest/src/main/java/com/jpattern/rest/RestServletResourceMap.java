package com.jpattern.rest;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.rest.action.ActionWrapper;
import com.jpattern.rest.action.IAction;
import com.jpattern.rest.action.IActionWrapper;
import com.jpattern.rest.action.IDeleteAction;
import com.jpattern.rest.action.IGetAction;
import com.jpattern.rest.action.IPostAction;
import com.jpattern.rest.action.IPutAction;
import com.jpattern.rest.action.NullAction;
import com.jpattern.logger.ILoggerFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class RestServletResourceMap implements IRestServletResourceMap {

	private static final long serialVersionUID = 1L;
	Map<String, IPostAction> postMap = new HashMap<String, IPostAction>();
	Map<String, IGetAction> getMap = new HashMap<String, IGetAction>();
	Map<String, IPutAction> putMap = new HashMap<String, IPutAction>();
	Map<String, IDeleteAction> deleteMap = new HashMap<String, IDeleteAction>();
	private final ILoggerFactory loggerFactory;
	
	public RestServletResourceMap(ILoggerFactory loggerFactory) {
		this.loggerFactory = loggerFactory;
	}
	
	@Override
	public void addAction(String path, IAction action) {
		addPut(path, action);
		addPost(path, action);
		addGet(path, action);
		addDelete(path, action);
	}

	@Override
	public IActionWrapper<IGetAction> getAction(String pathInfo) {
		String path = getBestKey(pathInfo, getMap);
		if (getMap.containsKey(path)) {
			return new ActionWrapper<IGetAction>(getMap.get(path), getRelativePath(pathInfo, path));
		}
		return new ActionWrapper<IGetAction>(new NullAction(), (path)); 
	}

	@Override
	public IActionWrapper<IDeleteAction> deleteAction(String pathInfo) {
		String path = getBestKey(pathInfo, deleteMap);
		if (deleteMap.containsKey(path)) {
			return new ActionWrapper<IDeleteAction>(deleteMap.get(path), getRelativePath(pathInfo, path));
		}
		return new ActionWrapper<IDeleteAction>(new NullAction(), (path)); 
	}

	@Override
	public IActionWrapper<IPostAction> postAction(String pathInfo) {
		String path = getBestKey(pathInfo, postMap);
		if (postMap.containsKey(path)) {
			return new ActionWrapper<IPostAction>(postMap.get(path), getRelativePath(pathInfo, path));
		}
		return new ActionWrapper<IPostAction>(new NullAction(), (path)); 
	}

	@Override
	public IActionWrapper<IPutAction> putAction(String pathInfo) {
		String path = getBestKey(pathInfo, putMap);
		if (putMap.containsKey(path)) {
			return new ActionWrapper<IPutAction>(putMap.get(path), getRelativePath(pathInfo, path));
		}
		return new ActionWrapper<IPutAction>(new NullAction(), (path)); 
	}
	
	private void addPost(String path, IAction action) {
		if (action instanceof IPostAction) {
			postMap.put(path, (IPostAction) action);
		}
	}
	
	private void addPut(String path, IAction action) {
		if (action instanceof IPutAction) {
			putMap.put(path, (IPutAction) action);
		}
	}
	
	private void addGet(String path, IAction action) {
		if (action instanceof IGetAction) {
			getMap.put(path, (IGetAction) action);
		}
	}
	
	private void addDelete(String path, IAction action) {
		if (action instanceof IDeleteAction) {
			deleteMap.put(path, (IDeleteAction) action);
		}
	}
	
	protected String getBestKey(String url, Map<String, ?> map) {
		String temp = url;
		while (!temp.isEmpty()) {
			if ( map.containsKey(temp) ) {
				return temp;
			}
			int index = temp.lastIndexOf("/");
			if (index>=0) {
				temp = temp.substring(0, index);
			} else {
				temp="";
			}
		} 
		return temp;
	}
	
	protected String getRelativePath( String fullpath, String subPath ) {
		if (fullpath.length()>=subPath.length()) {
			return fullpath.substring(subPath.length(), fullpath.length());
		}
		return fullpath;
	}

	@Override
	public ILoggerFactory loggerFactory() {
		return loggerFactory;
	}

}
