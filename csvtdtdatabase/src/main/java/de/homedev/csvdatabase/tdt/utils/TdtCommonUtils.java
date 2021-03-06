package de.homedev.csvdatabase.tdt.utils;

import org.apache.log4j.Logger;

import de.homedev.csvdatabase.tdt.dto.TechnikBasisDto;
import de.homedev.csvdatabase.utils.CommonConstants;
import de.homedev.csvdatabase.utils.CsvUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public final class TdtCommonUtils implements CommonConstants {
	private static Logger log = Logger.getLogger(TdtCommonUtils.class);

	private TdtCommonUtils() {
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

	public static TechnikBasisDto lineToDto(String line) {
		/**
		 * !Static variables use memory, but will be initialized only one time! It's
		 * like csv databese itself. Its dependent on what you want....
		 */
		/* 3 4 (003_006) */int HERST_SCHLUESSEL;
		/* 4 3 (007_009) */int TYP_SCHLUESSEL;
		/* 5 5 (010_014) */int VVS_SCHLUESSEL;

		/* 6 1 (015_015) */int TYP_VVS_PRUEFZIFFER;
		/* 7 4 (016_019) */int FAHRZEUGKLASSE;
		/* 8 4 (020_023) */int CODE_AUFBAU;
		/* 9 25 (024_048) */int FABRIKMARKE;

		/* 10 25 (049_073) */int AMTL_KLARTEXT_TYP;
		/* 11 25 (074_098) */int VARIANTE;
		/* 12 35 (099_133) */int VERSION;
		/* 13 25 (134_158) */int HANDELSNAME;
		/* 14 25 (159_183) */int HERST_TEXT;
		/* 15 25 (184_208) */int TXT_FAHRZEUG_KLASSE;
		/* 16 25 (209_233) */int AMTL_KLAR_TEXTAUFBAU;
		/* 17 25 (234_258) */int ABGASRICHTL_TG;
		/* 18 25 (259_283) */int NATIONALE_EMIKL;
		/* 19 4 (304_307) */int CODE_KRAFTSTOFF;
		/* 20 20 (284_303) */int TXT_KRAFT_STOFF_KURZ;
		/* 21 10 (308_317) */int SLD;
		/* 22 5 (318_322) */int HUBRAUM;
		/* 23 2 (323_324) */int ANZACHS;
		/* 24 2 (325_326) */int ANTRIEBSACHS;
		/* 25 4 (327_330) */int NENNLEIST_KW_4;
		/* 26 5 (331_335) */int BEIUMDREH;
		/* 27 3 (336_338) */int HOECHSTGESCHW;
		/* 28 5 (339_343) */int FASSVERMOEGEN;
		/* 29 3 (344_346) */int ANZSITZE;
		/* 30 3 (347_349) */int ANZSTEHPLAETZE;
		/* 31 5 (350_354) */int MASSEFAHR_BEREITMIN;
		/* 32 5 (355_359) */int MASSEFAHR_BEREITMAX;
		/* 33 6 (360_365) */int TECHZULGESAMTM;
		/* 34 6 (366_371) */int ZULSTAAT_ZUL_GESAMTMASSE;
		/* 35 5 (372_376) */int TECHZULMAX_ACHSLAST_MASSE_ACHSGR1;
		/* 36 5 (377_381) */int TECHZULMAX_ACHSLAST_MASSE_ACHSGR2;
		/* 37 5 (382_386) */int TECHZULMAX_ACHSLAST_MASSE_ACHSGR3;
		/* 38 5 (387_391) */int ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS1;
		/* 39 5 (392_396) */int ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS2;
		/* 40 5 (397_401) */int ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS3;
		/* 41 3 (402_404) */int CO2KOMBI;
		/* 42 3 (405_407) */int STANDGERAEUSCH_3;
		/* 43 5 (408_412) */int DREHZSTAND_GERAEUSCH;
		/* 44 3 (413_415) */int FAHRGERAEUSCH_3;
		/* 45 5 (416_420) */int TECHZUL_ANHLAST_GEBREM;
		/* 46 4 (421_424) */int TECHZUL_ANHLAST_UNGEBREM;
		/* 47 6 (425_430) */int LEISTUNGSGEW;
		/* 48 5 (431_435) */int LAENGEMIN;
		/* 49 5 (436_440) */int LAENGEMAX;
		/* 50 4 (441_444) */int BREITEMIN;
		/* 51 4 (445_448) */int BREITEMAX;
		/* 52 4 (449_452) */int HOEHEMIN;
		/* 53 4 (453_456) */int HOEHEMAX;
		/* 54 5 ( 457_461) */int STUETZLAST;
		/* 55 25 (462_486) */int BEREIFACHSE1;
		/* 56 25 (487_511) */int BEREIFACHSE2;
		/* 57 25 (512_536) */int BEREIFACHSE3;
		/* 58 25 (537_561) */int GENEHMIGUNGSNR01;
		/* 59 8 (562_569) */int GENEHMIGUNGSDAT01;
		/* 60 378 (570_947) */int BEMERKUNGEN_AUSNAHMEN;
		int i = 0;
		/* 3 4 (003_006) */HERST_SCHLUESSEL = i++;
		/* 4 3 (007_009) */TYP_SCHLUESSEL = i++;
		/* 5 5 (010_014) */VVS_SCHLUESSEL = i++;
		/* 6 1 (015_015) */TYP_VVS_PRUEFZIFFER = i++;
		/* 7 4 (016_019) */FAHRZEUGKLASSE = i++;
		/* 8 4 (020_023) */CODE_AUFBAU = i++;
		/* 9 25 (024_048) */FABRIKMARKE = i++;
		/* 10 25 (049_073) */AMTL_KLARTEXT_TYP = i++;
		/* 11 25 (074_098) */VARIANTE = i++;
		/* 12 35 (099_133) */VERSION = i++;
		/* 13 25 (134_158) */HANDELSNAME = i++;
		/* 14 25 (159_183) */HERST_TEXT = i++;
		/* 15 25 (184_208) */TXT_FAHRZEUG_KLASSE = i++;
		/* 16 25 (209_233) */AMTL_KLAR_TEXTAUFBAU = i++;
		/* 17 25 (234_258) */ABGASRICHTL_TG = i++;
		/* 18 25 (259_283) */NATIONALE_EMIKL = i++;
		/* 20 20 (284_303) */TXT_KRAFT_STOFF_KURZ = i++;
		/* 19 4 (304_307) */CODE_KRAFTSTOFF = i++;
		/* 21 10 (308_317) */SLD = i++;
		/* 22 5 (318_322) */HUBRAUM = i++;
		/* 23 2 (323_324) */ANZACHS = i++;
		/* 24 2 (325_326) */ANTRIEBSACHS = i++;
		/* 25 4 (327_330) */NENNLEIST_KW_4 = i++;
		/* 26 5 (331_335) */BEIUMDREH = i++;
		/* 27 3 (336_338) */HOECHSTGESCHW = i++;
		/* 28 5 (339_343) */FASSVERMOEGEN = i++;
		/* 29 3 (344_346) */ANZSITZE = i++;
		/* 30 3 (347_349) */ANZSTEHPLAETZE = i++;
		/* 31 5 (350_354) */MASSEFAHR_BEREITMIN = i++;
		/* 32 5 (355_359) */MASSEFAHR_BEREITMAX = i++;
		/* 33 6 (360_365) */TECHZULGESAMTM = i++;
		/* 34 6 (366_371) */ZULSTAAT_ZUL_GESAMTMASSE = i++;
		/* 35 5 (372_376) */TECHZULMAX_ACHSLAST_MASSE_ACHSGR1 = i++;
		/* 36 5 (377_381) */TECHZULMAX_ACHSLAST_MASSE_ACHSGR2 = i++;
		/* 37 5 (382_386) */TECHZULMAX_ACHSLAST_MASSE_ACHSGR3 = i++;
		/* 38 5 (387_391) */ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS1 = i++;
		/* 39 5 (392_396) */ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS2 = i++;
		/* 40 5 (397_401) */ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS3 = i++;
		/* 41 3 (402_404) */CO2KOMBI = i++;
		/* 42 3 (405_407) */STANDGERAEUSCH_3 = i++;
		/* 43 5 (408_412) */DREHZSTAND_GERAEUSCH = i++;
		/* 44 3 (413_415) */FAHRGERAEUSCH_3 = i++;
		/* 45 5 (416_420) */TECHZUL_ANHLAST_GEBREM = i++;
		/* 46 4 (421_424) */TECHZUL_ANHLAST_UNGEBREM = i++;
		/* 47 6 (425_430) */LEISTUNGSGEW = i++;
		/* 48 5 (431_435) */LAENGEMIN = i++;
		/* 49 5 (436_440) */LAENGEMAX = i++;
		/* 50 4 (441_444) */BREITEMIN = i++;
		/* 51 4 (445_448) */BREITEMAX = i++;
		/* 52 4 (449_452) */HOEHEMIN = i++;
		/* 53 4 (453_456) */HOEHEMAX = i++;
		/* 54 5 ( 457_461) */STUETZLAST = i++;
		/* 55 25 (462_486) */BEREIFACHSE1 = i++;
		/* 56 25 (487_511) */BEREIFACHSE2 = i++;
		/* 57 25 (512_536) */BEREIFACHSE3 = i++;
		/* 58 25 (537_561) */GENEHMIGUNGSNR01 = i++;
		/* 59 8 (562_569) */GENEHMIGUNGSDAT01 = i++;
		/* 60 378 (570_947) */BEMERKUNGEN_AUSNAHMEN = i++;

		TechnikBasisDto dto = new TechnikBasisDto();
		String[] array = CsvUtils.split(line, CommonConstants.CSV_PARAM_SEPARATOR_STR, 58);
		dto.setHerstellerSchluessel(array[HERST_SCHLUESSEL]);
		dto.setTypSchluessel(array[TYP_SCHLUESSEL]);
		dto.setVvsSchluessel(array[VVS_SCHLUESSEL]);
		dto.setPzhertypvvs(array[TYP_VVS_PRUEFZIFFER]);
		dto.setFahrzeugklasseSchluessel(array[FAHRZEUGKLASSE]);
		dto.setAufbauSchluessel(array[CODE_AUFBAU]);
		dto.setMarkeText(array[FABRIKMARKE]);
		dto.setTypText(array[AMTL_KLARTEXT_TYP]);
		dto.setVarianteText(array[VARIANTE]);
		dto.setVersionText(array[VERSION]);
		dto.setHandelsname(array[HANDELSNAME]);
		dto.setHerstellerText(array[HERST_TEXT]);
		dto.setFahrzeugklasseText(array[TXT_FAHRZEUG_KLASSE]);
		dto.setAufbauText(array[AMTL_KLAR_TEXTAUFBAU]);
		dto.setAbgasText(array[ABGASRICHTL_TG]);
		dto.setEmiklasseText(array[NATIONALE_EMIKL]);
		dto.setKraftstoffText(array[TXT_KRAFT_STOFF_KURZ]);
		dto.setKraftstoffSchluessel(array[CODE_KRAFTSTOFF]);
		dto.setEmiklasseSchluessel(array[SLD]);
		dto.setHubraum(CsvUtils.stringToLong(array[HUBRAUM]));
		dto.setAnzahlachsen(CsvUtils.stringToLong(array[ANZACHS]));
		dto.setAntriebsachsen(CsvUtils.stringToLong(array[ANTRIEBSACHS]));
		dto.setNennleistung(CsvUtils.stringToLong(array[NENNLEIST_KW_4]));
		dto.setDrehzleistung(CsvUtils.stringToLong(array[BEIUMDREH]));
		dto.setHoechstgeschwindigkeit(CsvUtils.stringToLong(array[HOECHSTGESCHW]));
		dto.setRauminhalt(CsvUtils.stringToLong(array[FASSVERMOEGEN]));
		dto.setSitzplaetze(CsvUtils.stringToLong(array[ANZSITZE]));
		dto.setStehplaetze(CsvUtils.stringToLong(array[ANZSTEHPLAETZE]));
		dto.setMassemin(CsvUtils.stringToLong(array[MASSEFAHR_BEREITMIN]));
		dto.setMassemax(CsvUtils.stringToLong(array[MASSEFAHR_BEREITMAX]));
		dto.setTechzulgesamtmasse(CsvUtils.stringToLong(array[TECHZULGESAMTM]));
		dto.setZulgesamtmasse(CsvUtils.stringToLong(array[ZULSTAAT_ZUL_GESAMTMASSE]));
		dto.setTechachslast1(CsvUtils.stringToLong(array[TECHZULMAX_ACHSLAST_MASSE_ACHSGR1]));
		dto.setTechachslast2(CsvUtils.stringToLong(array[TECHZULMAX_ACHSLAST_MASSE_ACHSGR2]));
		dto.setTechachslast3(CsvUtils.stringToLong(array[TECHZULMAX_ACHSLAST_MASSE_ACHSGR3]));
		dto.setAchslast1(CsvUtils.stringToLong(array[ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS1]));
		dto.setAchslast2(CsvUtils.stringToLong(array[ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS2]));
		dto.setAchslast3(CsvUtils.stringToLong(array[ZULMAX_ACHSLAST_MITGLIEDSTAAT_ACHS3]));
		dto.setCo2kombi(CsvUtils.stringToLong(array[CO2KOMBI]));
		dto.setStandgeraeusch(array[STANDGERAEUSCH_3]);
		dto.setDrehzstandgeraeusch(CsvUtils.stringToLong(array[DREHZSTAND_GERAEUSCH]));
		dto.setFahrgeraeusch(array[FAHRGERAEUSCH_3]);
		dto.setAnhlastgebremst(CsvUtils.stringToLong(array[TECHZUL_ANHLAST_GEBREM]));
		dto.setAnhlastungebremst(CsvUtils.stringToLong(array[TECHZUL_ANHLAST_UNGEBREM]));
		dto.setLeistungsgewicht(CsvUtils.stringToDouble(array[TECHZUL_ANHLAST_UNGEBREM]));
		dto.setBereifachse1(array[BEREIFACHSE1]);
		dto.setBereifachse2(array[BEREIFACHSE2]);
		dto.setBereifachse3(array[BEREIFACHSE3]);
		dto.setGenehmigungsnr(array[GENEHMIGUNGSNR01]);
		dto.setGenehmigungsdatum(array[GENEHMIGUNGSDAT01]);
		dto.setLaengemin(CsvUtils.stringToLong(array[LAENGEMIN]));
		dto.setStuetzlast(CsvUtils.stringToLong(array[STUETZLAST]));
		dto.setLaengemax(CsvUtils.stringToLong(array[LAENGEMAX]));
		dto.setBreitemin(CsvUtils.stringToLong(array[BREITEMIN]));
		dto.setBreitemax(CsvUtils.stringToLong(array[BREITEMAX]));
		dto.setHoehemin(CsvUtils.stringToLong(array[HOEHEMIN]));
		dto.setHoehemax(CsvUtils.stringToLong(array[HOEHEMAX]));
		dto.setBemerkungen(array[BEMERKUNGEN_AUSNAHMEN]);
		return dto;
	}

}
