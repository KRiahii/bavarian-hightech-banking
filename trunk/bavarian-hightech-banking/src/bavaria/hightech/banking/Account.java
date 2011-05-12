package bavaria.hightech.banking;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.time.TimeEmitter;

/**
 * 
 * foundation class Account with
 * attributes for account number,
 * account balance and account holder
 * and methods to manage the amount of money.
 */

public abstract class Account {

	private int kNumber;
	protected Money kBalance;
	private String kHolder;
	private Booking accountingEntry;
	public Calendar createDate;
	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

	public Account(int kNummer, String kInhaber) {

		this.kNumber = kNummer;
		this.kHolder = kInhaber;
		this.kBalance = new Money(0, Money.Currency.EURO);
		this.accountingEntry = new Booking();
		createDate = TimeEmitter.getTimeEmitter().getCalender();
	}

	/**
	 * get_kHolder()
	 * 
	 * @return AccountHolder
	 */
	public String getHolder() {
		return this.kHolder;
	}

	/**
	 * get_kBalance()
	 * 
	 * @return balance(double)
	 */
	public long getAccountBalance() {
		if(kBalance != null)
		return kBalance.getValue();
		
		return 0;
	}

	public Calendar getCreationDate() {
		return createDate;
	}

	/**
	 * manageMoney()
	 * 
	 * @param
	 */
	public void manageMoney(String reason, long amount, char sign,
			Currency currency) throws MoneyException {

		if (amount < 0) {

			throw new MoneyException("invalid amount " + amount);
		}

		if (sign != '+' && sign != '-') {

			throw new MoneyException("invalid sign " + sign);
		}

		update(reason, amount, sign, currency);
	}

	/**
	 * get_kNummer()
	 * 
	 * @return accountNumber
	 */
	public int getKnumber() {
		return this.kNumber;
	}

	/**
	 * update()
	 * 
	 * @param
	 */
	protected void update(String reason, long amount, char sign,
			Currency currency) throws MoneyException {

		if (amount < 0)
			amount = 0;

		if (sign == '+')
			this.kBalance.addMoney(amount, currency);

		else
			this.kBalance.subMoney(amount, currency);

		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2);
		accountingEntry.insert(reason, n.format(amount), sign, currency);
	}

	@Override
	public String toString() {

		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2);

		StringBuilder sb = new StringBuilder();
		sb.append(
				"creation date: " + dateFormat.format(getCreationDate().getTime())
						+ " " + timeFormat.format(getCreationDate().getTime())).append(
				"\n");
		sb.append("Name: " + getHolder()).append("\n");
		sb.append("Accountnumber: " + getKnumber()).append("\n");
		sb.append("Credit account: " + n.format(getAccountBalance())).append("\n");
		sb.append("currency: " + this.kBalance.getCurrency());
		sb.append("\n--------------------------------------\n");
		sb.append(accountingEntry.toString());

		return sb.toString();
	}

	abstract public void printTyp();

	abstract public void payInterest() throws MoneyException;
}