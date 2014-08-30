package com.jpattern.ioc.xml;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ParameterRef implements IParameter {
    private Value value;

    public ParameterRef(Value aValue) {
        value = aValue;
    }
       

    public Value getValue() {
        return value;
    }

    public Object typedValue(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
        return aContextCreator.create( aContextCreator.readProperty( getValue().value() ));
    }


	public Class<?> typedClass(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
		return typedValue(aContextCreator).getClass();
	}
    
}
