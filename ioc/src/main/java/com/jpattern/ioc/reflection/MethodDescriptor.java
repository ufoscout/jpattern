package com.jpattern.ioc.reflection;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 24/ott/08 13:06:47
 * @version $Id: $
 */
public class MethodDescriptor implements Serializable {

    private static final long serialVersionUID = 1L;

    public MethodDescriptor(String aName) {

        name = aName;
    }

    private String name;
    private Method setter;
    private Method getter;
    private Class<?>[] parameterTypes;

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    public String getName() {
        return name;
    }

}
