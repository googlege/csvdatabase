package de.homedev.csvdatabase.tdt.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import de.homedev.csvdatabase.tdt.utils.TdtInputOutputZipUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class TdtZipTest {
	private static Logger log = Logger.getLogger(TdtZipTest.class);

	@Test
	public void testIfDatabaseExist() throws IOException {
		File databaseDir = TdtCommonUtils.getTdtDir();
		if (!databaseDir.exists()) {
			throw new FileNotFoundException("Can not find TDT database: " + databaseDir.getAbsolutePath());
		}
		if (!databaseDir.isDirectory()) {
			throw new IOException("File " + databaseDir + " is not a directory");
		}
		if (!databaseDir.canRead()) {
			throw new IOException("Do not have right reading from " + databaseDir.getAbsolutePath());
		}
		File manufacturerFile = new File(databaseDir, TdtConstants.MANUFACTURER_FILENAME);
		if (!databaseDir.exists()) {
			throw new FileNotFoundException("Can not find manufacturer file: " + manufacturerFile.getAbsolutePath());
		}
		if (!manufacturerFile.canRead()) {
			throw new IOException("Do not have right reading from " + manufacturerFile.getAbsolutePath());
		}
	}

	@Test
	public void testTdtDatabase1() throws IOException {
		String herstellerSchl = "1313";
		String typeSchl = "BEF";
		String vvsSchl = "00378";
		File dbDir = TdtCommonUtils.getTdtDir();
		long time1 = System.currentTimeMillis();
		String result = TdtInputOutputZipUtils.findInZipFile(herstellerSchl, typeSchl, vvsSchl, dbDir);
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
		File dbDir = TdtCommonUtils.getTdtDir();
		long time1 = System.currentTimeMillis();
		String result = TdtInputOutputZipUtils.findInZipFile(herstellerSchl, typeSchl, vvsSchl, dbDir);
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
		File dbDir = TdtCommonUtils.getTdtDir();
		File manufacturerFile = new File(dbDir, TdtConstants.MANUFACTURER_FILENAME);
		List<HerstellerDto> list = TdtInputOutputZipUtils.getAllManufacturerInZip(manufacturerFile, TdtConstants.CHARSET);
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
		File dbDir = TdtCommonUtils.getTdtDir();
		List<TypeDto> list = TdtInputOutputZipUtils.getAllTypesInZip(herstellerSchl, dbDir, TdtConstants.CHARSET);
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
		File dbDir = TdtCommonUtils.getTdtDir();
		List<String> list = TdtInputOutputZipUtils.getAllVvsSchlInZip(herstellerSchl, typeSchl, dbDir, TdtConstants.CHARSET);
		Assert.assertTrue(!list.isEmpty());
		Collections.sort(list);
		int index = Collections.binarySearch(list, vvsSchl, String.CASE_INSENSITIVE_ORDER);
		Assert.assertTrue(index != -1);
		String value = list.get(index);
		Assert.assertEquals(vvsSchl, value);
	}

}
