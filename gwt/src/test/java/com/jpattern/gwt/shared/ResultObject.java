package com.jpattern.gwt.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class ResultObject implements IResultObject {

	private List<String> messages = new ArrayList<String>();
	private boolean valid = false;
	private String name = "";

	@Override
	public List<String> getMessages() {
		return messages;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid ){
		this.valid = valid;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
