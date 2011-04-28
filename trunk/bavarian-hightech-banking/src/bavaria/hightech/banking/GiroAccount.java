package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * Giroaccount with debit interest and interest methods
 * 			-negative amount possible-
 * 
 */

public class GiroAccount extends Account {

	private GiroConditions giroConditions;

	/**
	 * GiroAccount()
	 * 
	 * @param
	 * 
	 */

	public GiroAccount(int kNumber, String kHolder, GiroConditions giroConditions) {

		super(kNumber, kHolder);
		this.giroConditions = giroConditions;
	}

	/**
	 * depositInterest()
	 * 
	 * @param
	 */
	public void depositInterest(float depositInterest) throws MoneyException {

		long amount = (long) ((this.getAccountBalance() / 100) * giroConditions.getCreditInterest());
		this.manageMoney("HabeZins", amount, '+', this.kBalance.getCurrency());
	}

	@Override
	public void payInterest() throws MoneyException {

		long amount = (long) (((this.getAccountBalance() / 100) * giroConditions.getDebitInterest()) * -1);

		this.manageMoney("SollZins", amount, '-', this.kBalance.getCurrency());
	}

	@Override
	public void manageMoney(String reason, long amount, char sign,
			Currency currency) throws MoneyException {
		Money money = new Money(amount, currency);

		if (sign == '-')
			if (this.getAccountBalance() - money.getValue() < giroConditions
					.getOverpullingFrame()
					* -1) {

				throw new MoneyException("Überziehungsrahmen überschritten!"
						+ getAccountBalance() + " " + money.getValue());
			}

		super.update(reason, amount, sign, currency);
	}

	@Override
	public void printTyp() {
		System.out.println("GiroKonto:");
	}
}