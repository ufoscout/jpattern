package com.jpattern.ioc.xml.typebuilder;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public interface ITypeBuilder extends Serializable  {
	
	public static String INTEGER = "Integer";
	public static String DOUBLE = "Double";
	public static String STRING = "String";
	public static String STRINGBUFFER = "StringBuffer";
	public static String SHORT = "Short";
	public static String BYTE = "Byte";
	public static String LONG = "Long";
	public static String FLOAT = "Float";
	public static String BOOLEAN = "Boolean";
	public static String CHARACTER = "Character";
	public static String BIGINTEGER = "BigInteger";
	public static String BIGDECIMAL = "BigDecimal";
	
	public static String PRIMITIVE_INT = "int";
	public static String PRIMITIVE_DOUBLE = "double";
	public static String PRIMITIVE_SHORT = "short";
	public static String PRIMITIVE_BYTE = "byte";
	public static String PRIMITIVE_LONG = "long";
	public static String PRIMITIVE_FLOAT = "float";
	public static String PRIMITIVE_BOOLEAN = "boolean";
	public static String PRIMITIVE_CHAR = "char";
	
	
            
	ResultType exec(String typeName, String value);
    
}
