package de.homedev.csvdatabase.test;

import java.io.Serializable;

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
public class BlzDto implements Serializable, CsvLineToDto<BlzDto> {

	private static final long serialVersionUID = 1L;

	private String blz;

	private String bic;

	private String bankname;

	public BlzDto() {
	}

	public BlzDto(String blz, String bic, String bankname) {
		super();
		this.blz = blz;
		this.bic = bic;
		this.bankname = bankname;

	}

	public BlzDto(String line) {
		super();
		BlzDto dto = lineToDto(line);
		this.blz = dto.blz;
		this.bic = dto.bic;
		this.bankname = dto.bankname;
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

	@Override
	public BlzDto lineToDto(String line) {
		String[] array = CsvUtils.split(line, CommonConstants.CSV_PARAM_SEPARATOR_STR, 3);
		String blz = array[TestConstants.BLZ_ID];
		String bic = array[TestConstants.BIC_ID];
		String bankname = array[TestConstants.BANKNAME_ID];
		return new BlzDto(blz, bic, bankname);
	}

}
