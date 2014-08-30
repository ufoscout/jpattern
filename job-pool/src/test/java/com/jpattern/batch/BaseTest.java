package com.jpattern.batch;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.jpattern.batch.logger.JobPoolLogger;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
@RunWith(BlockJUnit4ClassRunner.class)
public abstract class BaseTest {

	public static String TEST_FILES_BASE_PATH = "./src/test/files";
	@Rule public final TestName testName = new TestName();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUpBeforeTest() throws Exception {

		System.out.println("===================================================================");
		System.out.println("BEGIN TEST " + testName.getMethodName());
		System.out.println("===================================================================");

		setUp();
	}


	@After
	public void tearDownAfterTest() throws Exception {
		tearDown();

		System.out.println("===================================================================");
		System.out.println("END TEST " + testName.getMethodName());
		System.out.println("===================================================================");
	}

	protected abstract void setUp() throws Exception;
	protected abstract void tearDown() throws Exception;

	protected final ILogger getLogger() {
		return JobPoolLogger.getLogger(getClass());
	}
}