package com.jpattern.cache.simple;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 24/set/2011
 */
public interface ICacheElement extends Serializable {

	long getTimestamp();

	Object getValue();

}
