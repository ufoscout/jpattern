package com.jpattern.core.textfiles;

import java.io.File;
import java.util.Date;


import com.jpattern.core.BaseTest;
import com.jpattern.core.textfiles.IFile;
import com.jpattern.core.textfiles.IFileReader;
import com.jpattern.core.textfiles.local.LocalResource;
import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/giu/2010
 */
public class CharsetEncodingTest extends BaseTest {

	
	private String filename;
	private LocalResource resource;

	protected void setUp() throws Exception {
		super.setUp();
		
		String path = "target/resource/in";
		
		File file = new File(path);
		if (!file.exists()) { file.mkdirs(); }
		
		System.out.println("test path: " + path);
		resource = new LocalResource( path , CharacterEncoding.ISO_8859_1 );
		assertTrue( resource.isValid() );
		
		filename = "testCharsetFile" + new Date().getTime() + ".txt";
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		assertTrue( resource.delete(filename) );
		
	}
	
	public void testCharset1() throws Exception {
		String testString = "èòàù@#°§ù";
		
		IFile file = resource.create(filename, testString, CharacterEncoding.UTF_8);
		
		IFileReader fileReader = file.getFileReader();
		
		String readString = fileReader.getFullText();
		fileReader.close();
		
		System.out.println("expected string: " + testString);
		System.out.println("read string: " + readString);
		
		assertEquals( testString , readString );
		
	}

	public void testCharset2() throws Exception {
		String testString = "èòàù@#°§ù";
		
		resource.create(filename, testString);
		
		IFile file = resource.getFile(filename, CharacterEncoding.UTF_16 );
		
		IFileReader fileReader = file.getFileReader();
		
		String readString = fileReader.getFullText();
		fileReader.close();
		
		System.out.println("expected string: " + testString);
		System.out.println("read string: " + readString);
		
		assertFalse( testString.equals( readString ) );
		
	}
	
	
	public void testCharset_UTF_8() throws Exception {
		String testString = "èòàù@#°§ù";
		
		resource.create(filename, testString, CharacterEncoding.UTF_8);
		
		IFileReader fileUTF8_ok = resource.getFile(filename, CharacterEncoding.UTF_8 ).getFileReader();
		IFileReader fileUTF16_WrongCharset = resource.getFile(filename, CharacterEncoding.UTF_16 ).getFileReader();
		IFileReader fileISO88591_WrongCharset = resource.getFile(filename, CharacterEncoding.ISO_8859_1 ).getFileReader();
		IFileReader fileCP1252_WrongCharset = resource.getFile(filename, CharacterEncoding.WINDOWS_1252 ).getFileReader();
		
		String utf8 = fileUTF8_ok.getFullText();
		String iso88591 = fileISO88591_WrongCharset.getFullText();
		String utf16 = fileUTF16_WrongCharset.getFullText();
		String cp1252 = fileCP1252_WrongCharset.getFullText();
		
		fileUTF8_ok.close();
		fileISO88591_WrongCharset.close();
		fileUTF16_WrongCharset.close();
		fileCP1252_WrongCharset.close();
		
		System.out.println("expected string in UTF-8: " + utf8);
		System.out.println("read string in UTF-16: " + utf16);
		System.out.println("read string in ISO-8859-1: " + iso88591);
		System.out.println("read string in CP-1252: " + cp1252);
		
		assertTrue( testString.equals( utf8 ) );
		assertFalse( testString.equals( iso88591 ) );
		assertFalse( testString.equals( utf16 ) );
		assertFalse( testString.equals( cp1252 ) );
		
	}
	
	public void testCharset_UTF_16() throws Exception {
		String testString = "èòàù@#°§ù";
		
		resource.create(filename, testString, CharacterEncoding.UTF_16);
		
		IFileReader fileUTF8_WrongCharset = resource.getFile(filename, CharacterEncoding.UTF_8 ).getFileReader();
		IFileReader fileUTF16_Ok = resource.getFile(filename, CharacterEncoding.UTF_16 ).getFileReader();
		IFileReader fileISO88591_WrongCharset = resource.getFile(filename, CharacterEncoding.ISO_8859_1 ).getFileReader();
		IFileReader fileCP1252_WrongCharset = resource.getFile(filename, CharacterEncoding.WINDOWS_1252 ).getFileReader();
		
		String utf8 = fileUTF8_WrongCharset.getFullText();
		String iso88591 = fileISO88591_WrongCharset.getFullText();
		String utf16 = fileUTF16_Ok.getFullText();
		String cp1252 = fileCP1252_WrongCharset.getFullText();
		
		fileUTF8_WrongCharset.close();
		fileUTF16_Ok.close();
		fileISO88591_WrongCharset.close();
		fileCP1252_WrongCharset.close();
		
		System.out.println("expected string in UTF-8: " + utf8);
		System.out.println("read string in UTF-16: " + utf16);
		System.out.println("read string in ISO-8859-1: " + iso88591);
		System.out.println("read string in CP-1252: " + cp1252);
		
		assertFalse( testString.equals( utf8 ) );
		assertFalse( testString.equals( iso88591 ) );
		assertTrue( testString.equals( utf16 ) );
		assertFalse( testString.equals( cp1252 ) );
		
	}
	
	
	public void testCharset_ISO_8859_1() throws Exception {
		String testString = "èòàù@#°§ù";
		
		resource.create(filename, testString, CharacterEncoding.ISO_8859_1);
		
		IFileReader fileUTF8_WrongCharset = resource.getFile(filename, CharacterEncoding.UTF_8 ).getFileReader();
		IFileReader fileUTF16_WrongCharset = resource.getFile(filename, CharacterEncoding.UTF_16 ).getFileReader();
		IFileReader fileISO88591_Ok = resource.getFile(filename, CharacterEncoding.ISO_8859_1 ).getFileReader();
		
		String utf8 = fileUTF8_WrongCharset.getFullText();
		String iso88591 = fileISO88591_Ok.getFullText();
		String utf16 = fileUTF16_WrongCharset.getFullText();
		
		fileUTF8_WrongCharset.close();
		fileUTF16_WrongCharset.close();
		fileISO88591_Ok.close();
		
		System.out.println("expected string in UTF-8: " + utf8);
		System.out.println("read string in UTF-16: " + utf16);
		System.out.println("read string in ISO-8859-1: " + iso88591);
		
		assertFalse( testString.equals( utf8 ) );
		assertTrue( testString.equals( iso88591 ) );
		assertFalse( testString.equals( utf16 ) );
		
	}
//	public void testListAvailableCharset() throws Exception {
//	    
//		Map<String, Charset> cs = Charset.availableCharsets();
//	    
//		System.out.printf("%d charsets:%n", cs.size());
//	    
//		for (String s : cs.keySet())
//	    {
//	      System.out.println(s);
//	    }
//	}
	
}
