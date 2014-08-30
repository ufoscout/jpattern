package com.jpattern.gwt.client.serializer;

import com.google.gwt.core.client.GWT;
import com.kfuntak.gwt.json.serialization.client.Serializer;

/**
 * 
 * @author cinafr
 *
 */
public class GwtProJsonSerializerService extends ASerializerService {

	private final Serializer serializer;
	
	public GwtProJsonSerializerService() {
		serializer =  (Serializer) GWT.create( Serializer.class );
	}
	
	@Override
	public <T> IObjectSerializer<T> getObjectSerializer(Class<T> clazz) {
		return new GwtProJsonObjectSerializer<T>(serializer, clazz, getProvider());
	}

}
