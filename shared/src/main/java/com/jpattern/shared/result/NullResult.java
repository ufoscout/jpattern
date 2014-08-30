package com.jpattern.shared.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class NullResult implements IResult {

	private static final long serialVersionUID = 1L;

	@Override
	public List<IErrorMessage> getErrorMessages() {
		return new ArrayList<IErrorMessage>();
	}

}
