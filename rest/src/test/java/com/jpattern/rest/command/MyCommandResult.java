package com.jpattern.rest.command;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class MyCommandResult {

	private final List<MyErrorMessage> errorMessages = new ArrayList<MyErrorMessage>();
	private final List<String> returnedObject = new ArrayList<String>();
	private boolean valid = false;
	
	public List<MyErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	public List<String> getReturnedObject() {
		return returnedObject ;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}