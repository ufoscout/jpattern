package com.jpattern.gwt.client.property;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class PropertyReader {

	private String text = "";

	public PropertyReader(String text) {
		if (text != null) {
			this.text  = text;
		}
	}

	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		String[] token = text.split("\n");
		for (String keyValue : token) {
			if (keyValue.indexOf("=")>0 ) {
				int index = keyValue.indexOf("=");
				String key = keyValue.substring(0, index).trim();
				String value = "";
				if (index < keyValue.length()) {
					value = keyValue.substring(index+1, keyValue.length()).trim();
				}
				if (key.length()>0) {
					map.put(key, value);
				}
			}
		}
		return map;
	}

}
