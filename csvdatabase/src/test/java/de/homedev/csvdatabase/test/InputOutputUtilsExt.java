package de.homedev.csvdatabase.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.homedev.csvdatabase.utils.InputOutputJarUtils;
import de.homedev.csvdatabase.utils.InputOutputZipUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class InputOutputUtilsExt {
	private static final Map<String, List<BlzDto>> MAP = new HashMap<>();

	private InputOutputUtilsExt() {
	}

	public static BlzDto findInJarFile(Class clazz, String value, String dirInJar, String idxFileName, long recordsPerFile, Charset charset, boolean useCash)
			throws IOException {
		BlzDto result = null;
		if (useCash) {
			List<BlzDto> list = MAP.get(dirInJar);
			if (list == null) {
				list = new ArrayList<>(100);
				MAP.put(dirInJar, list);
			}
			BlzDto compDto = new BlzDto(value, null, null);
			int index = Collections.binarySearch(list, compDto, new BlzComparator());
			if (index >= 0) {
				return list.get(index);
			}
		}
		String line = InputOutputJarUtils.findInJarFile(clazz, value, '/' + dirInJar + '/', idxFileName, recordsPerFile, charset);
		if (line != null) {
			result = new BlzDto(line);
		}
		if (useCash && result != null) {
			MAP.get(dirInJar).add(result);
		}
		return result;
	}

	public static BlzDto findInZipFile(File zipFile, String value, String dirInJar, String idxFileName, long recordsPerFile, Charset charset, boolean useCash)
			throws IOException {
		BlzDto result = null;
		if (useCash) {
			List<BlzDto> list = MAP.get(dirInJar);
			if (list == null) {
				list = new ArrayList<>(100);
				MAP.put(dirInJar, list);
			}
			BlzDto compDto = new BlzDto(value, null, null);
			int index = Collections.binarySearch(list, compDto, new BlzComparator());
			if (index >= 0) {
				return list.get(index);
			}
		}
		String line = InputOutputZipUtils.findInZipFile(zipFile, value, dirInJar, idxFileName, recordsPerFile, charset);
		if (line != null) {
			result = new BlzDto(line);
		}
		if (useCash && result != null) {
			MAP.get(dirInJar).add(result);
		}
		return result;
	}
}
