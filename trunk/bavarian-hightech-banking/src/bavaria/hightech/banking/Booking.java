package bavaria.hightech.banking;

import java.text.DateFormat;
import GenList.*;

import bavaria.hightech.banking.Money.Currency;

/**
 * 
 * class to manage the booking
 * 
 */
public class Booking {

	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

	GenList<Accounting> list = new GenList<Accounting>();
	LinkedListIterator<Accounting> itr = list.inception();

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
		list.insert(new Accounting(reason, amount, sign, currency), this.itr);
	}

	/**
	 * clear() -empty the booking list
	 */
	public void clear() {

		list = new GenList<Accounting>();
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		LinkedListIterator<Accounting> itrBuffer = list.first();

		for (; itrBuffer.isValid(); itrBuffer.advance()) {
			sb.append(((Accounting) itrBuffer.retrieve()).getReason() + ": ");
			sb.append(((Accounting) itrBuffer.retrieve()).getSign());
			sb.append(((Accounting) itrBuffer.retrieve()).getAmount() + " ");
			sb.append(((Accounting) itrBuffer.retrieve()).getCurrency() + "\n");
			sb.append("Time: "
					+ df.format(((Accounting) itrBuffer.retrieve())
							.getAccountingDate().getTime()) + " ");
			sb.append(tf.format(((Accounting) itrBuffer.retrieve())
					.getAccountingDate().getTime()));

			sb.append("\n");
		}

		return sb.toString();
	}
}