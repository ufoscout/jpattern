package com.jpattern.service.log.event;

import java.util.Date;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class Message implements IMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String message;
	private String methodName;
	private String className;

	public Message(String className , String methodName, String message, Date date) {
		this.className = className;
		this.methodName = methodName;
		this.message = message;
		this.date = date;
	}
	
	public String getClassName() {
		return className;
	}

	public Date getDate() {
		return date;
	}

	public String getMessage() {
		return message;
	}

	public String getMethod() {
		return methodName;
	}

}
