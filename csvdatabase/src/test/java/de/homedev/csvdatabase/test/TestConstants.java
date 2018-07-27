package de.homedev.csvdatabase.test;

import java.nio.charset.Charset;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public interface TestConstants {

	public static final String INDEX_FILENAME = "blz.idx";
	public static final Charset CHARSET = Charset.forName("ISO-8859-1");
	public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
	public static final long RECORDS_PER_FILE = 400;

	public static final int BLZ_ID = 0;
	public static final int BIC_ID = 1;
	public static final int BANKNAME_ID = 2;
	// public static final int PLZ_ID = 3;
	// public static final int ORT_ID = 4;

}
