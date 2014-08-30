package com.jpattern.ioc.property;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ott/08 11:58:44
 * @version $Id: $
 */

public class PropertyReader extends AbstractPropertyReader {
    
    public String property(String aProperty) {
        String returned =_properties.getProperty(aProperty);
        if (returned==null)
                returned = aProperty;
        return returned; 
    }
}
