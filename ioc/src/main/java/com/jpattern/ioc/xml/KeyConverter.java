package com.jpattern.ioc.xml;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class KeyConverter implements SingleValueConverter {

    public Object fromString(String aKey) {
        return new Key(aKey);
    }

    public String toString(Object aObjectKey) {
        return ((Key) aObjectKey).key();
    }

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class aClassType) {
        return aClassType.equals(Key.class);
    }

}
