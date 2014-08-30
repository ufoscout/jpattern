package com.jpattern.ioc;


import com.jpattern.ioc.exception.ConfigException;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ott/08 11:58:24
 * @version $Id: $
 */
public interface IPropertyReader {

    void load(String basePath) throws ConfigException;
    String property(String aProperty);
}
