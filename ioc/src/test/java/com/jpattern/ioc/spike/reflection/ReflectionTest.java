package com.jpattern.ioc.spike.reflection;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.jpattern.ioc.model.xml.BeanUno;

import junit.framework.TestCase;

public class ReflectionTest extends TestCase {

    private HashMap<String, MethodEntity> _methodsMap;

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReplace() throws Exception  {
        String original = "setNoMe";
        
        String orginalLowerCase = original.toLowerCase();
        String orginalLowerCaseWithoutPrefix = orginalLowerCase.substring(3, orginalLowerCase.length());
        
        String originalWithoutPrefix = original.substring(3, original.length());
        
        assertEquals("nome", orginalLowerCaseWithoutPrefix);
        assertEquals("NoMe", originalWithoutPrefix);
        
        String attributeNameWithoutPrefix = originalWithoutPrefix.substring(0, 1).toLowerCase() + originalWithoutPrefix.substring(1, originalWithoutPrefix.length());
        
        assertEquals("noMe", attributeNameWithoutPrefix);
        //String attributeNameWithoutPrefix = methodNameWithoutPrefix.substring(1, 2);
    }
    
    public void testDummyReflection() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        
        Class<BeanUno> clazz = BeanUno.class;
        Object objBean = clazz.newInstance();
        
        
        
        Method m = clazz.getMethod( "setNome",        new Class[] { String.class });
         m.invoke(objBean, new Object[] { "Claudio" });
        
        assertEquals( "Claudio" , ( (BeanUno) objBean ).getNome() );
        
    }
    
    public void testDummyFindMethod() {
        
        Class<BeanUno> clazz = BeanUno.class;
        
        _methodsMap = new HashMap<String, MethodEntity>();
        
        
        Method[] method = clazz.getMethods();
        for (int i = 0 ; i< method.length; i++) {
        
                       // System.out.println(method[i].getName());
                       // System.out.println(method[i].getParameterTypes());
                        parse("nome", method[i]);
        
        }
        
        MethodEntity entity = _methodsMap.get("nome");
        
        assertEquals( "getNome" , entity.getGetter().getName());
        assertEquals( "setNome" , entity.getSetter().getName());
        
        
    }
    
    private void parse(String name, Method method) {
        String methodNameLowerCase= method.getName().toLowerCase();
        String methodNameLowerCaseWithoutPrefix = methodNameLowerCase.substring(3, methodNameLowerCase.length());
        
        System.out.println(methodNameLowerCaseWithoutPrefix);
        if ( methodNameLowerCaseWithoutPrefix.equals(name)   ) {  // esiste
            MethodEntity methodEntity;
            if (_methodsMap.containsKey(name)) {
                
                methodEntity = _methodsMap.get(name);
            } else {
                methodEntity = new MethodEntity(name);
                methodEntity.setParameterTypes(method.getParameterTypes());
                _methodsMap.put(name, methodEntity);
            }
            
            if (methodNameLowerCase.startsWith("set")) {
                
                methodEntity.setSetter(method);
            }
            if (methodNameLowerCase.startsWith("get")) {
                
                methodEntity.setGetter(method);
            }            
        }
    }
    
    public class MethodEntity implements Serializable {

        private static final long serialVersionUID = 1L;
        
        public MethodEntity(String aName) {
            
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
}
