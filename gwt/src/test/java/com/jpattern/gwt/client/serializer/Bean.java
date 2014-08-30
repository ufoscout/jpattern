package com.jpattern.gwt.client.serializer;

public class Bean implements IBean {

	private String hello;
	private int year;
	private boolean male;
	
	@Override
	public String getHello() {
		return hello;
	}
	public void setHello(String hello) {
		this.hello = hello;
	}
	@Override
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	@Override
	public boolean isMale() {
		return male;
	}
	
	
	
}
