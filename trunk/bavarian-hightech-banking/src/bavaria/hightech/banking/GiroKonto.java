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

	private GiroKonditionen gk;

	/**
	 * GiroKonto()
	 * 
	 * @param SollZins
	 * @param kNummer
	 * @param kInhaber
	 */

	public GiroKonto(int kNummer, String kInhaber, GiroKonditionen gk) {

		super(kNummer, kInhaber);
		this.gk = gk;
	}

	/**
	 * HabeZins()
	 * 
	 * @param HabeZins
	 */
	public void HabeZins(float HabeZins) throws MoneyException {

		double amount = (this.getKStand() / 100) * gk.getHabezins();
		this.manageMoney("HabeZins", amount, '+');
	}

	@Override
	public void verzinsen() throws MoneyException {

		double amount = ((this.getKStand() / 100) * gk.getSollzins()) * -1;
																
		this.manageMoney("SollZins", amount, '-');
	}
	
	@Override
	public void manageMoney(String reason, double amount, char sign)
			throws MoneyException {

		if (sign == '-')
			if (this.getKStand() - amount < gk.getUeberziehungsrahmen()*-1) {

				throw new MoneyException("Überziehungsrahmen überschritten!"
						+ getKStand() + " " + amount);
			}

		super.verbuchen(reason, amount, sign);
	}

	@Override
	public void printTyp() {
		System.out.println("GiroKonto:");
	}
}