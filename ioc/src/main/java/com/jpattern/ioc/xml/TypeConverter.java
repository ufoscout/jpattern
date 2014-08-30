package com.jpattern.ioc.xml;

import com.thoughtworks.xstream.converters.SingleValueConverter;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/dic/2009
 */
public class TypeConverter implements SingleValueConverter {

    public Object fromString(String aType) {
        return new Type(aType);
    }

    public String toString(Object aObjectType) {
        return ((Type) aObjectType).type();
    }

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class aClassType) {
        return aClassType.equals(Type.class);
    }

}
