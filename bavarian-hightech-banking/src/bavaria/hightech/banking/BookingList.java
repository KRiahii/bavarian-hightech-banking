package bavaria.hightech.banking;

import java.text.DateFormat;

import bavaria.hightech.banking.Money.Currency;

public class BookingList {

	private Accounting h;
	private Accounting pos;
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

	public BookingList() {

		this.h = new Accounting(null, null, ' ', null, null);
		this.pos = h;
	}

	public void add(String reason, String amount, char sign, Currency currency) {

		Accounting obj = h;

		while (obj.getNext() != null)
			obj = obj.getNext();

		obj.creatNext(reason, amount, sign, null, currency);
	}

	public void clear() {
		this.h = new Accounting(null, null, ' ', null, null);
	}

	Accounting next() {

		if (pos.getNext() != null)
			this.pos = pos.getNext();

		else
			return null;

		return pos;
	}

	public void resetIteration() {
		this.pos = this.h;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Accounting lnk = h.getNext();
		while (lnk != null) {
			sb.append("> " + lnk.getReason() + ": " + lnk.getSign()
					+ lnk.getAmount() + " " + lnk.getCurrency() + " "
					+ df.format(h.getAccountingDate().getTime()) + " "
					+ tf.format(h.getAccountingDate().getTime()));
			sb.append('\n');
			lnk = lnk.getNext();
		}
		return sb.toString();
	}
}