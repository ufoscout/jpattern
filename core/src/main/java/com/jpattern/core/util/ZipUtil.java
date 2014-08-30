package com.jpattern.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public abstract class ZipUtil {

	/**
	 * Crea un file compresso da una lista di file
	 * @param files array di nomi di file da includere nello zip finale
	 * @param zipfilename nome del file zip che deve essere creato
	 * @param ignorePath se true archivia i file senza il loro path
	 * @param compressionLevel livello di compressione, varia da 1 (non compresso) a 9 (massima compressione)
	 * @throws Exception
	 */
	public static void zipFiles(String[] files, String zipfilename, boolean ignorePath, int compressionLevel) throws Exception {
		int buffer = 2048;
		BufferedInputStream origin = null;
		FileOutputStream dest = new FileOutputStream(zipfilename);
		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
		out.setLevel(compressionLevel);

		byte data[] = new byte[buffer];

		for (int i = 0; i < files.length; i++) {
			FileInputStream fi = new FileInputStream(files[i]);
			origin = new BufferedInputStream(fi, buffer);
			String currentFilename = files[i]; 
			if (ignorePath) {
				currentFilename = FileUtil.removePath(currentFilename);
			}
			ZipEntry entry = new ZipEntry(currentFilename);
			out.putNextEntry(entry);
			int count;
			while ((count = origin.read(data, 0, buffer)) != -1) {
				out.write(data, 0, count);
			}
			origin.close();
		}
		out.close();
	}
	
}
