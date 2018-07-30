package de.homedev.csvdatabase.test;

import de.homedev.csvdatabase.utils.InputOutputJarUtils;

public class Main {
	private static final String BLZ = "70020270";
	private static final String KONTONUMMER = "2712563";

	public static void main(String[] args) {
		try {
			final String landerkennung = "131400";// 1314 (D = 13, E = 14) + 00
			String normkontonummer = CheckDigitUtils.toNormNummer(KONTONUMMER, 10, '0');
			String ibanPruefziffer = CheckDigitUtils.calculatePruefziffer(KONTONUMMER, BLZ, landerkennung);
			final StringBuffer iban = new StringBuffer(22);
			iban.append("DE").append(ibanPruefziffer).append(BLZ).append(normkontonummer);
			System.out.println("Input");
			System.out.println("BLZ:" + BLZ + " KONTONUMMER:" + KONTONUMMER);
			System.out.println("Output");
			System.out.println("IBAN:" + iban.toString());
			BlzDto blzDto = InputOutputUtilsExt.findInJarFile(InputOutputJarUtils.class, BLZ, "blz", TestConstants.INDEX_FILENAME, TestConstants.RECORDS_PER_FILE,
					TestConstants.CHARSET, true);
			if (blzDto != null) {
				System.out.println("BIC:" + blzDto.getBic());
				System.out.println("Bankname:" + blzDto.getBankname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
