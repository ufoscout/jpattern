package com.jpattern.ioc.reflection;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.jpattern.ioc.xml.BeanContext;



public class BeanContextReflection {
    
    private Class<?> _class;
    private Map<String, MethodDescriptor> _methodsMap;
    private BeanContext _beanContext;
    
    public BeanContextReflection(BeanContext aBeanContext) throws ClassNotFoundException {
        _beanContext = aBeanContext;
        _class = aBeanContext.getClassname().typedValues();
        _methodsMap = new HashMap<String, MethodDescriptor>();
        parse();
    }
    
    private void parse() {
        Method[] methods = _class.getMethods();
        for (int i = 0 ; i < methods.length; i++) {
            parse(methods[i]);
        }
    }
    
    public MethodDescriptor getDescriptor(String propertyName) {
        return _methodsMap.get(propertyName);
    }
    
    private void parse( Method aMethod) {
        String methodName= aMethod.getName();
        String methodNameWithoutPrefix = methodName.substring(3, methodName.length());
        String attributeNameWithoutPrefix = methodNameWithoutPrefix.substring(0, 1).toLowerCase() + methodNameWithoutPrefix.substring(1, methodNameWithoutPrefix.length());        
        if ( _beanContext.containsEntryProperty(attributeNameWithoutPrefix) ) {
            MethodDescriptor methodDescriptor = methodDescriptorBuild(aMethod, attributeNameWithoutPrefix);
            setter(aMethod, methodName, methodDescriptor);
            getter(aMethod, methodName, methodDescriptor);            
        }
    }
    private void getter(Method aMethod, String methodCase, MethodDescriptor methodDescriptor) {
        if (methodCase.startsWith("get")) {
            methodDescriptor.setGetter(aMethod);
        }
    }
    private void setter(Method aMethod, String methodName, MethodDescriptor methodDescriptor) {
        if (methodName.startsWith("set")) {
            methodDescriptor.setSetter(aMethod);
        }
    }

    private MethodDescriptor methodDescriptorBuild(Method aMethod, String methodNameWithoutPrefix) {
        MethodDescriptor methodDescriptor;
        if (_methodsMap.containsKey(methodNameWithoutPrefix)) {
            methodDescriptor = _methodsMap.get(methodNameWithoutPrefix);
        } else {
            methodDescriptor = new MethodDescriptor(methodNameWithoutPrefix);
            methodDescriptor.setParameterTypes(aMethod.getParameterTypes());
            _methodsMap.put(methodNameWithoutPrefix, methodDescriptor);
        }
        return methodDescriptor;
    }    
}
