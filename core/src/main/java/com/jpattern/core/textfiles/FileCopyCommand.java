package com.jpattern.core.textfiles;

import java.util.Date;

import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/giu/2010
 */
public class FileCopyCommand extends ACommand<IProvider> {

	private ILogger logger;
	private StringBuffer sourceFileName;
	private IResource source;
	private IResource destination;
	private StringBuffer destinationFileName;
	
	public FileCopyCommand(StringBuffer sourceFileName, IResource source, StringBuffer destinationFileName, IResource destination) {
		this.sourceFileName = sourceFileName;
		this.source = source;
		this.destination = destination;
		this.destinationFileName = destinationFileName;
	}
	
	@Override
	protected void rollback(ACommandResult result) {
	}

	@Override
	protected void execute(ACommandResult result) {
		logger = getProvider().getLoggerService().logger(FileCopyCommand.class);
		logger.info("execute", "copy file " + sourceFileName.toString() + " from " + source.getPath() + " to " + destinationFileName.toString() + " in " + destination.getPath() );
		
		if ( !source.getFilenames().contains(sourceFileName.toString()) ) {
			logger.error("execute", "Impossible to copy file: source file doesn't exists");
			result.getErrorMessages().add( new ErrorMessage("FileCopyCommand", "Impossible to copy file: source file doesn't exists") );
			return;
		}
		
		if ( destination.getFilenames().contains(destinationFileName.toString()) ) {
			logger.error("execute", "Impossible to copy file: destination file with same name exists");
			result.getErrorMessages().add( new ErrorMessage("FileCopyCommand", "Impossible to copy file: destination file with same exists") );
			return;
		}
		
		FileWrapper destinationWrapper = new FileWrapper();
		StringBuffer temporaryName = new StringBuffer( destinationFileName.toString() );
		long now = new Date().getTime();
		temporaryName.append( "_temp_" + now );
		ACommand<IProvider> command = new FileCreateCommand(destination, temporaryName, new StringBuffer(), destinationWrapper);
		ACommandResult createResult = command.exec(getProvider());
		
		if ( createResult.isValid() ) {
			IFileReader sourceFileReader = null;
			IFileWriter destinationFileWriter = null;
			try {
				IFile sourceFile = source.getFile( sourceFileName.toString() );
				sourceFileReader = sourceFile.getFileReader();
				destinationFileWriter = destinationWrapper.getFile().getFileWriter(false);
				while ( sourceFileReader.hasNextLine() ) {
					destinationFileWriter.println( sourceFileReader.readLine() );
				}
				sourceFileReader.close();
				destinationFileWriter.close();
				
				command = new FileRenameCommand(temporaryName, destinationFileName, destination);
				ACommandResult renameResult = command.exec(getProvider());
				
				if ( !renameResult.isValid() ) {
					logger.error("execute", "error writing file in destination " + destination.getPath());
					result.getErrorMessages().addAll( createResult.getErrorMessages() );
				}
			}
			catch (Exception e) {
				logger.error("execute", "error writing file in destination " + destination.getPath(), e);
			}
			finally {
				if (destinationFileWriter!=null) destinationFileWriter.close();
				if (sourceFileReader!=null) sourceFileReader.close();
			}
		}
		else {
			result.getErrorMessages().addAll( createResult.getErrorMessages() );
		}
		
	}

}
