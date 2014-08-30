package com.jpattern.ioc.xml;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.xml.typebuilder.ITypeBuilder;
import com.jpattern.ioc.xml.typebuilder.TypeFactory;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:15
 * @version $Id: $
 */
public class Entry   implements IEntry  {
    private Value value;
    private  Key key;
	private Type type;

    public Entry(Key aKey, Value aValue) {
        this(aKey, aValue, new Type("String"));
    }
    
    public Entry(Key aKey, Value aValue, Type aType) {
        key = aKey;
        value = aValue;
        type = aType;
    }

    public Key getKey() {
        return key;
    }        

    private Value getValue() {
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

    public int hashCode() {
        return key.hashCode();
    }

    public boolean equals(Object obj) {
        if ( !(obj instanceof IEntry))
            return false;
        final IEntry other = (IEntry) obj;
        return key.equals( other.getKey() );
    }

}
