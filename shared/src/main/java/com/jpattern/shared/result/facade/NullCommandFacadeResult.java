package com.jpattern.shared.result.facade;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 25 Feb 2011
 */
public class NullCommandFacadeResult<T> implements ICommandFacadeResult<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public List<IErrorMessage> getErrorMessages() {
        return new ArrayList<IErrorMessage>();
    }

    @Override
    public T getReturnedObject() {
        return null;
    }

}
