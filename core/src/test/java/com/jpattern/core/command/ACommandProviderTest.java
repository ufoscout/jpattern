package com.jpattern.core.command;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.NullProvider;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/gen/2011
 */
public class ACommandProviderTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCommandProvider() throws Exception {
		CommandChain<IProvider> command = new CommandChain<IProvider>();
		command.addCommand( new MyCommand("ONE"));
		command.addCommand( new MyCommand("TWO"));
		command.addCommand( new MyCommand("THREE"));
		command.addCommand( new MyCommand("FOUR"));
		command.addCommand( new MyCommand("FIVE"));
		
		IProvider provider = getProvider();
		assertFalse(provider instanceof NullProvider);
		
		assertTrue( command.exec(getProvider()).isValid() );
	}
	
	class MyCommand extends ACommand<IProvider> {
		private final String name;

		public MyCommand(String name) {
			this.name = name;
		}

		@Override
		protected void execute(ACommandResult result) {
			boolean nullProvider = (getProvider() instanceof NullProvider);
			System.out.println("Is IProvider of " + name + " a null object? " + nullProvider);
			assertFalse( nullProvider );
		}

		@Override
		protected void rollback(ACommandResult rollBackResult) {
		}
		
	}
	
}
