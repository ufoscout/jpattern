package com.jpattern.service.log.event;

import java.util.Date;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class NullMessage implements IMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getClassName() {
		return "";
	}

	public Date getDate() {
		return new Date();
	}

	public String getMessage() {
		return "";
	}

	public String getMethod() {
		return "";
	}

	public String getException() {
		return "";
	}

}
