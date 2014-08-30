package com.jpattern.ioc.property;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ott/08 11:58:49
 * @version $Id: $
 */
public class ReplacerPropertyReader extends PropertyReader {
    
    protected static final String SUFFIX ="}";
    private static final String PREFIX ="${";
    
    
    
    public String property(String aProperty) {
        return evalueProperty(aProperty); 
    }

    private String evalueProperty(String aProperty) {
        if (aProperty.startsWith(PREFIX)) {
            aProperty= parse(aProperty, PREFIX, SUFFIX);
        }
        return super.property(aProperty);
    }


}
