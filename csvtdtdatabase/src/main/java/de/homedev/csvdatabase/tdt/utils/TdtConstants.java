package de.homedev.csvdatabase.tdt.utils;

import java.nio.charset.Charset;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public interface TdtConstants {
	public static final Charset CHARSET = Charset.forName("ISO-8859-1");
	public static final String INDEX_FILENAME = "i.i";
	public static final String MANUFACTURER_FILENAME = "h.dat";
	public static final long RECORDS_PER_FILE = 200;

}
