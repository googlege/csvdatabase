package de.homedev.springboot.jpa.utils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class FileUtils {

	private static final String APOSTROPHE = "'";
	private static final String SQL_APOSTROPHE_REPLACEMENT = "''";

	private FileUtils() {
	}

	public static String checkDirName(final String original) {
		for (final DeprecatedFileNamesEnum sz : DeprecatedFileNamesEnum.values()) {
			final String deprName = sz.getName();
			if (deprName.equalsIgnoreCase(original)) {
				return sz.getReplacement();
			}
		}
		return original;
	}

}
