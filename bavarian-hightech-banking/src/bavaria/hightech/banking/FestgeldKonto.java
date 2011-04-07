package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * FestgeldKonto mit einem HabeZins Attribut und entsprechender
 * Verzinsungsmethode !!!!kein negativer Kontostand möglich!!!!
 * 
 */

public class FestgeldKonto extends Konto {

	private FestgeldKonditionen fk;

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

		long amount = (long) ((this.getKStand() / 100) * fk.getZins());
		this.manageMoney("Zins", amount, '+', this.kStand.getCurrency());
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
	public void manageMoney(String reason, long amount, char sign, Currency currency)
			throws MoneyException {
		Money money = new Money(amount, currency);

		if (sign == '-')
			if (this.getKStand() - money.getValue() < 0) {

				throw new MoneyException("Nicht genug Geld vorhanden "
						+ getKStand() + " " + money.getValue());
			}

		super.verbuchen(reason, amount, sign, currency);
	}
}