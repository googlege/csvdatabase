package de.homedev.csvdatabase.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.homedev.csvdatabase.utils.InputOutputUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class InputOutputUtilTest {
	private static Logger log = LoggerFactory.getLogger(InputOutputUtilTest.class);

	public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

	private static final String BLZ = "70020270";

	/**
	 * Test data initialisation function
	 */
	@Before
	public void init() {

	}

	public File getDBDir() {
		File runDir = new File(System.getProperty("user.dir"));
		return new File(runDir, "etc");
	}

	@Test
	public void testIfFileExist() throws IOException {
		File zipFile = new File(getDBDir(), "blz.zip");
		if (!zipFile.exists()) {
			throw new RuntimeException("Database " + zipFile.getAbsolutePath() + " wurde nicht gefunden");
		}
	}

	@Test
	public void testZipFile() throws IOException {
		File zipFile = new File(getDBDir(), "blz.zip");
		String result = InputOutputUtils.findInZipFile(zipFile, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET);
		log.info("testZipFile result:" + result);
		Assert.assertNotNull(result);
		BlzDto blzDto = new BlzDto(result);
		Assert.assertEquals(BLZ, blzDto.getBlz());
		Assert.assertEquals("HYVEDEMMXXX", blzDto.getBic());
		Assert.assertEquals("UniCredit Bank - HypoVereinsbank", blzDto.getBankname());

	}

	@Test
	public void testJarFile() throws IOException {
		String result = InputOutputUtils.findInJarFile(InputOutputUtils.class, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET);
		log.info("testJarFile result:" + result);
		Assert.assertNotNull(result);
		BlzDto blzDto = new BlzDto(result);
		Assert.assertEquals(BLZ, blzDto.getBlz());
		Assert.assertEquals("HYVEDEMMXXX", blzDto.getBic());
		Assert.assertEquals("UniCredit Bank - HypoVereinsbank", blzDto.getBankname());

	}

	@Test
	public void testZipFileExt() throws IOException {
		File zipFile = new File(getDBDir(), "blz.zip");
		long time1 = System.currentTimeMillis();
		BlzDto blzDto = InputOutputUtilsExt.findInZipFile(zipFile, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET, true);
		long time2 = System.currentTimeMillis();
		blzDto = InputOutputUtilsExt.findInZipFile(zipFile, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE, TestConstants.CHARSET,
				true);
		long time3 = System.currentTimeMillis();
		Assert.assertNotNull(blzDto);
		Assert.assertEquals(BLZ, blzDto.getBlz());
		Assert.assertEquals("HYVEDEMMXXX", blzDto.getBic());
		Assert.assertEquals("UniCredit Bank - HypoVereinsbank", blzDto.getBankname());
		log.info("testZipFileExt time2-time1=" + (time2 - time1) + " time3-time2=" + (time3 - time2));
	}

	@Test
	public void testJarFileExt() throws IOException {
		long time1 = System.currentTimeMillis();
		BlzDto blzDto = InputOutputUtilsExt.findInJarFile(InputOutputUtils.class, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET, true);
		long time2 = System.currentTimeMillis();
		blzDto = InputOutputUtilsExt.findInJarFile(InputOutputUtils.class, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET, true);
		long time3 = System.currentTimeMillis();

		Assert.assertNotNull(blzDto);
		Assert.assertEquals(BLZ, blzDto.getBlz());
		Assert.assertEquals("HYVEDEMMXXX", blzDto.getBic());
		Assert.assertEquals("UniCredit Bank - HypoVereinsbank", blzDto.getBankname());
		log.info("testJarFileExt time2-time1=" + (time2 - time1) + " time3-time2=" + (time3 - time2));

	}

	@Test
	public void testCheckDigitUtils() throws IOException {
		String kontonummer = "2712563";
		String blz = "70020270";
		final String landerkennung = "131400";// 1314 (D = 13, E = 14) + 00
		String normkontonummer = CheckDigitUtils.toNormNummer(kontonummer, 10, '0');
		String ibanPruefziffer = CheckDigitUtils.calculatePruefziffer(normkontonummer, BLZ, landerkennung);
		final StringBuffer iban = new StringBuffer(22);
		iban.append("DE").append(ibanPruefziffer).append(BLZ).append(normkontonummer);
		log.info("Input");
		log.info("BLZ:" + BLZ + " KONTONUMMER:" + kontonummer);
		log.info("Output");
		log.info("IBAN:" + iban.toString());
		Assert.assertEquals("DE91700202700002712563", iban.toString());
		BlzDto blzDto = InputOutputUtilsExt.findInJarFile(InputOutputUtils.class, blz, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET, true);
		if (blzDto != null) {
			log.info("BIC:" + blzDto.getBic());
			log.info("Bankname:" + blzDto.getBankname());
			Assert.assertEquals("HYVEDEMMXXX", blzDto.getBic());
			Assert.assertEquals("UniCredit Bank - HypoVereinsbank", blzDto.getBankname());
		}

	}

}
