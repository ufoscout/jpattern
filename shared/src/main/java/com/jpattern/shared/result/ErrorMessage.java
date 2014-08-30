package com.jpattern.shared.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ErrorMessage implements  IErrorMessage {

    private static final long serialVersionUID = 1L;

    private String name;
    
    private String message;

    private List<String> parameters;
    
    public ErrorMessage(String name, String message) {
    	this(name, message, new ArrayList<String>());
    }    
    
    public ErrorMessage(String name, String message, String[] parameters) {
    	this(name, message, Arrays.asList(parameters));
    }
    
    public ErrorMessage(String name, String message, List<String> parameters) {
    	this.message = message;
    	this.name = name;
    	this.parameters = parameters;
    }

    @Override
    public String getMessage() {
    	if (message == null) {
    		message = "";
    	}
        return message;
    }

    @Override
    public String getName() {
    	if (name == null) {
    		name = "";
    	}
        return name;
    }
    
    @Override
    public List<String> getParameters() {
        if (parameters==null) {
            parameters = new ArrayList<String>();
        }
        return parameters;
    }    
}
