package bavaria.hightech.banking;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.time.TimeEmitter;

/**
 * 
 * foundation class Account with attributes for account number, account balance
 * and account holder and methods to manage the amount of money.
 */

public abstract class Account {

	private int kNumber;
	protected Money kBalance;
	private String kHolder;
	private Booking accountingEntry;
	public Calendar createDate;

	DateFormat dateFormat;
	DateFormat timeFormat;
	NumberFormat numberFormat;

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
		if (kBalance != null)
			return kBalance.getValue();

		return 0;
	}

	/**
	 * getCreationDate()
	 * 
	 * @return createDate(Calendar)
	 */
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

		accountingEntry.insert(reason, amount, sign, currency);
	}

	public String toString(int key, Locale currentLocale) {

		ResourceBundle bank = ResourceBundle.getBundle("i18n/BankBundle",
				currentLocale);

		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, currentLocale);
		timeFormat = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale);
		numberFormat = NumberFormat.getNumberInstance(currentLocale);

		accountingEntry.sort(key);

		StringBuilder sb = new StringBuilder();
		sb.append(
				bank.getString("creationDate") + ": "
						+ dateFormat.format(getCreationDate().getTime()) + " "
						+ timeFormat.format(getCreationDate().getTime()))
				.append("\n");
		sb.append(bank.getString("name") + ": " + getHolder()).append("\n");
		sb.append(
				bank.getString("accountnumber") + ": "
						+ numberFormat.format(getKnumber())).append("\n");
		sb.append(
				bank.getString("creditAccount") + ": "
						+ numberFormat.format(getAccountBalance()))
				.append("\n");
		sb.append(bank.getString("currency") + ": "
				+ this.kBalance.getCurrency());
		sb.append("\n--------------------------------------\n");
		sb.append(accountingEntry.toString(currentLocale));

		return sb.toString();
	}

	public String getCreation(Locale currentLocale) {
		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, currentLocale);
		timeFormat = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale);

		return dateFormat.format(getCreationDate().getTime()) + " "
				+ timeFormat.format(getCreationDate().getTime());
	}

	public String getName() {
		return getHolder();
	}

	public int getAccNumber() {
		return getKnumber();
	}

	public Currency getCurrency() {
		return kBalance.getCurrency();
	}

	public String getEntry(Locale currentLocale) {
		return accountingEntry.toString(currentLocale);
	}

	abstract public void printTyp();

	abstract public void payInterest(Locale currentLocale)
			throws MoneyException;

	public String getCredit(Locale currentLocale) {
		numberFormat = NumberFormat.getNumberInstance(currentLocale);
		return numberFormat.format(getAccountBalance());
	}
	
}