package com.jpattern.core.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ago/09 21:02:52
 *
 * @version $Id$
 */
public class RegistryValidator implements IValidator {

    
    private List<IValidator> _validators;

    public RegistryValidator() {
            _validators = new ArrayList<IValidator>();
    }

    public void add(IValidator aValidator) {
        _validators.add( aValidator );
    }
    
    public void validate() throws Exception {
        Iterator<IValidator> validatorsItrator = _validators.listIterator();
        while (validatorsItrator.hasNext()) {
            forEach(validatorsItrator.next());
        }
    }

    private void forEach(Object aValidator) throws Exception {
        ((IValidator)aValidator).validate();
    }

}
