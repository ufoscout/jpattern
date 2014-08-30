package com.jpattern.ioc.reflection;

import java.lang.reflect.Constructor;

import com.jpattern.ioc.exception.ConfigException;


public class ReflectionUtil {

    public Object newInstance(Class<?> aClass, ConstructorDescriptor constructorDescriptor) throws ConfigException {
          try {
        	  FindConstructor findConstructor = new FindConstructor( aClass, constructorDescriptor);
        	  Constructor<?> costruttore = findConstructor.getConstructor();
        	  return costruttore.newInstance(constructorDescriptor.getParameterObjects());
           }
           catch (Exception e) {
               throw new ConfigException("creating class " + aClass , e);
           }
    }
    
    public Object newInstance(String aClassName , ConstructorDescriptor constructorDescriptor) throws ConfigException {
        try {
              return newInstance(Class.forName(aClassName) , constructorDescriptor);
         }
         catch (Exception e) {
             throw new ConfigException("loading on creating class " + aClassName , e);
         }
      
  }    
    
    public Object classForName(String aClassName) throws ConfigException {
        try {
              return Class.forName(aClassName);
         }
         catch (Exception e) {
             throw new ConfigException("loading class " + aClassName , e);
         }
  }      

    public Object getterInvoke(Object buildableObject, MethodDescriptor aDescriptor) throws ConfigException {
        try {
            return aDescriptor.getGetter().invoke(buildableObject);
        }
        catch (Exception e) {
            throw new ConfigException("invoking getter" + aDescriptor.getName(), e);
        }
    }    
    
    public Object setterInvoke(MethodDescriptor aDescriptor, Object[] args, Object returnedObject) throws ConfigException {
        try {
              return  aDescriptor.getSetter().invoke(returnedObject, args);
        }
        catch (Exception e) {
            throw new ConfigException("invoking setter " + aDescriptor.getName(), e);
        }
    }    
}
