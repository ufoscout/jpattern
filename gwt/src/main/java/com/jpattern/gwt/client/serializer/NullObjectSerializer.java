package com.jpattern.gwt.client.serializer;

/**
 * 
 * @author cinafr
 *
 * @param <T>
 */
public class NullObjectSerializer<T> implements IObjectSerializer<T> {

	private Class<T> aClass;

	public NullObjectSerializer (Class<T> aClass) {
			this.aClass = aClass;
	}
	
	@Override
	public String serialize(T bean) {
		return "";
	}

	@Override
	public T deserialize(String json) {
		return null;
	}

	@Override
	public Class<T> getSerializerClass() {
		return aClass;
	}

}
