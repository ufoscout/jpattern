package com.jpattern.ioc.xml;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/ott/08 17:53:01
 * @version $Id: $
 */
public class Key {
        
        private String key;

        public Key(String akey) {
            
            key=  akey;
        }

        public String key() {
            return key;
        }

        public int hashCode() {
           return key.hashCode();
        }

        public boolean equals(Object obj) {
            if ( !(obj instanceof Key))
                return false;
            final Key other = (Key) obj;
            return key.equals(other.key);
        }

}
