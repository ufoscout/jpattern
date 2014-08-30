package com.jpattern.ioc.xml;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Francesco Cina'
 *
 * 18/dic/2009
 */
public interface IParameter {

    Object typedValue(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException;
    
    Class<?> typedClass(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException;
}
