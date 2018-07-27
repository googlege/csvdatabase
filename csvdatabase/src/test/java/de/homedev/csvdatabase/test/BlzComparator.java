package de.homedev.csvdatabase.test;

import java.util.Comparator;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class BlzComparator implements Comparator<BlzDto> {

	@Override
	public int compare(BlzDto o1, BlzDto o2) {
		String blz1 = o1.getBlz();
		String blz2 = o2.getBlz();
		return blz1.compareTo(blz2);
	}

}
