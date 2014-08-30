package com.jpattern.gwt.client.history;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2011
 */
public class EventToken implements IEventToken {

	private final String token;
	private final String queryString;

	public EventToken(String token, String queryString) {
		this.token = token;
		this.queryString = queryString;
	}
	
	@Override
	public String getToken() {
		return token;
	}

	@Override
	public String getQueryString() {
		return queryString;
	}

}
