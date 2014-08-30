package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public abstract class ATypeBuilder implements ITypeBuilder {

    private static final long serialVersionUID = 1L;


    private ITypeBuilder _successor;

    public ATypeBuilder( ITypeBuilder aSuccessor ) {
    	_successor = aSuccessor;
    }

    public ResultType exec(String typeName, String value) {
    	ResultType resultType = _successor.exec(typeName, value);
    	if ( resultType.isValid() ) {
    		return resultType;
    	}
        return result(typeName, value);
    }

    protected abstract ResultType result(String typeName, String value);

}
