package de.homedev.csvdatabase.tdt.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import de.homedev.csvdatabase.tdt.dto.HerstellerDto;
import de.homedev.csvdatabase.tdt.dto.TechnikBasisDto;
import de.homedev.csvdatabase.tdt.dto.TypeDto;
import de.homedev.csvdatabase.tdt.utils.TdtCommonUtils;
import de.homedev.csvdatabase.tdt.utils.TdtConstants;
import de.homedev.csvdatabase.tdt.utils.TdtInputOutputJarUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class TdtJarTest {
	private static Logger log = Logger.getLogger(TdtJarTest.class);

	@Test
	public void testIfDatabaseExist() throws IOException {
		String prefix = '/' + TdtConstants.DATABASE_DIR + '/';
		InputStream is = null;
		try {
			is = TdtInputOutputJarUtils.class.getResourceAsStream(prefix + TdtConstants.MANUFACTURER_FILENAME);
			Assert.assertNotNull(is);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ee) {
					log.error(ee.getMessage(), ee);
				}
			}
		}
	}

	@Test
	public void testTdtDatabase1() throws IOException {
		String herstellerSchl = "1313";
		String typeSchl = "BEF";
		String vvsSchl = "00378";
		long time1 = System.currentTimeMillis();
		String result = TdtInputOutputJarUtils.findInJarFile(herstellerSchl, typeSchl, vvsSchl);
		long time2 = System.currentTimeMillis();
		log.info("herstellerSchl:" + herstellerSchl + " typeSchl:" + typeSchl + " vvsSchl:" + vvsSchl);
		log.info("result:" + result);
		TechnikBasisDto dto = TdtCommonUtils.lineToDto(result);
		log.info("dto=" + dto);
		log.info("time2-time1=" + (time2 - time1));
		Assert.assertEquals(herstellerSchl, dto.getHerstellerSchluessel());
		Assert.assertEquals(typeSchl, dto.getTypSchluessel());
		Assert.assertEquals(vvsSchl, dto.getVvsSchluessel());
		Assert.assertEquals("7", dto.getPzhertypvvs());
	}

	@Test
	public void testTdtDatabase2() throws IOException {
		String herstellerSchl = "0603";
		String typeSchl = "BDS";
		String vvsSchl = "01208";
		long time1 = System.currentTimeMillis();
		String result = TdtInputOutputJarUtils.findInJarFile(herstellerSchl, typeSchl, vvsSchl);
		long time2 = System.currentTimeMillis();
		log.info("herstellerSchl:" + herstellerSchl + " typeSchl:" + typeSchl + " vvsSchl:" + vvsSchl);
		log.info("result:" + result);
		TechnikBasisDto dto = TdtCommonUtils.lineToDto(result);
		log.info("dto=" + dto);
		log.info("time2-time1=" + (time2 - time1));
		Assert.assertEquals(herstellerSchl, dto.getHerstellerSchluessel());
		Assert.assertEquals(typeSchl, dto.getTypSchluessel());
		Assert.assertEquals(vvsSchl, dto.getVvsSchluessel());
		Assert.assertEquals("9", dto.getPzhertypvvs());
	}

	@Test
	public void readAllAllManufacturer() throws IOException {
		String herstellerSchl = "0603";
		List<HerstellerDto> list = TdtInputOutputJarUtils.getAllManufacturerInJar(TdtConstants.MANUFACTURER_FILENAME, TdtConstants.CHARSET);
		Assert.assertNotNull(list);
		Assert.assertTrue(!list.isEmpty());
		Collections.sort(list);
		int index = Collections.binarySearch(list, new HerstellerDto(herstellerSchl, null));
		Assert.assertTrue(index != -1);
		HerstellerDto dto = list.get(index);
		Assert.assertEquals(herstellerSchl, dto.getSchluessel());
	}

	@Test
	public void testTdtDatabase1TypesList() throws IOException {
		String herstellerSchl = "1313";
		String typeSchl = "BEF";
		// String vvsSchl = "00378";
		List<TypeDto> list = TdtInputOutputJarUtils.getAllTypesInJar(herstellerSchl, TdtConstants.CHARSET);
		Assert.assertNotNull(list);
		Assert.assertTrue(!list.isEmpty());
		Collections.sort(list);
		int index = Collections.binarySearch(list, new TypeDto(typeSchl, null));
		Assert.assertTrue(index != -1);
		TypeDto dto = list.get(index);
		Assert.assertEquals(typeSchl, dto.getSchluessel());
	}

	@Test
	public void testTdtDatabase1VVSList() throws IOException {
		String herstellerSchl = "1313";
		String typeSchl = "BEF";
		String vvsSchl = "00378";
		List<String> list = TdtInputOutputJarUtils.getAllVvsSchlInJar(herstellerSchl, typeSchl, TdtConstants.CHARSET);
		Assert.assertTrue(!list.isEmpty());
		Collections.sort(list);
		int index = Collections.binarySearch(list, vvsSchl, String.CASE_INSENSITIVE_ORDER);
		Assert.assertTrue(index != -1);
		String value = list.get(index);
		Assert.assertEquals(vvsSchl, value);
	}
}
