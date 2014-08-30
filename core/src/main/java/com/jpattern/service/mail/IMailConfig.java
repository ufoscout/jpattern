package com.jpattern.service.mail;

import java.io.Serializable;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 20:29:35
 *
 * @version $Id$
 */
public interface IMailConfig extends Serializable {

    String getStmphost();
    
    String getStmpport();
    
    String getUsername();
    
    String getPassword();
    
    boolean isRequiredAuthentication();
}
