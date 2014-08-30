package com.jpattern.ioc.xml;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.xml.typebuilder.ITypeBuilder;
import com.jpattern.ioc.xml.typebuilder.TypeFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 18/dic/2009
 */

public class Parameter implements IParameter {

	private Type type;
    private Value value;

    public Parameter(Value aValue, Type aType) {
        value = aValue;
        type = aType;
    }

    public Value getValue() {
        return value;
    }
    
    public Type getType() {
    	if (type == null) {
    		type = new Type("String");
    	}
        return type;
    }

    public Object typedValue(IContextCreator aContextCreator) {
    	ITypeBuilder typeBuilder = new TypeFactory().typeBuilder();
    	return typeBuilder.exec( getType().type() , aContextCreator.readProperty( getValue().value() ) ).getInstance();
    }
    
    public Class<?> typedClass(IContextCreator aContextCreator) {
    	ITypeBuilder typeBuilder = new TypeFactory().typeBuilder();
    	return typeBuilder.exec( getType().type() , aContextCreator.readProperty( getValue().value() ) ).getClassOfInstance();
    }
    
}
