package com.jpattern.gwt.client.communication.sop;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * 
 * @author Francesco Cina'
 *
 * 13/mag/2011
 */
public interface JSONRequestHandler {
	
    public void onRequestComplete(JavaScriptObject json);
    
}