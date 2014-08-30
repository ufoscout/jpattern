package com.jpattern.ioc.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.jpattern.ioc.IContextCreator;
import com.jpattern.ioc.exception.ConfigException;

public class EntryList implements IEntry  {
        
        private List<IEntry> entries = new ArrayList<IEntry>();
        private  Key key;
        
        public EntryList(Key aKey) {
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
            List<Object> returnedList = new ArrayList<Object>();
            ListIterator<IEntry> entriesIterator = getEntries().listIterator();
            IEntry iEntry;
            while (entriesIterator.hasNext()) {
                iEntry = (IEntry) entriesIterator.next();
                returnedList.add(iEntry.typedValue(aContextCreator));
            }
            return returnedList;
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
