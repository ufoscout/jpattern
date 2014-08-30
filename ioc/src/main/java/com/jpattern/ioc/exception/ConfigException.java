package com.jpattern.ioc.exception;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 24/ott/08 17:02:15
 * @version $Id: $
 */
public class ConfigException extends Exception {

    public ConfigException() {
        super();
    }

    public ConfigException(String aMessage, Throwable aThrowable) {
        super(aMessage, aThrowable);
    }

    public ConfigException(String aMessage) {
        super(aMessage);
    }

    public ConfigException(Throwable aThrowable) {
        super(aThrowable);
    }

    private static final long serialVersionUID = 1L;

}
