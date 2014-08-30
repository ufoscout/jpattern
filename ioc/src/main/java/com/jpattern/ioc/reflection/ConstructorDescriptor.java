package com.jpattern.ioc.reflection;

import java.util.List;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;
import com.jpattern.ioc.xml.IParameter;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ConstructorDescriptor {

	List<IParameter> parameters;
	private IContextCreator contextCreator;
	
	public ConstructorDescriptor(IContextCreator contextCreator, List<IParameter> parameters) {
		this.parameters = parameters;
		this.contextCreator = contextCreator;
	}

	public Class<?>[] getParameterTypes() throws ConfigException, ClassNotFoundException {
		Class<?>[] parameterTypes = new Class[parameters.size()];
		for (int i = 0; i< parameters.size(); i++) {
			IParameter parameter = (IParameter) parameters.get(i);
			//parameterTypes[i] = parameter.typedValue(contextCreator).getClass();
			parameterTypes[i] = parameter.typedClass(contextCreator);
		}
		return parameterTypes;
	}
	
	public Object[] getParameterObjects() throws ConfigException, ClassNotFoundException {
		Object[] parameterObjects = new Object[parameters.size()];
		for (int i = 0; i< parameters.size(); i++) {
			IParameter parameter = (IParameter) parameters.get(i);
			parameterObjects[i] = parameter.typedValue(contextCreator);
		}
		return parameterObjects;
	}
}
