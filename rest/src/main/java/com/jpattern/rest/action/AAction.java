package com.jpattern.rest.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class AAction implements IAction {

	private static final long serialVersionUID = 1L;
	
	protected String firstValue(Map<String, List<String>> resultQueryMap, String key) {
		List<String> values = values(resultQueryMap, key);
		if ( !values.isEmpty() ) {
			return values.get(0);
		}
		return "";
	}
	
	protected List<String> values(Map<String, List<String>> resultQueryMap, String key) {
		if ( resultQueryMap.containsKey(key) ) {
			return resultQueryMap.get(key);
		}
		return new ArrayList<String>();
	}

}
