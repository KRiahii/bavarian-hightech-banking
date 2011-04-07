package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
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

		long amount = (long) ((this.getKStand() / 100) * gk.getHabezins());
		this.manageMoney("HabeZins", amount, '+', this.kStand.getCurrency());
	}

	@Override
	public void verzinsen() throws MoneyException {

		long amount = (long) (((this.getKStand() / 100) * gk.getSollzins()) * -1);

		this.manageMoney("SollZins", amount, '-', this.kStand.getCurrency());
	}

	@Override
	public void manageMoney(String reason, long amount, char sign, Currency currency)
			throws MoneyException {
		Money money = new Money(amount, currency);

		if (sign == '-')
			if (this.getKStand() - money.getValue() < gk.getUeberziehungsrahmen() * -1) {

				throw new MoneyException("Überziehungsrahmen überschritten!"
						+ getKStand() + " " + money.getValue());
			}

		super.verbuchen(reason, amount, sign, currency);
	}

	@Override
	public void printTyp() {
		System.out.println("GiroKonto:");
	}
}