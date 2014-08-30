package com.jpattern.gwt.client.util;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.http.client.URL;

/**
 * 
 * @author Francesco Cina'
 * 
 */
public class QueryString {

	/**
	 * Convert a map of key value pairs to a query String in the format ?key1=value1&key2=value2.
	 * This method try to encode the query String using the 'URL' class of gwt that fails to do the encoding
	 * if not in Debug mode. In that case the query is returned in plain format (not encoded)
	 * @param keyValuesMap
	 * @return
	 */
	public static String toQueryString(Map<String, String> keyValuesMap) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Entry<String, String> entry : keyValuesMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			stringBuilder.append( stringBuilder.length()==0 ? "?" : "&");
			stringBuilder.append((key != null ? encode(key) : ""));
			stringBuilder.append("=");
			stringBuilder.append(value != null ? encode(value) : "");
		}
		return stringBuilder.toString();
	}
	
	public static String addTimestampQueryToUrl(String url) {
		String separator = (url.lastIndexOf("?")<0) ? "?" : "&";
		return url + separator + "_ts=" + new Date().getTime();		
	}
	
	/**
	 * Read a query string and return a map with all the key value pairs.
	 * This method try to decode the query String using the 'URL' class of gwt that fails to do the decoding
	 * if not in Debug mode. In that case the query string parsed not decoded
	 * @param queryString
	 * @return
	 */
	public static Map<String, String> toMap(String queryString) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		queryString = queryString.substring(queryString.lastIndexOf("?")+1);
		String[] queryStringPair = queryString.split("&");
		for (int i = 0; i < queryStringPair.length; i++) {
		    String[] substrRay = queryStringPair[i].split("=");
		    if (substrRay.length==2){
		    	result.put(decode(substrRay[0]), decode(substrRay[1]));
		    }
		}
		return result;
	}
	
	private static String decode(String queryString) {
		try {
			return URL.decodeQueryString(queryString);
		} catch (Throwable e) {
			return queryString;
		}
	}
	
	private static String encode(String queryString) {
		try {
			return URL.encodeQueryString(queryString);
		} catch (Throwable e) {
			return queryString;
		}
	}
}
