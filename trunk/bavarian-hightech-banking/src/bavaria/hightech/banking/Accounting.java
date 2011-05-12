package bavaria.hightech.banking;

import java.util.Calendar;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.time.TimeEmitter;

public class Accounting {

	private String reason;
	private String amount;
	private char sign;
	private Currency currency;
	private Calendar accountingDate;

	Accounting(String reason, String amount, char sign,
			Currency currency) {
		this.reason = reason;
		this.amount = amount;
		this.sign = sign;
		this.currency = currency;
		accountingDate = TimeEmitter.getTimeEmitter().getCalender();
	}

	/**
	 * getReason()
	 * @return
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * getAmount()
	 * @return
	 */
	public String getAmount() {
		return this.amount;
	}

	/**
	 * getSign()
	 * @return
	 */
	public char getSign() {
		return this.sign;
	}

	/**
	 * getCurrency()
	 * @return
	 */
	public Currency getCurrency() {
		return this.currency;
	}

	/**
	 * getAccountingDate()
	 * @return
	 */
	public Calendar getAccountingDate() {
		return accountingDate;
	}

}
