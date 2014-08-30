package com.jpattern.ioc.xml;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class ValueConverter implements SingleValueConverter {
    
    public Object fromString(String aValue) {
        return new Value(aValue);
    }

    public String toString(Object aObjectValue) {
        return ((Value) aObjectValue).value();
    }

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class aClassType) {
        return aClassType.equals(Value.class);
    }    
}
