package com.example.anki;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Exports deck of cards to Anki *.apkg file format.
 *
 */
public class AnkiApkg implements AutoCloseable {

	private final BufferedOutputStream bufferedOutputStream;
	private final ZipOutputStream zipOutputStream;

	/**
	 * Creates *.apkg file at specified output stream.
	 * 
	 * @param outputStream - output stream to write binary data.
	 */
	public AnkiApkg(OutputStream outputStream) {
		bufferedOutputStream = new BufferedOutputStream(outputStream);
		zipOutputStream = new ZipOutputStream(bufferedOutputStream);
	}

	/**
	 * Adds file to apkg archive (zip archive). Empty file (length == 0) won't be
	 * added.
	 *
	 * @param fileName    - name of file to be added to archive
	 * @param fileContent - content of the file
	 * @return true if file can be added (file size > 0) or false if the file is
	 *         empty
	 */
	public boolean addToArchive(String fileName, byte[] fileContent) {
		if (fileContent.length > 0) {
			try {
				zipOutputStream.putNextEntry(new ZipEntry(fileName));
				zipOutputStream.write(fileContent);
				zipOutputStream.closeEntry();
				return true;
			} catch (IOException exception) {
				throw new RuntimeException("Error at adding " + fileName + " to archive.");
			}
		}
		return false;
	}

	@Override
	public void close() {
		try {
			zipOutputStream.close();
			bufferedOutputStream.close();
		} catch (IOException exception) {
			throw new RuntimeException("Can't close output file.");
		}
	}
}
