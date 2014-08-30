package com.jpattern.core.util;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class CharacterEncoding implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * Per ottenere la lista completa dei Charset disponibili utilizzare il comando:
	 * Map<String, Charset> cs = Charset.availableCharsets();
	 */
	public String UTF_8_ENCODING = "UTF-8";
	public String UTF_16_ENCODING = "UTF-16";
	public String ISO_8859_1_ENCODING = "ISO-8859-1";
	public String WINDOWS_1252_ENCODING = "windows-1252";

	public static CharacterEncoding UTF_8 = new CharacterEncoding("UTF-8");
	public static CharacterEncoding UTF_16 = new CharacterEncoding("UTF-16");
	public static CharacterEncoding ISO_8859_1 = new CharacterEncoding("ISO-8859-1");
	public static CharacterEncoding WINDOWS_1252 = new CharacterEncoding("windows-1252");
	
	private String charset = "";
	
	public CharacterEncoding( String charset ) {
		this.charset = charset;
	}
	
	public String getCharset() {
		return charset;
	}
}
