package de.homedev.csvdatabase.utils;

import org.apache.log4j.Logger;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class CsvUtils {
	private static Logger log = Logger.getLogger(CsvUtils.class);

	private CsvUtils() {
	}

	public static Long stringToLong(final String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static Double stringToDouble(final String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static Long longFromCSVLine(final String line, final char separator, final int position) throws IndexOutOfBoundsException {
		String value = valueFromCSVLine(line, separator, position);
		return stringToLong(value);

	}

	public static Double doubleFromCSVLine(final String line, final char separator, final int position) throws IndexOutOfBoundsException {
		String value = valueFromCSVLine(line, separator, position);
		return stringToDouble(value);
	}

	public static String[] split(final String line, final String separator, final int size) {
		String[] result = line.split(separator, size);
		if (result.length != size) {
			final String msg = "Separator:" + separator + " found values:" + result.length + " must have:" + size + " Line:" + line;
			log.error(msg);
			throw new IndexOutOfBoundsException(msg);
		}
		for (int i = 0; i < result.length; i++) {
			result[i] = result[i].trim().replace(CommonConstants.CSV_PARAM_SEPARATOR_REPLACEMENT, CommonConstants.CSV_PARAM_SEPARATOR);
		}
		return result;
	}

	public static String valueFromCSVLine(final String line, final char separator, final int position) throws IndexOutOfBoundsException {
		int startPointer = 0;
		int d = 0;
		if (line.charAt(0) == separator) {
			d = 1;
		}
		for (int i = d; i < position; i++) {
			startPointer = line.indexOf(separator, startPointer + 1);
			if (startPointer == -1) {
				final String msg = "Separator:" + separator + " Position:" + position + " existirt nicht. Line:" + line;
				log.error(msg);
				throw new IndexOutOfBoundsException(msg);
			}
		}

		// Kursor "pointer" wurde positioniert.
		int endPointer = line.indexOf(separator, startPointer + 1);
		if (endPointer == -1) {
			endPointer = line.length();
		}
		if (startPointer != 0) {
			startPointer++;// Separator Character wird nicht \u00fcbernommen.
		}
		if ((startPointer >= line.length()) || (line.charAt(startPointer) == separator)) {
			return null;
		}
		final String ergebnis = line.substring(startPointer, endPointer);
		if (!ergebnis.isEmpty()) {
			return ergebnis.trim().replace(CommonConstants.CSV_PARAM_SEPARATOR_REPLACEMENT, CommonConstants.CSV_PARAM_SEPARATOR);
		}
		return null;
	}

}
