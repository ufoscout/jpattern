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
public class FileRenameCommand extends ACommand<IProvider> {

	private ILogger logger;
	private StringBuffer oldFileName;
	private StringBuffer newFileName;
	private IResource resource;
	
	public FileRenameCommand(StringBuffer oldFileName, StringBuffer newFileName, IResource resource) {
		this.oldFileName = oldFileName;
		this.newFileName = newFileName;
		this.resource = resource;
	}
	
	@Override
	protected void rollback(ACommandResult result) {
	}

	@Override
	protected void execute(ACommandResult result) {
		logger = getProvider().getLoggerService().logger(FileRenameCommand.class);
		logger.info("execute", "rename file from " + oldFileName.toString() + " to " + newFileName.toString());
		
		if ( !resource.getFilenames().contains(oldFileName.toString()) ) {
			logger.error("execute", "impossible to rename file: source file doesn't exist");
			result.addErrorMessage( new ErrorMessage("FileRenameCommand", "impossible to rename file: source file doesn't exists") );
			return;
		}
		
		if ( resource.getFilenames().contains(newFileName.toString()) ) {
			logger.error("execute", "impossible to rename file: destination file exist");
			result.addErrorMessage( new ErrorMessage("FileRenameCommand", "impossible to rename file: destination file exists") );
			return;
		}
		
		if ( !resource.rename(oldFileName.toString(), newFileName.toString()) ){
			logger.error("execute", "impossible to rename file " + oldFileName.toString());
			result.addErrorMessage( new ErrorMessage("FileRenameCommand", "impossible to rename file " + oldFileName.toString()) );
		}
	
	}

}
