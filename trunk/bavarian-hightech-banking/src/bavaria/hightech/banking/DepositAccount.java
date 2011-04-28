package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * Deposit account with credit interest attributes
 *  		-no negative amount-
 * 
 */

public class DepositAccount extends Account {

	private DepositConditions depositConditions;

	/**
	 * DepositAccount()
	 * 
	 * @param interest
	 * @param kNumber
	 * @param kHolder
	 */
	public DepositAccount(int kNumber, String kHolder, DepositConditions fk) {

		super(kNumber, kHolder);
	}

	@Override
	public void payInterest() throws MoneyException {

		long amount = (long) ((this.getAccountBalance() / 100) * depositConditions.getInterest());
		this.manageMoney("interest", amount, '+', this.kBalance.getCurrency());
	}

	@Override
	public void printTyp() {
		System.out.println("DepositAccount:");
	}

	/**
	 * set_kStand()
	 * 
	 * @param
	 */

	@Override
	public void manageMoney(String reason, long amount, char sign,
			Currency currency) throws MoneyException {
		Money money = new Money(amount, currency);

		if (sign == '-')
			if (this.getAccountBalance() - money.getValue() < 0) {

				throw new MoneyException("not enought money "
						+ getAccountBalance() + " " + money.getValue());
			}

		super.update(reason, amount, sign, currency);
	}
}