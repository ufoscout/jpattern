package com.jpattern.cache;

import java.io.File;

import junit.framework.TestCase;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/nov/2009
 */
public abstract class BaseTest extends TestCase {

	private String testFilesInputBasePath = "./src/test/files";
	private String testFilesOutputBasePath = "./target/test/files";
	
    public BaseTest() {
    }

    public BaseTest(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        System.out.println("===================================================================");
        System.out.println("BEGIN TEST " + getName());
        System.out.println("===================================================================");

    }


	protected void tearDown() throws Exception {
        super.tearDown();
        
        System.out.println("===================================================================");
        System.out.println("END TEST " + getName());
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