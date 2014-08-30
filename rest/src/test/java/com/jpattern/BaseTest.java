package com.jpattern;

import java.io.File;
import java.math.BigDecimal;

import com.jpattern.shared.util.Chronometer;

import junit.framework.TestCase;

/**
 * 
 * @author Francesco Cina
 *
 * 15/giu/2011
 */
public abstract class BaseTest extends TestCase {

	private String testFilesInputBasePath = "./src/test/files";
	private String testFilesOutputBasePath = "./target/test/files";
	
	Chronometer chronometer = new Chronometer();
    
    public BaseTest() {
    }

    public BaseTest(String name) {
        super(name);
    }
    
	protected void setUp() throws Exception {
		super.setUp();
		
		File path = new File("target/log");
		if (!path.exists()) {
			path.mkdirs();
		}
		
        chronometer.restart();
        
        System.out.println("===================================================================");
        System.out.println("BEGIN TEST " + getName());
        System.out.println("===================================================================");

    }


	protected void tearDown() throws Exception {
        super.tearDown();
        
        chronometer.pause();
        
        String time = new BigDecimal(chronometer.read() ).divide(new BigDecimal(1000)).toString();
        
        System.out.println("===================================================================");
        System.out.println("END TEST " + getName());
        System.out.println("Execution time: " + time + " seconds");
        System.out.println("===================================================================");
        
    }
	
	protected String getTestInputBasePath() throws Exception {
		return testFilesInputBasePath;
	}
	
	protected String getTestOutputBasePath() throws Exception {
		mkDir(testFilesOutputBasePath);
		return testFilesOutputBasePath;
	}
	
	protected void mkDir( String dirPath ) throws Exception {
		File path = new File(dirPath);
		if (!path.exists()) {
			path.mkdirs();
		}
	}
	
}