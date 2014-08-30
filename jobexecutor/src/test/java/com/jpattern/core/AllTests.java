package com.jpattern.core;


import com.jpattern.jobexecutor.console.ConsoleManagerExecutorCommandTest;
import com.jpattern.jobexecutor.console.UnknownCommandExecutorHandlerTest;
import com.jpattern.jobexecutor.core.ExecutableJobTest;
import com.jpattern.jobexecutor.core.JobThreadPoolTest;
import com.jpattern.jobexecutor.execution.RunOnceJobExecutionStrategyTest;
import com.jpattern.jobexecutor.execution.SleepingIntervalJobExecutionStrategyTest;
import com.jpattern.jobexecutor.iostream.ICommunicationChannelTest;
import com.jpattern.jobexecutor.iostream.IInputReaderTest;
import com.jpattern.jobexecutor.socket.ASocketTest;
import com.jpattern.jobexecutor.socket.IdentifySocketStrategyDecoratorTest;
import com.jpattern.jobexecutor.socket.JobThreadPoolCommunicatorManagerTest;
import com.jpattern.jobexecutor.socket.ShutdownStrategyTest;
import com.jpattern.jobexecutor.socket.SocketServerTest;
import com.jpattern.jobexecutor.socket.StartStrategyTest;
import com.jpattern.jobexecutor.socket.StopStrategyTest;
import com.jpattern.jobexecutor.socket.StressStartStopStrategyTest;
import com.jpattern.jobexecutor.socket.starter.ClientExecutorTest;
import com.jpattern.jobexecutor.socket.starter.PropertiesFileAdminSocketPortReaderTest;
import com.jpattern.jobexecutor.socket.starter.SocketActionFactoryTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
public class AllTests extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AllTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("TEST SUITE FOR executor Framework");
        
        suite.addTestSuite( ClientExecutorTest.class );
        suite.addTestSuite( IInputReaderTest.class );
        suite.addTestSuite( ICommunicationChannelTest.class );
        suite.addTestSuite( ExecutableJobTest.class );
        suite.addTestSuite( ASocketTest.class );
        suite.addTestSuite( JobThreadPoolTest.class );
        suite.addTestSuite( JobThreadPoolCommunicatorManagerTest.class );
        suite.addTestSuite( SocketServerTest.class );
        suite.addTestSuite( StartStrategyTest.class );
        suite.addTestSuite( StopStrategyTest.class );
        suite.addTestSuite( ShutdownStrategyTest.class );
        suite.addTestSuite( IdentifySocketStrategyDecoratorTest.class );
        suite.addTestSuite( UnknownCommandExecutorHandlerTest.class );
        suite.addTestSuite( StressStartStopStrategyTest.class );
        suite.addTestSuite( RunOnceJobExecutionStrategyTest.class );
        suite.addTestSuite( SocketActionFactoryTest.class );
        suite.addTestSuite( SleepingIntervalJobExecutionStrategyTest.class );
        suite.addTestSuite( ConsoleManagerExecutorCommandTest.class );
        suite.addTestSuite( PropertiesFileAdminSocketPortReaderTest.class );
        return suite;
    }
}
