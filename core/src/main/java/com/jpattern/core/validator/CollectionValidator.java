package com.jpattern.core.validator;

import java.util.Collection;
import java.util.List;

import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 26/ago/09 20:41:58
 *
 * @version $Id$
 */
public class CollectionValidator extends AValidator implements IValidator {
    private Collection<String> _collection;

    private String _stringToCheck;

    public CollectionValidator(Collection<String> aCollection, String aString, List<IErrorMessage> validateMessages) {
        super(validateMessages);
        _collection = aCollection;
        _stringToCheck = aString;

    }

    public void validate() {
        validateContains();
    }

    private void validateContains() {
        if (!_collection.contains(_stringToCheck)) {
            add(new ErrorMessage("role", "not present in application context"));
        }

    }

}
