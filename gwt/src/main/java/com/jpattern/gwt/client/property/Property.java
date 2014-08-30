package com.jpattern.gwt.client.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class Property implements IProperty {

	private Map<String, String> properties = new HashMap<String, String>();
	
	public Property(Map<String, String> properties) {
		if (properties!=null) {
			this.properties = properties;
		}
	}
	
	@Override
	public List<String> getKeys() {
		List<String> result = new ArrayList<String>(properties.keySet());
		return result;
	}

	@Override
	public boolean containsKey(String key) {
		return (key!=null && properties.containsKey(key));
	}
	
	@Override
	public String getProperty(String key) {
		if (containsKey(key)) {
			return properties.get(key);
		}
		return "";
	}

}
