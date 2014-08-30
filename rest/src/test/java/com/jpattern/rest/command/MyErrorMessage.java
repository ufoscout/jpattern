package com.jpattern.rest.command;

import java.util.List;

import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class MyErrorMessage implements IErrorMessage {

	private static final long serialVersionUID = 1L;
	String message;
	String name;
	List<String> parameters;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getParameters() {
		return parameters;
	}
	
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	

}
