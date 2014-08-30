package com.jpattern.gwt.client.serializer;

/**
 * 
 * @author cinafr
 *
 */
public class AutoBeanSerializerService<T extends IBeanFactory> extends ASerializerService {

	private final T beanFactory;

	public AutoBeanSerializerService(T beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <E> IObjectSerializer<E> getObjectSerializer(Class<E> clazz) {
		return new AutoBeanObjectSerializer(beanFactory, clazz, getProvider());
	}

}
