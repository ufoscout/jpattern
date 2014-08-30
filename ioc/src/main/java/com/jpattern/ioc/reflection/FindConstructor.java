package com.jpattern.ioc.reflection;

import java.lang.reflect.Constructor;

import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class FindConstructor {

	private ConstructorDescriptor constructorDescriptor;
	private Class<?> aClass;

	public FindConstructor(Class<?> aClass, ConstructorDescriptor constructorDescriptor) {
		this.aClass = aClass;
		this.constructorDescriptor = constructorDescriptor;
	}

	public Constructor<?> getConstructor() throws SecurityException, NoSuchMethodException, ConfigException, ClassNotFoundException {
		Constructor<?> resultConstructor = null;
		try {
			resultConstructor = aClass.getConstructor(constructorDescriptor.getParameterTypes());
		}
		catch (NoSuchMethodException e) {
			
			resultConstructor = searchCompatibleConstructor();
			
			if ( resultConstructor == null ) {
				throw e;
			}
		}
		return resultConstructor;
	}

	
	Constructor<?> searchCompatibleConstructor() throws ConfigException, ClassNotFoundException {
		Constructor<?>[] constructors = aClass.getConstructors();
		Constructor<?> resultConstructor = null;
		
		for (int i = 0; i<constructors.length; i++) {
			Constructor<?> constructor = constructors[i];
			if ( checkParameters( constructor , constructorDescriptor ) ) {
				resultConstructor = constructor;
			}
		}
		return resultConstructor;
	}

	
	boolean checkParameters(Constructor<?> constructor, ConstructorDescriptor constructorDescriptor) throws ConfigException, ClassNotFoundException {
		boolean result = true;
		Class<?>[] constructorParameters = constructor.getParameterTypes();
		Object[] parameterObjects = constructorDescriptor.getParameterObjects();
		Class<?>[] parameterTypes = constructorDescriptor.getParameterTypes();
		
		if (!(constructorParameters.length == parameterObjects.length)) {
			return false;
		}
		
		for (int i = 0; i<constructorParameters.length; i++) {
			result = result && checkInstance(constructorParameters[i], parameterObjects[i], parameterTypes[i] );
		}
		return result;
	}
	
	boolean checkInstance(Class<?> constructorParameter, Object objectType, Class<?> objectClass) {
		if ( constructorParameter.isPrimitive() ) {
			return constructorParameter.equals(objectClass);
		}
		return constructorParameter.isInstance( objectType ); 
	}

}
