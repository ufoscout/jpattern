package com.jpattern.shared.result;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Francesco Cina'
 *
 * 05/mag/2011
 */
public interface IResult extends Serializable {

	List<IErrorMessage> getErrorMessages();
	
}
