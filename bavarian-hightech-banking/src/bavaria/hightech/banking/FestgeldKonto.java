package bavaria.hightech.banking;

import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * FestgeldKonto mit einem HabeZins Attribut und entsprechender
 * Verzinsungsmethode !!!!kein negativer Kontostand möglich!!!!
 * 
 */

public class FestgeldKonto extends Konto {

	/**
	 * FestgeldKonto()
	 * 
	 * @param zins
	 * @param kNummer
	 * @param kInhaber
	 */
	public FestgeldKonto(int kNummer, String kInhaber, FestgeldKonditionen fk) {

		super(kNummer, kInhaber);
	}

	@Override
	public void verzinsen() throws MoneyException {

		double amount = (this.getKStand() / 100) * 2; // ÄNDERUNG!!! <--- * ZINS
		this.manageMoney("Zins", amount, '+');
	}

	@Override
	public void printTyp() {
		System.out.println("FestgeldKonto:");
	}

	/**
	 * set_kStand()
	 * 
	 * @param
	 */

	@Override
	public void manageMoney(String reason, double amount, char sign)
			throws MoneyException {

		if (sign == '-')
			if (this.getKStand() - amount < 0) {

				throw new MoneyException("Nicht genug Geld vorhanden "
						+ getKStand() + " " + amount);
			}

		super.verbuchen(reason, amount, sign);
	}
}