package com.jpattern.jobexecutor.iostream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.iostream.IInputReader;
import com.jpattern.jobexecutor.iostream.IOutputWriter;
import com.jpattern.jobexecutor.iostream.StreamInputReader;
import com.jpattern.jobexecutor.iostream.StreamOutputWriter;


/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class IInputReaderTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testStreamInputOutput() throws Exception {
		InputMock inputMock = new InputMock();
		OutputMock outputMock = new OutputMock();
		
		IInputReader reader = new StreamInputReader( inputMock );
		IOutputWriter writer = new StreamOutputWriter( outputMock );
		
		String readed = "leggere da console";
		writer.write("1");
		readed = reader.read();
		writer.write(readed);
		
		assertTrue( inputMock.isCalled() );
		assertTrue( outputMock.isCalled() );
		
	}
	
	
	class InputMock extends InputStream {

		Integer counter = Integer.valueOf(0);
		private boolean readCalled = false; 
		
		@Override
		public int read() throws IOException {
			counter++;
			readCalled = true;
			return counter.intValue();
		}
		
		boolean isCalled() {
			return readCalled;
		}
		
	}
	
	class OutputMock extends OutputStream {

		boolean writeCalled = false;
		StringBuffer message = new StringBuffer();
		
		public String getMessage() {
			String result = message.toString();
			message = new StringBuffer();
			return result;
		}

		@Override
		public void write(int b) throws IOException {
			System.out.println(b);	
			writeCalled = true;
		}
		
		boolean isCalled() {
			return writeCalled;
		}
	}

}
