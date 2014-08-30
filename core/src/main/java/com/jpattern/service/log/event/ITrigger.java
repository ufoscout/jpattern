package com.jpattern.service.log.event;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public interface ITrigger extends Serializable {

	void fire( IEvent event );
	
}
