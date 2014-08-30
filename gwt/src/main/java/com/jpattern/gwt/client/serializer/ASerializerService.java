package com.jpattern.gwt.client.serializer;

import com.jpattern.gwt.client.AService;

/**
 * 
 * @author cinafr
 *
 */
public abstract class ASerializerService extends AService {

	public abstract <T> IObjectSerializer<T> getObjectSerializer(Class<T> clazz);
	
}
