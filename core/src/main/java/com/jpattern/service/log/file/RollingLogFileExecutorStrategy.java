package com.jpattern.service.log.file;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public class RollingLogFileExecutorStrategy implements ILogFileExecutorStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filepath;
	private int maxFileSize;
	private int unitOfMisure;
	private int maxFileBackupNumber;
	private transient ILogFileWriter fileWriter;

	public RollingLogFileExecutorStrategy(String filepath, int maxFileSize, int unitOfMisure, int maxFileBackupNumber) {
		this.filepath = filepath;
		this.maxFileSize = maxFileSize;
		this.unitOfMisure = unitOfMisure;
		this.maxFileBackupNumber = maxFileBackupNumber;
		this.fileWriter = new LogFileWriter(filepath);
	}

	public ILogFileWriter getFileWriter() {
		if ( fileWriter.getSize() >= ( ((long) maxFileSize) * ((long) unitOfMisure)) ) {
//			System.out.println("filesize: " + fileWriter.getSize() +  " ruoto file!");
			roll();
		}
		return fileWriter;
	}

	private void roll() {
		if ( maxFileBackupNumber > 0 ) {
			LogFileWriter lastWriter = new LogFileWriter( filepath + "." + maxFileBackupNumber );
			lastWriter.delete();
			
			for ( int i=maxFileBackupNumber-1; i>0 ;i-- ) {
				LogFileWriter tempWriter = new LogFileWriter( filepath + "." + i );
				if (tempWriter.exist()) {
					tempWriter.rename( filepath + "." + (i+1) );
					tempWriter.close();
				}
			}
			
			fileWriter.rename( filepath + ".1" );
			fileWriter.close();
			
			fileWriter = new LogFileWriter(filepath);
		}
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		this.fileWriter = new LogFileWriter(filepath);
	}
}
