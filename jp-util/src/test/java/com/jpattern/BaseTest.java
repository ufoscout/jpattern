package com.jpattern;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.jpattern.util.Chronometer;
import com.jpattern.util.FileUtil;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/nov/2009
 */
public abstract class BaseTest {

	@Rule
	public TestName testName = new TestName();

	private String testFilesInputBasePath = "./src/test/files";
	private String testFilesOutputBasePath = "./target/test/files";

	private Chronometer chronometer = new Chronometer();

	@Before
	public void setUpBeforeEachTest() throws Exception {

		chronometer.reset();
		chronometer.start();

		System.out.println("===================================================================");
		System.out.println("BEGIN TEST " + testName.getMethodName());
		System.out.println("===================================================================");

	}


	@After
	public void tearDownAfterEachTest() throws Exception {

		chronometer.pause();

		final String time = new BigDecimal(chronometer.read() ).divide(new BigDecimal(1000)).toString();

		System.out.println("===================================================================");
		System.out.println("END TEST " + testName.getMethodName());
		System.out.println("Execution time: " + time + " seconds");
		System.out.println("===================================================================");
	}

	protected String getTestInputBasePath() throws Exception {
		return testFilesInputBasePath;
	}

	protected String getTestOutputBasePath() throws Exception {
		FileUtil.createDirectoriesTreeIfNotExist(testFilesOutputBasePath);
		return testFilesOutputBasePath;
	}

}