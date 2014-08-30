package com.jpattern.service.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jpattern.service.log.event.IMessage;
import com.jpattern.service.log.event.MessageEx;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class DefaultMessageFormatter implements IMessageFormatter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int eventNameMinLenght = 5;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss SSS");
	
	public String toString(String eventName, IMessage message) {
		
		StringBuffer buffer = new StringBuffer();
        buffer.append(  "["  );
        buffer.append(  fixNameLength( eventName , eventNameMinLenght )  );
        buffer.append(  "] ["  );
        formatDate(buffer, message.getDate());
        buffer.append(  "] ["  );
        
        buffer.append( "Thread.id-" + Thread.currentThread().getId() );
        buffer.append(  "] ["  );
        
        buffer.append( message.getClassName() );
        buffer.append(  "] ["  );
        buffer.append( message.getMethod() );
        buffer.append(  "] ["  );
        buffer.append( message.getMessage() );
        buffer.append("]");
        
        return buffer.toString();
	}

	public String toString(String eventName, MessageEx message) {
		StringBuffer buffer = new StringBuffer();
        
		buffer.append(  "["  );
        buffer.append(  fixNameLength( eventName, eventNameMinLenght ) );
        buffer.append(  "] ["  );
        formatDate(buffer, message.getDate());
        buffer.append(  "] ["  );
        
        buffer.append( "Thread.id-" + Thread.currentThread().getId() );
        buffer.append(  "] ["  );
        
        buffer.append( message.getClassName() );
        buffer.append(  "] ["  );
        buffer.append( message.getMethod() );
        buffer.append(  "] ["  );
        buffer.append( message.getMessage() );
        buffer.append(  "] ["  );
        buffer.append( message.getExceptionType() );
        buffer.append("]");
        
        return buffer.toString();
	}
	
	public String toStringWithStackTrace(String eventName, MessageEx message) {
		String result = toString(eventName,message);
		result = result + appendException( message ); 
        return result;
	}


    private String appendException( MessageEx message) {
    	String result = "";
    	if ( message.getExceptionType().length()>0) {
    		result = "\n" + message.getExceptionStackTrace();	
    	}
    	return result;
	}

	private Object fixNameLength(String eventName, int eventNameLenght) {
    	StringBuffer fixedEventName = new StringBuffer( eventName );
		while ( fixedEventName.length() < eventNameLenght ) {
			fixedEventName.append(" ");
		}
		return fixedEventName.toString();
	}

	private void formatDate(StringBuffer buffer, Date aDate) {
        buffer.append( dateFormat.format(  aDate ) );
    }    
}
