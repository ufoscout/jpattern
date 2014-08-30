package com.jpattern.ioc.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

public class EntryMap  implements IEntry  {
        
        private List<IEntry> entries = new ArrayList<IEntry>();
        private  Key key;
        
        public EntryMap(Key aKey) {
            key = aKey;
        }

        public Key getKey() {
            return key;
        }    
        
        public void add(Entry aEntry) {
            entries.add(aEntry);
        }

        public List<IEntry> getEntries() {
            return entries;
        }

        public Object typedValue(IContextCreator aContextCreator) throws ConfigException, ClassNotFoundException {
            Map<String, Object> returnedMap = new HashMap<String, Object>();
            ListIterator<IEntry> entriesIterator = getEntries().listIterator();
            IEntry iEntry;
            while (entriesIterator.hasNext()) {
                iEntry = (IEntry)entriesIterator.next();
                returnedMap.put(iEntry.getKey().key(), iEntry.typedValue(aContextCreator));
            }
            return returnedMap;
        }

        public int hashCode() {
            return key.hashCode();
        }


        public boolean equals(Object obj) {
            if ( !(obj instanceof IEntry))
                return false;            
            final IEntry other = (IEntry) obj;
            return key.equals( other.getKey() );
        }
       
        
}
