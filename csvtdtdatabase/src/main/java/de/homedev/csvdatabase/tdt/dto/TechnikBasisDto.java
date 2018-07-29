package de.homedev.csvdatabase.tdt.dto;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
public class TechnikBasisDto {
	/**
	 * HER - Hersteller-Schlüsselnr. (2.1)
	 */

	private String herstellerSchluessel;

	/**
	 * TYP - Code zum Typ (2.2)
	 */

	private String typSchluessel;

	/**
	 * VVS - Code zu Variante / Version bzw. Ausführungs- Schlüsselnummer (2.2)
	 */

	private String vvsSchluessel;

	/**
	 * PZTYP - Prüfziffer zum Code Typ u.Variante / Version (2.2)
	 */

	private String pzhertypvvs;

	/**
	 * FZKL - Fahrzeugklasse (J)
	 */

	private String fahrzeugklasseSchluessel;

	/**
	 * AAUFB - Art des Aufbaus (4)
	 */

	private String aufbauSchluessel;

	/**
	 * MARKE - Marke (D.1)
	 */

	private String markeText;

	/**
	 * TXT25 - Typ (Klartext) (D.2)
	 */

	private String typText;

	/**
	 * TXVAR - Variante (Klartext) (D.2)
	 */

	private String varianteText;

	/**
	 * TXVER - Version (Klartext) (D.2)
	 */

	private String versionText;

	/**
	 * HDBEZ - Handelsbezeichnung(en) (D.3)
	 */

	private String handelsname;

	/**
	 * TXH25 - Hersteller-Kurzbezeichnung (2)
	 */

	private String herstellerText;

	/**
	 * TXFZK - Fahrzeugklasse (Klartext) (5)
	 */

	private String fahrzeugklasseText;

	/**
	 * TXAAU - Art des Aufbaus (Klartext) (5)
	 */

	private String aufbauText;

	/**
	 * TSSKL - Maßgebl. Schadstoffklasse für die EGTypgenehmigung (V.9)
	 */

	private String abgasText;

	/**
	 * TXEMI - Nat. Schadstoffklasse / Emissionsklasse (14)
	 */

	private String emiklasseText;

	/**
	 * TXKRE - Kraftstoffart oder Energiequelle (P.3)
	 */

	private String kraftstoffText;

	/**
	 * KREN - Code zur Kraftstoffart oder Energiequelle (10)
	 */

	private String kraftstoffSchluessel;

	/**
	 * EMI - Code zur nat. Schadstoffklasse / Emissionsklasse (14.1)
	 */

	private String emiklasseSchluessel;

	/**
	 * HUB - Hubraum in cm³ (P.1)
	 */

	private Long hubraum;

	/**
	 * ZDA - Anzahl der Achsen (L)
	 */

	private Long anzahlachsen;

	/**
	 * ADA2 - Anzahl der Antriebsachsen (9)
	 */

	private Long antriebsachsen;

	/**
	 * KW4 - Nennleistung in kW (P.2)
	 */

	private Long nennleistung;

	/**
	 * DREHZ - Nenndrehzahl (P.4)
	 */

	private Long drehzleistung;

	/**
	 * KMH - Höchstgeschwindigkeit in km/h (T)
	 */

	private Long hoechstgeschwindigkeit;

	/**
	 * RINH5 - Rauminhalt des Tanks (nur Tankfahrz.) (12)
	 */

	private Long rauminhalt;

	/**
	 * SITZM - Sitze einschl. Fahrersitz (S.1)
	 */

	private Long sitzplaetze;

	/**
	 * STEH - Stehplätze (S.2)
	 */

	private Long stehplaetze;

	/**
	 * LGMIN - Masse des in Betrieb befindlichen Fz. in kg min. (G)
	 */

	private Long massemin;

	/**
	 * LGMAX - Masse des in Betrieb befindlichen Fz. in kg max. (G)
	 */

	private Long massemax;

	/**
	 * TGM - technisch zulässige Gesamtmasse in kg (F.1)
	 */

	private Long techzulgesamtmasse;

	/**
	 * ZULGW - im Zulassungsstaat zul. Gesamtmasse in kg (F.2)
	 */

	private Long zulgesamtmasse;

	/**
	 * TACH1 - technische zulässige Achslast – Achse 1 in kg (7.1)
	 */

	private Long techachslast1;

	/**
	 * TACH2 - technische zulässige Achslast – Achse 2 in kg (7.2)
	 */

	private Long techachslast2;

	/**
	 * TACH3 - technische zulässige Achslast – Achse 3 in kg (7.3)
	 */

	private Long techachslast3;

	/**
	 * SLAST - Stützlast in kg (13)
	 */

	private Long stuetzlast;

	/**
	 * ACHS1 - zul. Achslast – Achse 1 in kg (8.1)
	 */

	private Long achslast1;

	/**
	 * ACHS2 - zul. Achslast – Achse 2 in kg (8.2)
	 */

	private Long achslast2;

	/**
	 * ACHS3 - zul. Achslast – Achse 3 in kg (8.3)
	 */

	private Long achslast3;

	/**
	 * CO2 - CO2 (V.7)
	 */

	private Long co2kombi;

	/**
	 * STDEZ - Standgeräusch in dB (A) (U.1)
	 */

	private String standgeraeusch;

	/**
	 * DSGER - Drehzahl in U/min zum Standgeräusch (U.2)
	 */

	private Long drehzstandgeraeusch;

	/**
	 * FADEZ - Fahrgeräusch in dB (A) (U.3)
	 */

	private String fahrgeraeusch;

	/**
	 * ZALG - techn. zul. Anhängelast gebremst in kg (O.1)
	 */

	private Long anhlastgebremst;

	/**
	 * ZALUG - techn. zul. Anhängelast ungebremst in kg (O.2)
	 */

	private Long anhlastungebremst;

	/**
	 * LGKR - Leistungsgewicht in kW/kg (nur Krafräder) (Q)
	 */
	// 0.01 - 999.99
	private Double leistungsgewicht;

	/**
	 * REIF1 - Bereifung – Achse 1 (15.1)
	 */

	private String bereifachse1;

	/**
	 * REIF2 - Bereifung – Achse 2 (15.2)
	 */

	private String bereifachse2;

	/**
	 * REIF3 - Bereifung – Achse 3 (15.3)
	 */

	private String bereifachse3;

	/**
	 * TGNR - Nummer der EG-Typgenehmigung oder ABE (K)
	 */

	private String genehmigungsnr;

	/**
	 * Datum zur EG-Typgenehmigung oder ABE (6)
	 */

	private String genehmigungsdatum;

	/**
	 * LAMIN - Länge des Fahrzeugs in mm (min) (18)
	 */

	private Long laengemin;

	/**
	 * LAMAX - Länge des Fahrzeugs in mm (max) (18)
	 */

	private Long laengemax;

	/**
	 * BRMIN - Breite des Fahrzeugs in mm (min) (19)
	 */

	private Long breitemin;

	/**
	 * BRMAX - Breite des Fahrzeugs in mm (max) (19)
	 */

	private Long breitemax;

	/**
	 * HOMIN - Höhe des Fahrzeugs in mm (min) (20)
	 */

	private Long hoehemin;

	/**
	 * HOMAX - Höhe des Fahrzeugs in mm (max) (20)
	 */

	private Long hoehemax;

	/**
	 * BEM - Bemerkungen und Ausnahmen (22)
	 */

	private String bemerkungen;

	/**
	 * Beiblatt
	 */

	private String beiblatt;

	public String getHerstellerSchluessel() {
		return herstellerSchluessel;
	}

	public void setHerstellerSchluessel(final String herstellerSchluessel) {
		this.herstellerSchluessel = herstellerSchluessel;
	}

	public String getTypSchluessel() {
		return typSchluessel;
	}

	public void setTypSchluessel(final String typSchluessel) {
		this.typSchluessel = typSchluessel;
	}

	public String getVvsSchluessel() {
		return vvsSchluessel;
	}

	public void setVvsSchluessel(final String vvsSchluessel) {
		this.vvsSchluessel = vvsSchluessel;
	}

	public String getPzhertypvvs() {
		return pzhertypvvs;
	}

	public void setPzhertypvvs(final String pzhertypvvs) {
		this.pzhertypvvs = pzhertypvvs;
	}

	public String getFahrzeugklasseSchluessel() {
		return fahrzeugklasseSchluessel;
	}

	public void setFahrzeugklasseSchluessel(final String fahrzeugklasseSchluessel) {
		this.fahrzeugklasseSchluessel = fahrzeugklasseSchluessel;
	}

	public String getAufbauSchluessel() {
		return aufbauSchluessel;
	}

	public void setAufbauSchluessel(final String aufbauSchluessel) {
		this.aufbauSchluessel = aufbauSchluessel;
	}

	public String getMarkeText() {
		return markeText;
	}

	public void setMarkeText(final String markeText) {
		this.markeText = markeText;
	}

	public String getTypText() {
		return typText;
	}

	public void setTypText(final String typText) {
		this.typText = typText;
	}

	public String getVarianteText() {
		return varianteText;
	}

	public void setVarianteText(final String varianteText) {
		this.varianteText = varianteText;
	}

	public String getVersionText() {
		return versionText;
	}

	public void setVersionText(final String versionText) {
		this.versionText = versionText;
	}

	public String getHandelsname() {
		return handelsname;
	}

	public void setHandelsname(final String handelsname) {
		this.handelsname = handelsname;
	}

	public String getHerstellerText() {
		return herstellerText;
	}

	public void setHerstellerText(final String herstellerText) {
		this.herstellerText = herstellerText;
	}

	public String getFahrzeugklasseText() {
		return fahrzeugklasseText;
	}

	public void setFahrzeugklasseText(final String fahrzeugklasseText) {
		this.fahrzeugklasseText = fahrzeugklasseText;
	}

	public String getAufbauText() {
		return aufbauText;
	}

	public void setAufbauText(final String aufbauText) {
		this.aufbauText = aufbauText;
	}

	public String getAbgasText() {
		return abgasText;
	}

	public void setAbgasText(final String abgasText) {
		this.abgasText = abgasText;
	}

	public String getEmiklasseText() {
		return emiklasseText;
	}

	public void setEmiklasseText(final String emiklasseText) {
		this.emiklasseText = emiklasseText;
	}

	public String getKraftstoffText() {
		return kraftstoffText;
	}

	public void setKraftstoffText(final String kraftstoffText) {
		this.kraftstoffText = kraftstoffText;
	}

	public String getKraftstoffSchluessel() {
		return kraftstoffSchluessel;
	}

	public void setKraftstoffSchluessel(final String kraftstoffSchluessel) {
		this.kraftstoffSchluessel = kraftstoffSchluessel;
	}

	public String getEmiklasseSchluessel() {
		return emiklasseSchluessel;
	}

	public void setEmiklasseSchluessel(final String emiklasseSchluessel) {
		this.emiklasseSchluessel = emiklasseSchluessel;
	}

	public Long getHubraum() {
		return hubraum;
	}

	public void setHubraum(final Long hubraum) {
		this.hubraum = hubraum;
	}

	public Long getAnzahlachsen() {
		return anzahlachsen;
	}

	public void setAnzahlachsen(final Long anzahlachsen) {
		this.anzahlachsen = anzahlachsen;
	}

	public Long getAntriebsachsen() {
		return antriebsachsen;
	}

	public void setAntriebsachsen(final Long antriebsachsen) {
		this.antriebsachsen = antriebsachsen;
	}

	public Long getNennleistung() {
		return nennleistung;
	}

	public void setNennleistung(final Long nennleistung) {
		this.nennleistung = nennleistung;
	}

	public Long getDrehzleistung() {
		return drehzleistung;
	}

	public void setDrehzleistung(final Long drehzleistung) {
		this.drehzleistung = drehzleistung;
	}

	public Long getHoechstgeschwindigkeit() {
		return hoechstgeschwindigkeit;
	}

	public void setHoechstgeschwindigkeit(final Long hoechstgeschwindigkeit) {
		this.hoechstgeschwindigkeit = hoechstgeschwindigkeit;
	}

	public Long getRauminhalt() {
		return rauminhalt;
	}

	public void setRauminhalt(final Long rauminhalt) {
		this.rauminhalt = rauminhalt;
	}

	public Long getSitzplaetze() {
		return sitzplaetze;
	}

	public void setSitzplaetze(final Long sitzplaetze) {
		this.sitzplaetze = sitzplaetze;
	}

	public Long getStehplaetze() {
		return stehplaetze;
	}

	public void setStehplaetze(final Long stehplaetze) {
		this.stehplaetze = stehplaetze;
	}

	public Long getMassemin() {
		return massemin;
	}

	public void setMassemin(final Long massemin) {
		this.massemin = massemin;
	}

	public Long getMassemax() {
		return massemax;
	}

	public void setMassemax(final Long massemax) {
		this.massemax = massemax;
	}

	public Long getTechzulgesamtmasse() {
		return techzulgesamtmasse;
	}

	public void setTechzulgesamtmasse(final Long techzulgesamtmasse) {
		this.techzulgesamtmasse = techzulgesamtmasse;
	}

	public Long getZulgesamtmasse() {
		return zulgesamtmasse;
	}

	public void setZulgesamtmasse(final Long zulgesamtmasse) {
		this.zulgesamtmasse = zulgesamtmasse;
	}

	public Long getTechachslast1() {
		return techachslast1;
	}

	public void setTechachslast1(final Long techachslast1) {
		this.techachslast1 = techachslast1;
	}

	public Long getTechachslast2() {
		return techachslast2;
	}

	public void setTechachslast2(final Long techachslast2) {
		this.techachslast2 = techachslast2;
	}

	public Long getTechachslast3() {
		return techachslast3;
	}

	public void setTechachslast3(final Long techachslast3) {
		this.techachslast3 = techachslast3;
	}

	public Long getStuetzlast() {
		return stuetzlast;
	}

	public void setStuetzlast(final Long stuetzlast) {
		this.stuetzlast = stuetzlast;
	}

	public Long getAchslast1() {
		return achslast1;
	}

	public void setAchslast1(final Long achslast1) {
		this.achslast1 = achslast1;
	}

	public Long getAchslast2() {
		return achslast2;
	}

	public void setAchslast2(final Long achslast2) {
		this.achslast2 = achslast2;
	}

	public Long getAchslast3() {
		return achslast3;
	}

	public void setAchslast3(final Long achslast3) {
		this.achslast3 = achslast3;
	}

	public Long getCo2kombi() {
		return co2kombi;
	}

	public void setCo2kombi(final Long co2kombi) {
		this.co2kombi = co2kombi;
	}

	public String getStandgeraeusch() {
		return standgeraeusch;
	}

	public void setStandgeraeusch(final String standgeraeusch) {
		this.standgeraeusch = standgeraeusch;
	}

	public Long getDrehzstandgeraeusch() {
		return drehzstandgeraeusch;
	}

	public void setDrehzstandgeraeusch(final Long drehzstandgeraeusch) {
		this.drehzstandgeraeusch = drehzstandgeraeusch;
	}

	public String getFahrgeraeusch() {
		return fahrgeraeusch;
	}

	public void setFahrgeraeusch(final String fahrgeraeusch) {
		this.fahrgeraeusch = fahrgeraeusch;
	}

	public Long getAnhlastgebremst() {
		return anhlastgebremst;
	}

	public void setAnhlastgebremst(final Long anhlastgebremst) {
		this.anhlastgebremst = anhlastgebremst;
	}

	public Long getAnhlastungebremst() {
		return anhlastungebremst;
	}

	public void setAnhlastungebremst(final Long anhlastungebremst) {
		this.anhlastungebremst = anhlastungebremst;
	}

	public Double getLeistungsgewicht() {
		return leistungsgewicht;
	}

	public void setLeistungsgewicht(final Double leistungsgewicht) {
		this.leistungsgewicht = leistungsgewicht;
	}

	public String getBereifachse1() {
		return bereifachse1;
	}

	public void setBereifachse1(final String bereifachse1) {
		this.bereifachse1 = bereifachse1;
	}

	public String getBereifachse2() {
		return bereifachse2;
	}

	public void setBereifachse2(final String bereifachse2) {
		this.bereifachse2 = bereifachse2;
	}

	public String getBereifachse3() {
		return bereifachse3;
	}

	public void setBereifachse3(final String bereifachse3) {
		this.bereifachse3 = bereifachse3;
	}

	public String getGenehmigungsnr() {
		return genehmigungsnr;
	}

	public void setGenehmigungsnr(final String genehmigungsnr) {
		this.genehmigungsnr = genehmigungsnr;
	}

	public String getGenehmigungsdatum() {
		return genehmigungsdatum;
	}

	public void setGenehmigungsdatum(final String genehmigungsdatum) {
		this.genehmigungsdatum = genehmigungsdatum;
	}

	public Long getLaengemin() {
		return laengemin;
	}

	public void setLaengemin(final Long laengemin) {
		this.laengemin = laengemin;
	}

	public Long getLaengemax() {
		return laengemax;
	}

	public void setLaengemax(final Long laengemax) {
		this.laengemax = laengemax;
	}

	public Long getBreitemin() {
		return breitemin;
	}

	public void setBreitemin(final Long breitemin) {
		this.breitemin = breitemin;
	}

	public Long getBreitemax() {
		return breitemax;
	}

	public void setBreitemax(final Long breitemax) {
		this.breitemax = breitemax;
	}

	public Long getHoehemin() {
		return hoehemin;
	}

	public void setHoehemin(final Long hoehemin) {
		this.hoehemin = hoehemin;
	}

	public Long getHoehemax() {
		return hoehemax;
	}

	public void setHoehemax(final Long hoehemax) {
		this.hoehemax = hoehemax;
	}

	public String getBemerkungen() {
		return bemerkungen;
	}

	public void setBemerkungen(final String bemerkungen) {
		this.bemerkungen = bemerkungen;
	}

	public String getBeiblatt() {
		return beiblatt;
	}

	public void setBeiblatt(final String beiblatt) {
		this.beiblatt = beiblatt;
	}

	@Override
	public String toString() {
		return "VkTechnikBasisDto [herstellerSchluessel=" + herstellerSchluessel + ", typSchluessel=" + typSchluessel + ", vvsSchluessel=" + vvsSchluessel
				+ ", pzhertypvvs=" + pzhertypvvs + ", fahrzeugklasseSchluessel=" + fahrzeugklasseSchluessel + ", aufbauSchluessel=" + aufbauSchluessel
				+ ", markeText=" + markeText + ", typText=" + typText + ", varianteText=" + varianteText + ", versionText=" + versionText + ", handelsname="
				+ handelsname + ", herstellerText=" + herstellerText + ", fahrzeugklasseText=" + fahrzeugklasseText + ", aufbauText=" + aufbauText
				+ ", abgasText=" + abgasText + ", emiklasseText=" + emiklasseText + ", kraftstoffText=" + kraftstoffText + ", kraftstoffSchluessel="
				+ kraftstoffSchluessel + ", emiklasseSchluessel=" + emiklasseSchluessel + ", hubraum=" + hubraum + ", anzahlachsen=" + anzahlachsen
				+ ", antriebsachsen=" + antriebsachsen + ", nennleistung=" + nennleistung + ", drehzleistung=" + drehzleistung + ", hoechstgeschwindigkeit="
				+ hoechstgeschwindigkeit + ", rauminhalt=" + rauminhalt + ", sitzplaetze=" + sitzplaetze + ", stehplaetze=" + stehplaetze + ", massemin="
				+ massemin + ", massemax=" + massemax + ", techzulgesamtmasse=" + techzulgesamtmasse + ", zulgesamtmasse=" + zulgesamtmasse + ", techachslast1="
				+ techachslast1 + ", techachslast2=" + techachslast2 + ", techachslast3=" + techachslast3 + ", stuetzlast=" + stuetzlast + ", achslast1="
				+ achslast1 + ", achslast2=" + achslast2 + ", achslast3=" + achslast3 + ", co2kombi=" + co2kombi + ", standgeraeusch=" + standgeraeusch
				+ ", drehzstandgeraeusch=" + drehzstandgeraeusch + ", fahrgeraeusch=" + fahrgeraeusch + ", anhlastgebremst=" + anhlastgebremst
				+ ", anhlastungebremst=" + anhlastungebremst + ", leistungsgewicht=" + leistungsgewicht + ", bereifachse1=" + bereifachse1 + ", bereifachse2="
				+ bereifachse2 + ", bereifachse3=" + bereifachse3 + ", genehmigungsnr=" + genehmigungsnr + ", genehmigungsdatum=" + genehmigungsdatum
				+ ", laengemin=" + laengemin + ", laengemax=" + laengemax + ", breitemin=" + breitemin + ", breitemax=" + breitemax + ", hoehemin=" + hoehemin
				+ ", hoehemax=" + hoehemax + ", bemerkungen=" + bemerkungen + ", beiblatt=" + beiblatt + "]";
	}

}
