package de.homedev.csvdatabase.test;

import java.io.Serializable;

import de.homedev.csvdatabase.utils.CommonConstants;
import de.homedev.csvdatabase.utils.CsvUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class BlzDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String blz;

	private String bic;

	private String bankname;

	public BlzDto() {
	}

	public BlzDto(String blz, String bic, String bankname, String plz, String ort) {
		super();
		this.blz = blz;
		this.bic = bic;
		this.bankname = bankname;

	}

	public BlzDto(String line) {
		super();
		this.blz = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, TestConstants.BLZ_ID);
		this.bic = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, TestConstants.BIC_ID);
		this.bankname = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, TestConstants.BANKNAME_ID);
	}

	public String getBlz() {
		return blz;
	}

	public void setBlz(String blz) {
		this.blz = blz;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	@Override
	public String toString() {
		return "BlzDto [blz=" + blz + ", bic=" + bic + ", bankname=" + bankname + "]";
	}

}
