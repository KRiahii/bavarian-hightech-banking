package bavaria.hightech.banking;

import java.util.Calendar;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.time.TimeEmitter;

public class Accounting {

	private String reason;
	private long amount;
	private char sign;
	private Currency currency;
	private Calendar accountingDate;

	Accounting(String reason, long string, char sign, Currency currency) {
		this.reason = reason;
		this.amount = string;
		this.sign = sign;
		this.currency = currency;
		accountingDate = TimeEmitter.getTimeEmitter().getCalender();
	}

	/**
	 * getReason()
	 * 
	 * @return
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * getAmount()
	 * 
	 * @return
	 */
	public long getAmount() {
		return this.amount;
	}

	/**
	 * getSign()
	 * 
	 * @return
	 */
	public char getSign() {
		return this.sign;
	}

	/**
	 * getCurrency()
	 * 
	 * @return
	 */
	public Currency getCurrency() {
		return this.currency;
	}

	/**
	 * getAccountingDate()
	 * 
	 * @return
	 */
	public Calendar getAccountingDate() {
		return accountingDate;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Accounting))
			return false;
		Accounting n = (Accounting) o;
		return n.getReason().equals(getReason())
				&& n.getAmount() == getAmount() && n.getSign() == getSign()
				&& n.getCurrency().equals(getCurrency())
				&& n.getAccountingDate().equals(getAccountingDate());
	}

}