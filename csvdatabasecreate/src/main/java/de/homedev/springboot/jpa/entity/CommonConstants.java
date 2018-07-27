package de.homedev.springboot.jpa.entity;

import java.nio.charset.Charset;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public interface CommonConstants {
	public static final char CSV_PARAM_SEPARATOR = ';';
	public static final char CSV_PARAM_SEPARATOR_REPLACEMENT = '|';
	public static final String RECORD_SEPARATOR = "\r\n";
	public static final Charset CHARSET = Charset.forName("ISO-8859-1");
	public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
	public static final int RECORDS_PER_FILE = 400;
	public static final String INDEX_FILENAME = "blz.idx";

	public static final int BLZ_ID = 0;
	public static final int BIC_ID = 1;
	public static final int BANKNAME_ID = 2;
	public static final int PLZ_ID = 3;
	public static final int ORT_ID = 4;

}
