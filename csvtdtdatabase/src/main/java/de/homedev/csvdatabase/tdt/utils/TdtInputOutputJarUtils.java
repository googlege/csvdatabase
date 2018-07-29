package de.homedev.csvdatabase.tdt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import de.homedev.csvdatabase.tdt.dto.HerstellerDto;
import de.homedev.csvdatabase.tdt.dto.TypeDto;
import de.homedev.csvdatabase.utils.CommonConstants;
import de.homedev.csvdatabase.utils.InputOutputUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class TdtInputOutputJarUtils implements CommonConstants {
	private static Logger log = Logger.getLogger(TdtInputOutputJarUtils.class);

	private TdtInputOutputJarUtils() {
	}

	public static final String findInJarFile(String herstellerSchl, String typeSchl, String vvsSchl) throws IOException {
		String dirInJar = '/' + TdtConstants.DATABASE_DIR + '/' + TdtCommonUtils.checkDirName(herstellerSchl) + '/' + TdtCommonUtils.checkDirName(typeSchl)
				+ '/';
		return InputOutputUtils.findInJarFile(TdtInputOutputJarUtils.class, vvsSchl, dirInJar, TdtConstants.INDEX_FILENAME, TdtConstants.RECORDS_PER_FILE,
				TdtConstants.CHARSET);
	}

	public static final List<HerstellerDto> getAllManufacturerInJar(String manufacturerFileName, Charset charset) throws IOException {
		String url = '/' + TdtConstants.DATABASE_DIR + '/' + manufacturerFileName;
		HerstellerDto obj = new HerstellerDto();
		int resultInitSize = 411;
		return InputOutputUtils.getAllDataInJar(obj, TdtInputOutputJarUtils.class, url, charset, resultInitSize);
	}

	public static final List<TypeDto> getAllTypesInJar(String herstellerSchl, Charset charset) throws IOException {
		String url = '/' + TdtConstants.DATABASE_DIR + '/' + herstellerSchl + '/' + TdtConstants.INDEX_FILENAME;
		TypeDto obj = new TypeDto();
		int resultInitSize = 400;
		return InputOutputUtils.getAllDataInJar(obj, TdtInputOutputJarUtils.class, url, charset, resultInitSize);
	}

	public static final List<String> getAllVvsSchlInJar(String herstellerSchl, String typeSchl, Charset charset) throws IOException {
		BufferedReader reader = null;
		List<String> result = new ArrayList<>(400);
		try {
			String prefix = '/' + TdtConstants.DATABASE_DIR + '/' + herstellerSchl + '/' + typeSchl + '/';
			InputStream is = TdtInputOutputJarUtils.class.getResourceAsStream(prefix + TdtConstants.INDEX_FILENAME);
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
