package com.jpattern.gwt.client.serializer;

/**
 * 
 * @author cinafr
 *
 */
public class NullSerializerService extends ASerializerService {

	@Override
	public <T> IObjectSerializer<T> getObjectSerializer(Class<T> clazz) {
		return new NullObjectSerializer<T>(clazz);
	}

}
