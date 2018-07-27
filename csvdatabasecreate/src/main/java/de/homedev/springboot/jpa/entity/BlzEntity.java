package de.homedev.springboot.jpa.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import de.homedev.springboot.jpa.utils.CsvUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = BlzEntity.TABLE_NAME)
public class BlzEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "BLZ";
	public static final int BLZ_MAX_LENGTH = 8;
	public static final int BIC_MAX_LENGTH = 11;
	public static final int BANKNAME_MAX_LENGTH = 58 + 2;
	private static final String TMP_INSERT = "insert into blz (id,version,blz,bic,bankname) values (";
	private static final char COMMA = ',';
	private static final char AP = '\'';

	// public static final int PLZ_MAX_LENGTH = 256;
	// public static final int ORT_MAX_LENGTH = 256;

	@Id
	@TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
	@Column(name = "ID")
	private Long id;

	@Column(name = "BLZ", length = BLZ_MAX_LENGTH, nullable = false)
	private String blz;

	@Column(name = "BIC", length = BIC_MAX_LENGTH, nullable = true)
	private String bic;

	@Column(name = "BANKNAME", length = BANKNAME_MAX_LENGTH, nullable = true)
	private String bankname;

	// @Column(name = "PLZ", length = PLZ_MAX_LENGTH)
	// private String plz;
	//
	// @Column(name = "ORT", length = ORT_MAX_LENGTH)
	// private String ort;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	public BlzEntity() {
	}

	public BlzEntity(String blz, String bic, String bankname, String plz, String ort) {
		super();
		this.blz = blz;
		this.bic = bic;
		this.bankname = bankname;
		// this.plz = plz;
		// this.ort = ort;
	}

	public BlzEntity(String line) {
		super();
		this.blz = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, CommonConstants.BLZ_ID);
		this.bic = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, CommonConstants.BIC_ID);
		this.bankname = CsvUtils.valueFromCSVLine(line, CommonConstants.CSV_PARAM_SEPARATOR, CommonConstants.BANKNAME_ID);
		// this.plz = CsvUtils.valueFromCSVLine(line,
		// CommonConstants.CSV_PARAM_SEPARATOR, CommonConstants.PLZ_ID);
		// this.ort = CsvUtils.valueFromCSVLine(line,
		// CommonConstants.CSV_PARAM_SEPARATOR, CommonConstants.ORT_ID);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BlzEntity [id=" + id + ", blz=" + blz + ", bic=" + bic + ", bankname=" + bankname + ", version=" + version + "]";
	}

	public byte[] toSqlInsert(long id) {
		StringBuilder sb = new StringBuilder(200);
		sb.append(TMP_INSERT).append(id).append(COMMA);
		sb.append('0').append(COMMA);
		sb.append(AP).append(blz).append(AP).append(COMMA);
		if (bic != null && !bic.isEmpty()) {
			sb.append(AP).append(bic).append(AP).append(COMMA);
		} else {
			sb.append(bic).append(COMMA);
		}
		if (bankname != null && !bankname.isEmpty()) {
			sb.append(AP).append(bankname).append(AP);
		} else {
			sb.append(bankname);
		}
		sb.append(");\r\n");
		return sb.toString().getBytes(CommonConstants.CHARSET);
	}

	public String toCsvInsert() {
		StringBuilder sb = new StringBuilder(200);
		sb.append(blz).append(CommonConstants.CSV_PARAM_SEPARATOR);
		if (bic != null && !bic.isEmpty()) {
			sb.append(bic).append(CommonConstants.CSV_PARAM_SEPARATOR);
		} else {
			sb.append(CommonConstants.CSV_PARAM_SEPARATOR);
		}
		if (bankname != null && !bankname.isEmpty()) {
			sb.append(bankname.replace(CommonConstants.CSV_PARAM_SEPARATOR, CommonConstants.CSV_PARAM_SEPARATOR_REPLACEMENT));
		}
		sb.append("\r\n");
		return sb.toString();
	}

}
