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
public class FileDeleteCommand extends ACommand<IProvider> {

	private ILogger logger;
	private StringBuffer fileName;
	private IResource resource;
	
	public FileDeleteCommand(IResource resource, StringBuffer fileName ) {
		this.resource = resource;
		this.fileName = fileName;
	}
	
	@Override
	protected void rollback(ACommandResult result) {
	}

	@Override
	protected void execute(ACommandResult result) {
		logger = getProvider().getLoggerService().logger(FileDeleteCommand.class);
		logger.info("execute", "deleting file " + fileName.toString() + " from " + resource.getPath());
		
		if ( !resource.getFilenames().contains(fileName.toString()) ) {
			String error = "impossible to delete file " + fileName.toString() + ": file doesn't exit";
			logger.error("execute", error);
			result.addErrorMessage( new ErrorMessage("FileDeleteCommand", error) );
			return;
		}
		
		if ( resource.delete(fileName.toString()) ) {
			logger.debug("execute", "file deleted");
		}
		else {
			String errorText = "error deleting file " + fileName.toString();
			logger.error("execute", errorText);
			result.addErrorMessage( new ErrorMessage("FileDeleteCommand", errorText) );
		}
		
	}

}
