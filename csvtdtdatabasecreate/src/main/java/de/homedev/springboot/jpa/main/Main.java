
package de.homedev.springboot.jpa.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import de.homedev.springboot.jpa.config.DbConfig;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@SpringBootApplication
@Import({ DbConfig.class })
public class Main {
	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		try {
			ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

		}

	}

}
