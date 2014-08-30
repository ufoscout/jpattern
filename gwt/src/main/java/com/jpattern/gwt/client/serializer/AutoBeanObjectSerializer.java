package com.jpattern.gwt.client.serializer;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class AutoBeanObjectSerializer<T> implements IObjectSerializer<T> {

	private final Class<T> aClass;
	private final ILogger logger;
	private final IBeanFactory beanFactory;
	
	
	public AutoBeanObjectSerializer(IBeanFactory beanFactory, Class<T> aClass, IApplicationProvider provider) {
		this.beanFactory = beanFactory;
		this.aClass = aClass;
		this.logger = provider.getLoggerService().getLogger(this.getClass());
	}

	@Override
	public T deserialize(String json) {
		logger.trace("deserialize" , "Deserializing using beanFactory: " + beanFactory.getClass());
		logger.trace("deserialize" , "Expected object class: " + aClass);
		logger.trace("deserialize" , "json object to deserialize: " + json);
		return AutoBeanCodex.decode(beanFactory, aClass, json).as();
	}

	@Override
	public String serialize(T bean) {
		AutoBean<T> autoBean = beanFactory.create(aClass, bean);
		return AutoBeanCodex.encode(autoBean).getPayload();
	}

	@Override
	public Class<T> getSerializerClass() {
		return aClass;
	}
}
