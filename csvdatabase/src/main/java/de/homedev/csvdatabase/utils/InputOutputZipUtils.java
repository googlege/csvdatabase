package de.homedev.csvdatabase.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.apache.log4j.Logger;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class InputOutputZipUtils {
	private static Logger log = Logger.getLogger(InputOutputZipUtils.class);

	private InputOutputZipUtils() {
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
			Long idxPos = InputOutputCommonUtils.findInIdxFile(isIndex, value, charset);
			String result = null;
			if (idxPos != null) {
				String internFileFullName = prefix + idxPos / recordsPerFile;
				long recNumber = idxPos % recordsPerFile;
				isData = getResourceAsStream(jarFile, internFileFullName);
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
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException ee) {
					log.error(ee.getMessage(), ee);
				}
			}
		}
	}

}
