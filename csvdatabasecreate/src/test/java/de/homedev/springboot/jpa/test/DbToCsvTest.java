package de.homedev.springboot.jpa.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
public class DbToCsvTest {
	private static final Logger log = LoggerFactory.getLogger(DbToCsvTest.class);
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
	public void dbToCsvTest() {
		InputStream is = null;
		OutputStream os = null;
		JarFile jarFile = null;
		try {

			File dbFile = new File(CsvUtils.getDBDir(), "blz.zip");
			jarFile = new JarFile(dbFile);
			File file = new File(CsvUtils.getDBDir(), "insert.sql");
			os = new FileOutputStream(file, false);
			fassade.deleteAll();
			long id = 0;
			for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements();) {
				JarEntry entry = e.nextElement();
				String name = entry.getName();
				System.err.println(name + " isDirectory:" + entry.isDirectory());
				if (entry.isDirectory() || !name.startsWith("blz/") || name.endsWith(".idx")) {
					continue;
				}
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(entry), CommonConstants.CHARSET));
					String line = null;
					List<BlzEntity> list = new ArrayList<>(PUFFER_SIZE);
					while ((line = reader.readLine()) != null) {
						if (line.isEmpty()) {
							continue;
						}
						BlzEntity entity = new BlzEntity(line);
						os.write(entity.toSqlInsert(id++));
						list.add(entity);
						if (list.size() == PUFFER_SIZE) {
							fassade.save(list);
							list.clear();

						}
					}
					fassade.save(list);

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
			System.err.println(fassade.getDbInfo());
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

	}
}