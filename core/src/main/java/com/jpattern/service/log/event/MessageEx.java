package com.jpattern.service.log.event;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class MessageEx implements IMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String message;
	private String methodName;
	private String className;
	private Throwable exception;

	public MessageEx(String className, String methodName, String message, Date date ) {
		this(className, methodName, message, date, new NullException());
		
	}

	public MessageEx(String className , String methodName, String message, Date date, Throwable exception) {
		this.className = className;
		this.methodName = methodName;
		this.message = message;
		this.date = date;
		this.exception = exception;
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
	
	public String getExceptionType() {
		return exception.toString();
	}	
	
	
	public String getExceptionStackTrace() {
		StringWriter stringWriter = new StringWriter();
	    PrintWriter printWriter = new PrintWriter(stringWriter);
	    exception.printStackTrace(printWriter);
	    return stringWriter.toString();
	}
	
	
	public Throwable getException() {
		return exception;
	}
}
