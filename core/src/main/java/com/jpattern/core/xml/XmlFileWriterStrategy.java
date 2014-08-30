package com.jpattern.core.xml;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.jpattern.core.util.CharacterEncoding;

/**
 * 
 * @author Francesco Cina'
 *
 * 16/giu/2010
 */
public class XmlFileWriterStrategy implements IXmlWriterStrategy {

	private final CharacterEncoding characterEncoding;
	private final OutputStream output;

	public XmlFileWriterStrategy( OutputStream output, CharacterEncoding characterEncoding ) {
		this.output = output;
		this.characterEncoding = characterEncoding;
	}
	
	public Writer getWriter() throws Exception {
		return new OutputStreamWriter(output, characterEncoding.getCharset() );
	}

	@Override
	public void close() throws Exception {
		output.close();		
	}

}
