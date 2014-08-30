package com.jpattern.ioc.xml;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

public interface IEntry {

    Key getKey() ;
    
    int hashCode();

    boolean equals(Object obj);    
    
    Object typedValue(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException;
}
