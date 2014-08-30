package com.jpattern.ioc.xml;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 25/ott/08 12:31:41
 * @version $Id: $
 */
public class EntryRef implements IEntry {
    private Value value;
    private  Key key;

    public EntryRef(Key aKey, Value aValue) {
        key = aKey;
        value = aValue;
    }
       

    public Key getKey() {
        return key;
    }        

    public Value getValue() {
        return value;
    }

    public Object typedValue(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
        return aContextCreator.create( aContextCreator.readProperty( getValue().value() ));
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
