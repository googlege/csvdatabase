package de.homedev.csvdatabase.tdt.utils;

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

import org.apache.log4j.Logger;

import de.homedev.csvdatabase.tdt.dto.HerstellerDto;
import de.homedev.csvdatabase.tdt.dto.TypeDto;
import de.homedev.csvdatabase.utils.CommonConstants;
import de.homedev.csvdatabase.utils.InputOutputCommonUtils;
import de.homedev.csvdatabase.utils.InputOutputZipUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class TdtInputOutputZipUtils implements CommonConstants {
	private static Logger log = Logger.getLogger(TdtInputOutputZipUtils.class);

	private TdtInputOutputZipUtils() {
	}

	public static final File getDBDir() {
		File runDir = new File(System.getProperty("user.dir"));
		File etcDir = new File(runDir, "etc");
		return new File(etcDir, "database");
	}

	public static final File getTdtDir() {
		return new File(getDBDir(), TdtConstants.DATABASE_DIR);
	}

	public static final String findInZipFile(String herstellerSchl, String typeSchl, String vvsSchl, File dbDir) throws IOException {
		String dirInJar = TdtCommonUtils.checkDirName(herstellerSchl) + "/" + TdtCommonUtils.checkDirName(typeSchl);
		File zipFile = new File(dbDir, herstellerSchl + ".zip");
		return InputOutputZipUtils.findInZipFile(zipFile, vvsSchl, dirInJar, TdtConstants.INDEX_FILENAME, TdtConstants.RECORDS_PER_FILE, TdtConstants.CHARSET);
	}

	public static final List<HerstellerDto> getAllManufacturerInZip(File manufacturerFile, Charset charset) throws IOException {
		HerstellerDto obj = new HerstellerDto();
		int resultInitSize = 411;
		return InputOutputCommonUtils.getAllDataInFile(obj, manufacturerFile, charset, resultInitSize);

	}

	public static final List<TypeDto> getAllTypesInZip(String herstellerSchl, File dbDir, Charset charset) throws IOException {
		JarFile jarFile = null;
		InputStream isIndex = null;
		List<TypeDto> result = new ArrayList<>(400);
		File zipFile = new File(dbDir, herstellerSchl + ".zip");
		try {
			if (!zipFile.exists()) {
				throw new FileNotFoundException("Can not find file " + zipFile.getAbsolutePath());
			}
			jarFile = new JarFile(zipFile);
			isIndex = InputOutputZipUtils.getResourceAsStream(jarFile, herstellerSchl + '/' + TdtConstants.INDEX_FILENAME);
			BufferedReader reader = new BufferedReader(new InputStreamReader(isIndex, charset));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				result.add(new TypeDto(line));
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
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException ee) {
					log.error(ee.getMessage(), ee);
				}
			}
		}
	}

	public static final List<String> getAllVvsSchlInZip(String herstellerSchl, String typeSchl, File dbDir, Charset charset) throws IOException {
		JarFile jarFile = null;
		InputStream isIndex = null;
		List<String> result = new ArrayList<>(400);
		try {
			File zipFile = new File(dbDir, herstellerSchl + ".zip");
			if (!zipFile.exists()) {
				throw new FileNotFoundException("Can not find file " + zipFile.getAbsolutePath());
			}
			jarFile = new JarFile(zipFile);
			isIndex = InputOutputZipUtils.getResourceAsStream(jarFile, herstellerSchl + "/" + typeSchl + "/" + TdtConstants.INDEX_FILENAME);
			BufferedReader reader = new BufferedReader(new InputStreamReader(isIndex, charset));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				result.add(line);
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
