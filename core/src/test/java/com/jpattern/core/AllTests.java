package com.jpattern.core;

import com.jpattern.core.chain.ChainOfResponsibilityBlockingTest;
import com.jpattern.core.chain.ChainOfResponsibilityMixedTest;
import com.jpattern.core.chain.ChainOfResponsibilityUnblockingTest;
import com.jpattern.core.chain.NullChainElementTest;
import com.jpattern.core.command.AAsyncCommandTest;
import com.jpattern.core.command.ACommandExceptionTest;
import com.jpattern.core.command.ACommandProviderTest;
import com.jpattern.core.command.ACommandTest;
import com.jpattern.core.command.CommandCatchFalseTest;
import com.jpattern.core.command.CommandChainTest;
import com.jpattern.core.command.ICommandRollbackTest;
import com.jpattern.core.command.ICommandTest;
import com.jpattern.core.command.PoolAsyncCommandExecutorTest;
import com.jpattern.core.command.SpikeListCommandContainsTest;
import com.jpattern.core.textfiles.CharsetEncodingTest;
import com.jpattern.core.textfiles.FileCopyCommandTest;
import com.jpattern.core.textfiles.FileCreateCommandTest;
import com.jpattern.core.textfiles.FileDeleteCommandTest;
import com.jpattern.core.textfiles.FileRenameCommandTest;
import com.jpattern.core.textfiles.local.LocalFileReaderTest;
import com.jpattern.core.textfiles.local.LocalFileWriterTest;
import com.jpattern.core.textfiles.local.LocalResourceTest;
import com.jpattern.core.util.CollectionUtilTest;
import com.jpattern.core.util.DateUtilTest;
import com.jpattern.core.util.FileUtilTest;
import com.jpattern.core.util.MapUtilTest;
import com.jpattern.core.util.SizeOfUtilTest;
import com.jpattern.core.util.ValueUtilTest;
import com.jpattern.core.util.ZipUtilTest;
import com.jpattern.core.xml.XmlElementTest;
import com.jpattern.core.xml.XmlReaderCommandTest;
import com.jpattern.core.xml.XmlValidatorTest;
import com.jpattern.core.xml.XmlWriterCommandTest;
import com.jpattern.service.log.AExecutorTest;
import com.jpattern.service.log.DefaultMessageFormatterTest;
import com.jpattern.service.log.ILoggerServiceTest;
import com.jpattern.service.log.MailExecutorTest;
import com.jpattern.service.log.RosterTest;
import com.jpattern.service.log.event.EventTest;
import com.jpattern.service.log.event.EventTest2;
import com.jpattern.service.log.event.MessageExceptionTest;
import com.jpattern.service.log.file.FileExecutorTest;
import com.jpattern.service.log.file.FileWriterTest;
import com.jpattern.service.log.file.RollingFileExecutorTest;
import com.jpattern.service.log.slf4j.Slf4JExecutorTest;
import com.jpattern.service.log.reader.EventQueueTest;
import com.jpattern.service.log.reader.LoggerReaderServiceTest;
import com.jpattern.service.log.reader.MessageReaderTest;
import com.jpattern.service.log.reader.QueueMessagesTest;
import com.jpattern.service.log.reader.TailCommandTest;
import com.jpattern.service.log.reader.UtilizzoLoggerReaderTest;
import com.jpattern.service.mail.MailSendTest;

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
        TestSuite suite = new TestSuite("TEST SUITE FOR jPattern Framework");
        
        suite.addTestSuite( ChainOfResponsibilityBlockingTest.class );
        suite.addTestSuite( ChainOfResponsibilityUnblockingTest.class );
        suite.addTestSuite( ChainOfResponsibilityMixedTest.class );
        suite.addTestSuite( NullChainElementTest.class );
        suite.addTestSuite( ICommandTest.class );
        suite.addTestSuite( ICommandRollbackTest.class );
        suite.addTestSuite( EventTest.class );
        suite.addTestSuite( MessageExceptionTest.class );
        suite.addTestSuite( RosterTest.class );
        suite.addTestSuite( DefaultMessageFormatterTest.class );
        suite.addTestSuite( ILoggerServiceTest.class );
        suite.addTestSuite( MessageReaderTest.class );
        suite.addTestSuite( LoggerReaderServiceTest.class);
        suite.addTestSuite( QueueMessagesTest.class );
        suite.addTestSuite( UtilizzoLoggerReaderTest.class );
        suite.addTestSuite( EventQueueTest.class );
        suite.addTestSuite( EventTest2.class );
        suite.addTestSuite( FileWriterTest.class );
        suite.addTestSuite( RollingFileExecutorTest.class );
        suite.addTestSuite( FileExecutorTest.class );
        suite.addTestSuite( XmlValidatorTest.class );
        suite.addTestSuite( LocalResourceTest.class );
        suite.addTestSuite( LocalFileWriterTest.class );
        suite.addTestSuite( LocalFileReaderTest.class );
        suite.addTestSuite( FileCopyCommandTest.class );
        suite.addTestSuite( FileCreateCommandTest.class );
        suite.addTestSuite( FileDeleteCommandTest.class );
        suite.addTestSuite( CharsetEncodingTest.class );
        suite.addTestSuite( XmlReaderCommandTest.class );
        suite.addTestSuite( XmlWriterCommandTest.class );
        suite.addTestSuite( XmlElementTest.class );
        suite.addTestSuite( FileRenameCommandTest.class );
        suite.addTestSuite( AExecutorTest.class );
        suite.addTestSuite( MailExecutorTest.class );
        suite.addTestSuite( Slf4JExecutorTest.class );
        suite.addTestSuite( MailSendTest.class );
        suite.addTestSuite( AAsyncCommandTest.class );
        suite.addTestSuite( SpikeListCommandContainsTest.class );
        suite.addTestSuite( PoolAsyncCommandExecutorTest.class );
        suite.addTestSuite( ACommandProviderTest.class );
        suite.addTestSuite( ACommandExceptionTest.class );
        suite.addTestSuite( TailCommandTest.class );
        suite.addTestSuite( ZipUtilTest.class );
        suite.addTestSuite( CollectionUtilTest.class );
        suite.addTestSuite( DateUtilTest.class );
        suite.addTestSuite( FileUtilTest.class );
        suite.addTestSuite( MapUtilTest.class );
        suite.addTestSuite( ValueUtilTest.class );
        suite.addTestSuite( CommandChainTest.class );
        suite.addTestSuite( ACommandTest.class );
        suite.addTestSuite( CommandCatchFalseTest.class );
        suite.addTestSuite( SizeOfUtilTest.class );
        
        return suite;
    }
}
