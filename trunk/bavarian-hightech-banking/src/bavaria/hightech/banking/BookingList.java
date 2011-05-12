package bavaria.hightech.banking;

import java.text.DateFormat;

import bavaria.hightech.banking.Money.Currency;

/**
 * 
 * class to manage the booking
 * 
 */
public class BookingList {

	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

	LinkElement<Accounting> h = new LinkElement<Accounting>(null, null);

	/**
	 * insert()
	 * 
	 * @param reason
	 * @param amount
	 * @param sign
	 * @param currency
	 */

	public void insert(String reason, String amount, char sign,
			Currency currency) {
		h.next = new LinkElement<Accounting>(new Accounting(reason, amount,
				sign, currency), h.next);
	}

	/**
	 * clear() -empty the booking list
	 */
	public void clear() {

		this.h = new LinkElement<Accounting>(null, null);
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();

		LinkElement<Accounting> le = h.next;
		while (le.next != null) {
			Accounting acc = le.element;
			sb.append("> " + acc.getReason() + ": " + acc.getSign()
					+ acc.getAmount() + " " + acc.getCurrency() + " "
					+ df.format(acc.getAccountingDate().getTime()) + " "
					+ tf.format(acc.getAccountingDate().getTime()));
			sb.append('\n');

			le = le.next;
		}

		return sb.toString();
	}

	class LinkElement<E> {
		E element;
		LinkElement<E> next;

		LinkElement(E element, LinkElement<E> next) {
			this.element = element;
			this.next = next;
		}
	}
}