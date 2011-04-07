package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;

public class Buchung {

	private String reason;
	private String amount;
	private char sign;
	private Buchung next;
	private Currency currency;

	Buchung(String reason, String amount, char sign, Buchung next,
			Currency currency) {
		this.reason = reason;
		this.amount = amount;
		this.next = next;
		this.sign = sign;
		this.currency = currency;
	}

	public void creatNext(String reason, String amount, char sign,
			Buchung next, Currency currency) {

		this.next = new Buchung(reason, amount, sign, next, currency);
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

	public Buchung getNext() {
		return this.next;
	}

	public Currency getCurrency() {
		return this.currency;
	}

}
