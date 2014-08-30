package com.jpattern.ioc;

import java.io.Serializable;

import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 25/ott/08 12:06:07
 * @version $Id: $
 */
public interface IContextCreator extends Serializable {
    
    String REPLACER_BEAN_ID = "jodreplacer";

    Object create(String beanIdName) throws ConfigException, ClassNotFoundException;
    
    String readProperty(String aProperty);
}
