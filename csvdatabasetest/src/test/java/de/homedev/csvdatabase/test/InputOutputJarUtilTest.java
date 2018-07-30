package de.homedev.csvdatabase.test;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.homedev.csvdatabase.utils.InputOutputJarUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class InputOutputJarUtilTest {
	private static Logger log = Logger.getLogger(InputOutputJarUtilTest.class);

	public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
	private static final String BLZ = "70020270";

	/**
	 * Test data initialisation function
	 */
	@Before
	public void init() {

	}

	@Test
	public void testJarFile() throws IOException {
		String result = InputOutputJarUtils.findInJarFile(InputOutputJarUtils.class, BLZ, "/blz/", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET);
		log.info("testJarFile result:" + result);
		Assert.assertNotNull(result);
		BlzDto blzDto = new BlzDto(result);
		Assert.assertEquals(BLZ, blzDto.getBlz());
		Assert.assertEquals("HYVEDEMMXXX", blzDto.getBic());
		Assert.assertEquals("UniCredit Bank - HypoVereinsbank", blzDto.getBankname());

	}

	@Test
	public void testJarFileExt() throws IOException {
		long time1 = System.currentTimeMillis();
		BlzDto blzDto = InputOutputUtilsExt.findInJarFile(InputOutputJarUtils.class, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET, true);
		long time2 = System.currentTimeMillis();
		blzDto = InputOutputUtilsExt.findInJarFile(InputOutputJarUtils.class, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
				TestConstants.CHARSET, true);
		long time3 = System.currentTimeMillis();

		Assert.assertNotNull(blzDto);
		Assert.assertEquals(BLZ, blzDto.getBlz());
		Assert.assertEquals("HYVEDEMMXXX", blzDto.getBic());
		Assert.assertEquals("UniCredit Bank - HypoVereinsbank", blzDto.getBankname());
		log.info("testJarFileExt time2-time1=" + (time2 - time1) + " time3-time2=" + (time3 - time2));

	}

}
