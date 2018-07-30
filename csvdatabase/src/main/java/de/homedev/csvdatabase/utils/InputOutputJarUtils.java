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

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class InputOutputJarUtils {
	private static Logger log = Logger.getLogger(InputOutputJarUtils.class);

	private InputOutputJarUtils() {
	}

	public static String findInJarFile(Class clazz, String value, String prefix, String idxFileName, long recordsPerFile, Charset charset) throws IOException {
		InputStream isData = null;
		InputStream isIndex = null;
		try {
			isIndex = clazz.getResourceAsStream(prefix + idxFileName);
			Long idxPos = InputOutputCommonUtils.findInIdxFile(isIndex, value, charset);
			String result = null;
			if (idxPos != null) {
				String internFileFullName = prefix + idxPos / recordsPerFile;
				long recNumber = idxPos % recordsPerFile;
				isData = clazz.getResourceAsStream(internFileFullName);
				result = InputOutputCommonUtils.readLine(recNumber, isData, charset);
			}
			return result;
		} finally {
			if (isIndex != null) {
				try {
					isIndex.close();
				} catch (IOException ee) {
					log.error(ee.getMessage(), ee);
				}
			}
			if (isData != null) {
				try {
					isData.close();
				} catch (IOException ee) {
					log.error(ee.getMessage(), ee);
				}
			}
		}
	}

	public static final <T extends CsvLineToDto<T>> List<T> getAllDataInJar(T obj, Class clazz, String url, Charset charset, int resultInitSize)
			throws IOException {
		BufferedReader reader = null;
		List<T> result = new ArrayList<>(resultInitSize);
		try {
			InputStream is = clazz.getResourceAsStream(url);
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

	public static final List<String> getAllDataInJar(Class clazz, String url, Charset charset, int resultInitSize) throws IOException {
		BufferedReader reader = null;
		List<String> result = new ArrayList<>(resultInitSize);
		try {
			InputStream is = clazz.getResourceAsStream(url);
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				result.add(line);
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
