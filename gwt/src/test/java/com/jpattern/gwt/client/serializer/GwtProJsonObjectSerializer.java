package com.jpattern.gwt.client.serializer;

import com.google.gwt.core.client.GWT;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.logger.ILogger;
import com.kfuntak.gwt.json.serialization.client.Serializer;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class GwtProJsonObjectSerializer<T> implements IObjectSerializer<T> {

	private final Class<T> aClass;
	private final ILogger logger;
	private final Serializer serializer;
	
	public GwtProJsonObjectSerializer(Serializer serializer, Class<T> aClass, IApplicationProvider provider) {
		this.serializer = serializer;
		this.aClass = aClass;
		this.logger = provider.getLoggerService().getLogger(this.getClass());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(String json) {
		logger.trace("deserialize", "Expected object class: " + aClass);
		logger.trace("deserialize", "json object to deserialize: " + json);
		return (T) serializer.deSerialize( json , aClass.getName() );
	}

	@Override
	public String serialize(T bean) {
		Serializer serializer =  (Serializer) GWT.create( Serializer.class );
		return serializer.serialize( bean );
	}

	@Override
	public Class<T> getSerializerClass() {
		return aClass;
	}

}
