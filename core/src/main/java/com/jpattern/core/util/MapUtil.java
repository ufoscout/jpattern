package com.jpattern.core.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Francesco Cina' 17/dic/2010 - 16.14.04
 * @version $Id: MapUtil.java,v 1.0, 2010-12-17 16:36:09Z, Francesco Cina$ 
 *
 */
public abstract class MapUtil {
	private static String ENCODING = CharacterEncoding.UTF_8.getCharset();
	
	public static String mapToUTF8String(Map<String, String> map) {
		StringBuilder stringBuilder = new StringBuilder();

		for (Entry<String, String> entry : map.entrySet()) {
			if (stringBuilder.length() > 0) {
				stringBuilder.append("&");
			}
			String key = entry.getKey();
			String value = entry.getValue();
			try {
				stringBuilder.append((key != null ? URLEncoder.encode(key, ENCODING) : ""));
				stringBuilder.append("=");
				stringBuilder.append(value != null ? URLEncoder.encode(value, ENCODING) : "");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return stringBuilder.toString();
	}

	public static Map<String, String> stringUTF8ToMap(String input) {
		Map<String, String> map = new HashMap<String, String>();

		String[] nameValuePairs = input.split("&");
		for (String nameValuePair : nameValuePairs) {
			String[] nameValue = nameValuePair.split("=");
			try {
				map.put(URLDecoder.decode(nameValue[0], ENCODING),
				nameValue.length > 1 ? URLDecoder.decode(nameValue[1], ENCODING) : "");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
