package bavaria.hightech.banking;

import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * Girokonto mit einem SollZins Attribut und entsprechender Verzinsungsmethode
 * auch Methode für Verzinsung von HabeZins möglich ein Girokonto kann auch
 * einen negativen Kontostand auweisen.
 * 
 */

public class GiroKonto extends Konto {

	private float SollZins;

	/**
	 * GiroKonto()
	 * 
	 * @param SollZins
	 * @param kNummer
	 * @param kInhaber
	 */

	public GiroKonto(float SollZins, int kNummer, String kInhaber) {

		super(kNummer, kInhaber);
		this.SollZins = SollZins;
	}

	/**
	 * HabeZins()
	 * 
	 * @param HabeZins
	 */
	public void HabeZins(float HabeZins) throws MoneyException {

		double amount = (this.getKStand() / 100) * HabeZins;
		this.manageMoney("HabeZins", amount, '+');
	}

	@Override
	public void verzinsen() throws MoneyException {

		double amount = ((this.getKStand() / 100) * this.SollZins) * -1;
		this.manageMoney("SollZins", amount, '-');
	}

	@Override
	public void printTyp() {
		System.out.println("GiroKonto:");
	}
}