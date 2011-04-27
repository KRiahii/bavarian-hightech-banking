package bavaria.hightech.banking;

import java.util.Calendar;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.time.Zeitgeber;

public class Buchung {

	private String reason;
	private String amount;
	private char sign;
	private Buchung next;
	private Currency currency;
	private Zeitgeber buchungdate;

	Buchung(String reason, String amount, char sign, Buchung next,
			Currency currency) {
		this.reason = reason;
		this.amount = amount;
		this.next = next;
		this.sign = sign;
		this.currency = currency;
		buchungdate = Zeitgeber.getZeitgeber();
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
	
	@SuppressWarnings("static-access")
	public Calendar getBuchungdate() {
		return buchungdate.getCalender();
	}

}
