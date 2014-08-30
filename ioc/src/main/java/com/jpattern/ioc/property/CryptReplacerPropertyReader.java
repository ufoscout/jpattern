package com.jpattern.ioc.property;

import com.jpattern.ioc.IEncrypter;
import com.jpattern.ioc.desencrypter.DesEncrypter;

public class CryptReplacerPropertyReader  extends ReplacerPropertyReader  {
    
    private static final String PREFIX ="$CRYPT{";
    
    
    private IEncrypter encrypter;
    
    public String property(String aProperty) {
        return evalueProperty(aProperty); 
    }

    private String evalueProperty(String aProperty) {
        if (aProperty.startsWith(PREFIX)) {
            aProperty = super.property(parse(aProperty, PREFIX, SUFFIX));
            aProperty = getEncrypter().decrypt(aProperty);
        } else {
            aProperty = super.property(aProperty);
        }
        return aProperty;
    }

    public IEncrypter getEncrypter() {
       if (encrypter==null)
                encrypter = new DesEncrypter();
        return encrypter;
    }

    public void setEncrypter(IEncrypter encrypter) {
        this.encrypter = encrypter;
    }
}
