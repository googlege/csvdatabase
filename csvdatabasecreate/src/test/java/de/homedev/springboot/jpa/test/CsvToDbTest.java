package de.homedev.springboot.jpa.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import de.homedev.springboot.jpa.config.DbConfig;
import de.homedev.springboot.jpa.entity.BlzEntity;
import de.homedev.springboot.jpa.entity.CommonConstants;
import de.homedev.springboot.jpa.service.IBlzService;
import de.homedev.springboot.jpa.utils.CsvUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Import({ DbConfig.class })
public class CsvToDbTest {
	private static final Logger log = LoggerFactory.getLogger(CsvToDbTest.class);
	public static final int PUFFER_SIZE = 1000;
	@Autowired
	private ConfigurableApplicationContext ctx;
	private IBlzService fassade;

	@Before
	public void setUp() {
		fassade = (IBlzService) ctx.getBean(IBlzService.SERVICE_NAME);
	}

	@Test
	public void fassadeInitialisationTest() {
		Assert.assertNotNull(fassade);
	}

	@Test
	public void csvToDbTest() {
		BufferedReader reader = null;
		try {

			File dbFile = new File(CsvUtils.getDBDir(), "insert.sql");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(dbFile), CommonConstants.CHARSET));
			String line = null;
			long lineNumber = 0;
			List<String> list = new ArrayList<>(PUFFER_SIZE);
			fassade.deleteAll();
			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				line = line.trim().substring(0, line.length() - 1) + CommonConstants.RECORD_SEPARATOR;

				list.add(line);
				if (lineNumber++ >= PUFFER_SIZE) {
					fassade.saveNative(list);
					list.clear();
				}
			}
			fassade.saveNative(list);

			Iterable<BlzEntity> dbList = fassade.findAll();
			StringBuilder fileContent = new StringBuilder(CommonConstants.RECORDS_PER_FILE * 100);
			StringBuilder idxContent = new StringBuilder(CommonConstants.RECORDS_PER_FILE * BlzEntity.BLZ_MAX_LENGTH);
			int i = 0;
			int j = 0;
			for (BlzEntity e : dbList) {
				fileContent.append(e.toCsvInsert());
				idxContent.append(e.getBlz()).append(CommonConstants.RECORD_SEPARATOR);
				i++;
				if (i >= CommonConstants.RECORDS_PER_FILE) {
					CsvUtils.writeFile(Integer.toString(j), fileContent.toString(), CsvUtils.getBlzDir());
					fileContent.delete(0, fileContent.length());
					i = 0;
					j++;
				}
			}
			CsvUtils.writeFile(Integer.toString(j), fileContent.toString(), CsvUtils.getBlzDir());
			CsvUtils.writeFile(CommonConstants.INDEX_FILENAME, idxContent.toString(), CsvUtils.getBlzDir());
		} catch (Exception e) {
			log.error(e.getMessage(), e);

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