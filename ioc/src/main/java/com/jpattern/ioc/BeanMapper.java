package com.jpattern.ioc;

import java.util.HashMap;
import java.util.Map;

public class BeanMapper {

    private Map<String, Object> _beansMap ;
    public BeanMapper() {
        _beansMap = new HashMap<String, Object>();
    }
    
    public void put(String aKey, Object aBean) {
        _beansMap.put(aKey, aBean);
    }
    
    public Object get(String aKey) {
        return _beansMap.get(aKey);
    }    
    
    public boolean contains(String aKey) {
        return _beansMap.containsKey(aKey);
    }
    
}
