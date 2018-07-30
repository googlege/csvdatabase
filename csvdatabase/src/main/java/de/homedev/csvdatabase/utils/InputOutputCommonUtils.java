package de.homedev.csvdatabase.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.homedev.csvdatabase.exception.InconsistentDataException;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class InputOutputCommonUtils {
	private static Logger log = Logger.getLogger(InputOutputCommonUtils.class);

	private InputOutputCommonUtils() {
	}

	public static String readLine(long lineNr, InputStream resourceAsStream, Charset charset) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, charset));
		String line = null;
		long lineNumber = 0;
		while ((line = reader.readLine()) != null) {
			if (lineNumber >= lineNr) {
				return line;
			}
			lineNumber++;
		}
		throw new InconsistentDataException("Zeile:" + lineNr + " existiert nicht. Max:" + lineNumber);

	}

	public static Long findInIdxFile(InputStream is, String value, Charset charset) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
		String line = null;
		long lineNumber = 0;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith(value)) {
				return lineNumber;
			}
			lineNumber++;
		}
		return null;
	}

	public static final <T extends CsvLineToDto<T>> List<T> getAllDataInFile(T obj, File file, Charset charset, int resultInitSize) throws IOException {
		BufferedReader reader = null;
		List<T> result = new ArrayList<>(resultInitSize);
		try {
			InputStream is = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				result.add(obj.lineToDto(line));
			}
			return result;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ee) {
					log.error(ee.getMessage(), ee);
				}
			}

		}
	}

}
