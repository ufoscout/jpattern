package com.jpattern.ioc.xml;

import com.thoughtworks.xstream.converters.SingleValueConverter;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:06
 * @version $Id: $
 */
public class IdConverter implements SingleValueConverter {

    public Object fromString(String aId) {
        return new Id(aId);
    }

    public String toString(Object aObjectId) {
        return ((Id) aObjectId).id();
    }

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class aClassType) {
        return aClassType.equals(Id.class);
    }

}
