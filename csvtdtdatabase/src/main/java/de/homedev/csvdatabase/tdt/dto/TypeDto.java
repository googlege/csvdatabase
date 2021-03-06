package de.homedev.csvdatabase.tdt.dto;

import de.homedev.csvdatabase.utils.CommonConstants;
import de.homedev.csvdatabase.utils.CsvLineToDto;
import de.homedev.csvdatabase.utils.CsvUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class TypeDto implements Comparable<TypeDto>, CsvLineToDto<TypeDto> {
	private String schluessel;
	private String text;

	public TypeDto() {
		super();
	}

	public TypeDto(String schluessel, String text) {
		super();
		this.schluessel = schluessel;
		this.text = text;
	}

	public TypeDto(String line) {
		super();
		this.schluessel = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, 0);
		this.text = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, 1);
	}

	public String getSchluessel() {
		return schluessel;
	}

	public void setSchluessel(String schluessel) {
		this.schluessel = schluessel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int compareTo(TypeDto o) {
		return o.getSchluessel().compareToIgnoreCase(this.schluessel);
	}

	@Override
	public TypeDto lineToDto(String line) {
		String[] array = CsvUtils.split(line, CommonConstants.CSV_PARAM_SEPARATOR_STR, 2);
		String schluessel = array[0];
		String text = array[1];
		return new TypeDto(schluessel, text);
	}

}
