package de.homedev.csvdatabase.tdt.dto;

import de.homedev.csvdatabase.utils.CommonConstants;
import de.homedev.csvdatabase.utils.CsvUtils;
import de.homedev.csvdatabase.utils.CsvLineToDto;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class HerstellerDto implements Comparable<HerstellerDto>, CsvLineToDto<HerstellerDto> {
	private String schluessel;
	private String text;

	public HerstellerDto() {
		super();
	}

	public HerstellerDto(String schluessel, String text) {
		super();
		this.schluessel = schluessel;
		this.text = text;
	}

	public HerstellerDto(String line) {
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
	public int compareTo(HerstellerDto o) {
		return o.getSchluessel().compareToIgnoreCase(this.schluessel);
	}

	@Override
	public HerstellerDto lineToDto(String line) {
		String schluessel = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, 0);
		String text = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, 1);
		return new HerstellerDto(schluessel, text);
	}

}
