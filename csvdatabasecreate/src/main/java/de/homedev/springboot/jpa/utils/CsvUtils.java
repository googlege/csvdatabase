package de.homedev.springboot.jpa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.homedev.springboot.jpa.entity.CommonConstants;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class CsvUtils {
	private static Logger log = LoggerFactory.getLogger(CsvUtils.class);

	public static Long longFromCSVLine(final String line, final char separator, final int position) throws IndexOutOfBoundsException {
		String value = valueFromCSVLine(line, separator, position);
		if (value == null)
			return null;
		if (value != null) {
			try {
				return Long.parseLong(value);
			} catch (NumberFormatException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public static Double doubleFromCSVLine(final String line, final char separator, final int position) throws IndexOutOfBoundsException {
		String value = valueFromCSVLine(line, separator, position);
		if (value == null)
			return null;
		if (value != null) {
			try {
				return Double.parseDouble(value);
			} catch (NumberFormatException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public static String valueFromCSVLine(final String line, final char separator, final int position) throws IndexOutOfBoundsException {
		int startPointer = 0;
		int d = 0;
		if (line.charAt(0) == separator) {
			d = 1;
		}
		for (int i = d; i < position; i++) {
			startPointer = line.indexOf(separator, startPointer + 1);
			if (startPointer == -1) {
				final String msg = "Separator:" + separator + " Position:" + position + " existirt nicht. Line:" + line;
				log.error(msg);
				throw new IndexOutOfBoundsException(msg);
			}
		}

		// Kursor "pointer" wurde positioniert.
		int endPointer = line.indexOf(separator, startPointer + 1);
		if (endPointer == -1) {
			endPointer = line.length();
		}
		if (startPointer != 0) {
			startPointer++;// Separator Character wird nicht \u00fcbernommen.
		}
		if ((startPointer >= line.length()) || (line.charAt(startPointer) == separator)) {
			return null;
		}
		final String ergebnis = line.substring(startPointer, endPointer);
		if (!ergebnis.isEmpty()) {
			return ergebnis.trim().replace(CommonConstants.CSV_PARAM_SEPARATOR_REPLACEMENT, CommonConstants.CSV_PARAM_SEPARATOR);
		}
		return null;
	}

	public static File getDBDir() {
		File runDir = new File(System.getProperty("user.dir"));
		return new File(runDir, "etc");
	}

	public static File getBlzDir() {
		File dbDir = getDBDir();
		File result = new File(dbDir, "blz");
		if (!result.exists()) {
			result.mkdirs();
		}
		return result;
	}

	/**
	 * Reading any file with 'fileName' from database directory
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream getFileInputStream(String fileName, File currentDBDir) throws FileNotFoundException {
		if (!currentDBDir.exists() || !currentDBDir.isDirectory()) {
			throw new RuntimeException("Database directory" + currentDBDir.getAbsolutePath() + " does not exist");
		}
		File file = new File(currentDBDir, fileName);
		if (!file.exists() || file.isDirectory()) {
			throw new RuntimeException("File " + file.getAbsolutePath() + " does not exist");
		}
		return new FileInputStream(file);
	}

	public static void writeFile(String fileName, String data, File currentDBDir) throws IOException {
		if (!currentDBDir.exists() || !currentDBDir.isDirectory()) {
			throw new RuntimeException("Database directory" + currentDBDir.getAbsolutePath() + " does not exist");
		}
		File file = new File(currentDBDir, fileName);
		if (file.isDirectory()) {
			throw new RuntimeException("File " + file.getAbsolutePath() + " exists and is directory");
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(file, false);
			os.write(data.getBytes(CommonConstants.CHARSET));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

}
