package com.jpattern.ioc.xml;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:10
 * @version $Id: $
 */
public class Id  {

    public Id(String aId) {
     
        id = aId;
    }
    
    private String id;

    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object obj) {
        final Id other = (Id) obj;
        return id.equals(other.id);
    }

    public String id() {
        return id;
    }


}
