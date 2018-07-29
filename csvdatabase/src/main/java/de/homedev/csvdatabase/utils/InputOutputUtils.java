package de.homedev.csvdatabase.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.apache.log4j.Logger;

import de.homedev.csvdatabase.exception.InconsistentDataException;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class InputOutputUtils {
	private static Logger log = Logger.getLogger(InputOutputUtils.class);

	private InputOutputUtils() {
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

	public static InputStream getResourceAsStream(JarFile jarFile, String internFileFullName) throws IOException {
		ZipEntry entry = jarFile.getEntry(internFileFullName);
		if (entry == null) {
			throw new RuntimeException("Can't read " + internFileFullName + " in " + jarFile.getName());
		}
		InputStream is = jarFile.getInputStream(entry);
		if (is == null) {
			throw new RuntimeException("Can't read " + internFileFullName + " in " + jarFile.getName());
		}
		return is;

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

	public static String findInZipFile(File zipFile, String value, String dirInJar, String idxFileName, long recordsPerFile, Charset charset)
			throws IOException {
		InputStream isData = null;
		InputStream isIndex = null;
		JarFile jarFile = null;
		String prefix = dirInJar + '/';
		try {
			if (!zipFile.exists()) {
				throw new FileNotFoundException("Can not find file " + zipFile.getAbsolutePath());
			}
			jarFile = new JarFile(zipFile);
			isIndex = getResourceAsStream(jarFile, prefix + idxFileName);
			Long idxPos = findInIdxFile(isIndex, value, charset);
			String result = null;
			if (idxPos != null) {
				String internFileFullName = prefix + idxPos / recordsPerFile;
				long recNumber = idxPos % recordsPerFile;
				isData = getResourceAsStream(jarFile, internFileFullName);
				result = readLine(recNumber, isData, charset);
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
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException ee) {
					log.error(ee.getMessage(), ee);
				}
			}
		}
	}

	public static String findInJarFile(Class clazz, String value, String prefix, String idxFileName, long recordsPerFile, Charset charset) throws IOException {
		InputStream isData = null;
		InputStream isIndex = null;
		try {
			isIndex = clazz.getResourceAsStream(prefix + idxFileName);
			Long idxPos = findInIdxFile(isIndex, value, charset);
			String result = null;
			if (idxPos != null) {
				String internFileFullName = prefix + idxPos / recordsPerFile;
				long recNumber = idxPos % recordsPerFile;
				isData = clazz.getResourceAsStream(internFileFullName);
				result = readLine(recNumber, isData, charset);
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

}
