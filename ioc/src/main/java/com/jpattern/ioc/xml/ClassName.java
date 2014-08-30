package com.jpattern.ioc.xml;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:30
 * @version $Id: $
 */
public class ClassName  {
    
        public ClassName(String aClassName) {
            
            classname = aClassName; 
        }
        
        private String classname;

        public String classname() {
            return classname;
        }
        
        public Class<?> typedValues() throws ClassNotFoundException {
            return  Class.forName(classname) ;
        }
}
