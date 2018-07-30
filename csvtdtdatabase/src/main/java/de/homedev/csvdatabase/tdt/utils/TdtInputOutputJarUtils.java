package de.homedev.csvdatabase.tdt.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.log4j.Logger;

import de.homedev.csvdatabase.tdt.dto.HerstellerDto;
import de.homedev.csvdatabase.tdt.dto.TypeDto;
import de.homedev.csvdatabase.utils.CommonConstants;
import de.homedev.csvdatabase.utils.InputOutputJarUtils;

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
		return InputOutputJarUtils.findInJarFile(TdtInputOutputJarUtils.class, vvsSchl, dirInJar, TdtConstants.INDEX_FILENAME, TdtConstants.RECORDS_PER_FILE,
				TdtConstants.CHARSET);
	}

	public static final List<HerstellerDto> getAllManufacturerInJar(String manufacturerFileName, Charset charset) throws IOException {
		String url = '/' + TdtConstants.DATABASE_DIR + '/' + manufacturerFileName;
		HerstellerDto obj = new HerstellerDto();
		int resultInitSize = 411;
		return InputOutputJarUtils.getAllDataInJar(obj, TdtInputOutputJarUtils.class, url, charset, resultInitSize);
	}

	public static final List<TypeDto> getAllTypesInJar(String herstellerSchl, Charset charset) throws IOException {
		String url = '/' + TdtConstants.DATABASE_DIR + '/' + herstellerSchl + '/' + TdtConstants.INDEX_FILENAME;
		TypeDto obj = new TypeDto();
		int resultInitSize = 400;
		return InputOutputJarUtils.getAllDataInJar(obj, TdtInputOutputJarUtils.class, url, charset, resultInitSize);
	}

	public static final List<String> getAllVvsSchlInJar(String herstellerSchl, String typeSchl, Charset charset) throws IOException {
		String url = '/' + TdtConstants.DATABASE_DIR + '/' + herstellerSchl + '/' + typeSchl + '/' + TdtConstants.INDEX_FILENAME;
		int resultInitSize = 400;
		return InputOutputJarUtils.getAllDataInJar(TdtInputOutputJarUtils.class, url, charset, resultInitSize);
	}

}
