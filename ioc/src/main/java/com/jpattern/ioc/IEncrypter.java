package com.jpattern.ioc;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ott/08 15:49:08
 * @version $Id: $
 */
public interface IEncrypter {

    String encrypt(String aString);
    
    String decrypt(String aString);
}
