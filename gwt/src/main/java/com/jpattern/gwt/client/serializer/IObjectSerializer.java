package com.jpattern.gwt.client.serializer;


/**
 * 
 * @author Francesco Cina'
 *
 */
public interface IObjectSerializer<T> {
	
	Class<T> getSerializerClass();
	
	String serialize(T bean);

	T deserialize(String json);
	
}
