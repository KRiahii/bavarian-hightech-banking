package bavaria.hightech.banking;

import java.util.Calendar;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.time.TimeEmitter;

public class Accounting {

	private String reason;
	private String amount;
	private char sign;
	private Currency currency;
	private TimeEmitter accountingDate;

	Accounting(String reason, String amount, char sign,
			Currency currency) {
		this.reason = reason;
		this.amount = amount;
		this.sign = sign;
		this.currency = currency;
		accountingDate = TimeEmitter.getTimeEmitter();
	}

	public String getReason() {
		return this.reason;
	}

	public String getAmount() {
		return this.amount;
	}

	public char getSign() {
		return this.sign;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	@SuppressWarnings("static-access")
	public Calendar getAccountingDate() {
		return accountingDate.getCalender();
	}

}
