package com.jpattern.core.textfiles;

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
public class FileCreateCommand extends ACommand<IProvider> {

	private ILogger logger; 
	private FileWrapper fileWrapper;
	private StringBuffer fileContent;
	private StringBuffer fileName;
	private IResource resource;
	
	public FileCreateCommand(IResource resource, StringBuffer fileName, StringBuffer fileContent, FileWrapper fileWrapper) {
		this.resource = resource;
		this.fileName = fileName;
		this.fileContent = fileContent;
		this.fileWrapper = fileWrapper;
	}
	
	@Override
	protected void rollback(ACommandResult result) {
		
	}

	@Override
	protected void execute(ACommandResult result) {
		logger = getProvider().getLoggerService().logger(FileCreateCommand.class);
		logger.info("execute", "Creating file " + fileName.toString() + " in " + resource.getPath());
		try {
			IFile newFile = resource.create( fileName.toString(), fileContent.toString() );
			fileWrapper.setFile(newFile);
			if ( newFile.exists() ){
				logger.debug("execute", "Created file " + newFile.getPath());
			}
			else {
				logger.error("execute", "Error creating file " + fileName);
				result.addErrorMessage( new ErrorMessage("FileCreateCommand", "Error creating file " + fileName) );
			}
			
		} catch (Exception e) {
			logger.error("execute", "Error creating file " + fileName, e);
			result.addErrorMessage( new ErrorMessage("FileCreateCommand", "Error creating file " + fileName + ": " + e.getMessage()) );
		}
	}

}
