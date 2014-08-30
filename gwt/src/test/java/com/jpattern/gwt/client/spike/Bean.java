package com.jpattern.gwt.client.spike;

public class Bean implements IBean {

	private final String hello;
	
	public Bean(String hello){
		this.hello = hello;
	}
	
	@Override
	public String getHello() {
		return hello;
	}
	
}
