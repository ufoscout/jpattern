package com.jpattern.ioc.xml;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/nov/2010
 */
public class ExtraContext {
    private Value value;

    public ExtraContext(Value aValue) {
        value = aValue;
    }

    public Value getValue() {
        return value;
    }

    public int hashCode() {
        return value.hashCode();
    }

    public boolean equals(Object obj) {
        if ( !(obj instanceof ExtraContext))
            return false;
        final ExtraContext other = (ExtraContext) obj;
        return value.value().equals( other.getValue().value() );
    }
    
}
