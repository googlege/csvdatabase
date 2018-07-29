package de.homedev.csvdatabase.tdt.utils;

//http://blog.onetechnical.com/2006/11/16/forbidden-file-and-folder-names-on-windows/
/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */

public enum DeprecatedFileNamesEnum {
	//@formatter:off	
    CON("CON", "CON0#"),
    PRN("PRN", "PRN0#"),
    AUX("AUX", "AUX0#"),
    NUL("NUL", "NUL0#"),
    COM1("COM1", "COM10#"),
    COM2("COM2", "COM20#"),
    COM3("COM3", "COM30#"),
    COM4("COM4", "COM40#"),
    COM5("COM5", "COM50#"),
    COM6("COM6", "COM60#"),
    COM7("COM7", "COM70#"),
    COM8("COM8", "COM80#"),
    COM9("COM9", "COM90#"),
    LPT1("LPT1", "LPT10#"),
    LPT2("LPT2", "LPT20#"),
    LPT3("LPT3", "LPT30#"),
    LPT4("LPT4", "LPT40#"),
    LPT5("LPT5", "LPT50#"),
    LPT6("LPT6", "LPT60#"),
    LPT7("LPT7", "LPT70#"),
    LPT8("LPT8", "LPT80#"),
    LPT9("LPT9", "LPT90#");
//@formatter:on    
	private final String name;
	private final String replacement;

	private DeprecatedFileNamesEnum(final String name, final String replacement) {
		this.name = name;
		this.replacement = replacement;
	}

	public String getName() {
		return name;
	}

	public String getReplacement() {
		return replacement;
	}

}
