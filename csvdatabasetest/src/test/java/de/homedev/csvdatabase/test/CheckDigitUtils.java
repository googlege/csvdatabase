package de.homedev.csvdatabase.test;

import org.apache.log4j.Logger;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class CheckDigitUtils {
	private static Logger log = Logger.getLogger(CheckDigitUtils.class);

	private CheckDigitUtils() {
	}

	public static String toNormNummer(final String param, final int schluesselLength, final char normChar) {
		String value = param;
		if (value == null) {
			value = Character.toString(normChar);
		}
		if (value.length() > schluesselLength) {
			log.error("toNummer i=" + value + " length>" + schluesselLength);
			return value;
		}
		final StringBuffer resultSB = new StringBuffer(schluesselLength);
		final int delta = schluesselLength - value.length();
		for (int j = 0; j < delta; j++) {
			resultSB.append(normChar);
		}
		resultSB.append(value);
		return resultSB.toString();
	}

	private static final long MAX = 999999999;
	private static final long MODULUS = 97;

	/*
	 * Zeichenkette - "code" - ist nummerischer Wert, ist aber fuer Long-Typ zu
	 * gross.
	 */
	private static int calculateModulus(final String code) {
		long total = 0;
		for (int i = 0; i < code.length(); i++) {
			final int charValue = Character.getNumericValue(code.charAt(i));
			if (charValue < 0 || charValue > 35) {
				throw new RuntimeException("Invalid Character[" + i + "] = '" + code.charAt(i) + "'");
			}
			total = (charValue > 9 ? total * 100 : total * 10) + charValue;
			if (total > MAX) {
				total = (total % MODULUS);
			}
		}
		return (int) (total % MODULUS);
	}

	public static String calculatePruefziffer(final String kontonummer, final String blz, final String landerkennung) {
		final StringBuffer sb = new StringBuffer(24);
		sb.append(blz);
		sb.append(toNormNummer(kontonummer, 10, '0'));
		sb.append(landerkennung);
		final int modulusResult = calculateModulus(sb.toString());
		final int charValue = (98 - modulusResult);
		final String checkDigit = Integer.toString(charValue);
		// System.out.println("sb=" + sb.toString() + " mod 97=" + modulusResult
		// + " 98-" + modulusResult + "=" + charValue);
		return (charValue > 9 ? checkDigit : "0" + checkDigit);
	}
}
