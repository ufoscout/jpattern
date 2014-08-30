package com.jpattern.service.log;

import java.io.Serializable;

import com.jpattern.service.log.event.IMessage;
import com.jpattern.service.log.event.MessageEx;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public interface IMessageFormatter extends Serializable {
	
	String toString( String eventName, IMessage message );
	
	String toString( String eventName, MessageEx message );
	
	String toStringWithStackTrace( String eventName, MessageEx message );
	
}
