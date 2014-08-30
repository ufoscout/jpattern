package com.jpattern.service.log.event;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public interface IMessage extends Serializable {

	String getClassName();
	
	Date getDate();
	
	String getMethod();
	
	String getMessage();
	
}
