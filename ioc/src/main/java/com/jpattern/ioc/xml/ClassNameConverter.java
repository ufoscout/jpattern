package com.jpattern.ioc.xml;

import com.thoughtworks.xstream.converters.SingleValueConverter;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:23
 * @version $Id: $
 */
public class ClassNameConverter  implements SingleValueConverter {

    public Object fromString(String aClassName) {
        return new ClassName(aClassName);
    }

    public String toString(Object aClassNameObject) {
        return ((ClassName) aClassNameObject).classname();
    }

	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class aClassType) {
        return aClassType.equals(ClassName.class);
    }

}
