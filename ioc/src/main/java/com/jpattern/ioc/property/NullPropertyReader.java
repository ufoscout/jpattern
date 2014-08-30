package com.jpattern.ioc.property;

import com.jpattern.ioc.exception.ConfigException;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ott/08 12:22:43
 * @version $Id: $
 */
public class NullPropertyReader extends AbstractPropertyReader {

    public void load() throws ConfigException {
    }
    
    public String property(String aProperty) {
        return aProperty;
    }

}
