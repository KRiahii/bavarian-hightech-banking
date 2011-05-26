package bavaria.hightech.banking;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

import bavaria.hightech.banking.Money.Currency;
import Comparatoren.Comparators;

/**
 * 
 * class to manage the booking
 * 
 */
public class Booking {

	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

	ArrayList<Accounting> list = new ArrayList<Accounting>();
	ListIterator<Accounting> itr = list.listIterator();

	/**
	 * insert()
	 * 
	 * @param reason
	 * @param string
	 * @param sign
	 * @param currency
	 */

	public void insert(String reason, long string, char sign, Currency currency) {
		list.add(this.itr.nextIndex(), new Accounting(reason, string, sign,
				currency));
	}

	/**
	 * clear() -empty the booking list
	 */
	public void clear() {
		list = new ArrayList<Accounting>();
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sort(int key) {
		Comparators comp = new Comparators();
		Collections.sort(list, (Comparator)comp.getComparator(key));
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		ListIterator<Accounting> itrBuffer = list.listIterator();

		while (itrBuffer.hasNext()) {
			Accounting obj = (Accounting) itrBuffer.next();
			sb.append(obj.getReason() + ": ");
			sb.append(obj.getSign());
			sb.append(obj.getAmount() + " ");
			sb.append(obj.getCurrency() + "\n");
			sb.append("Time: " + df.format(obj.getAccountingDate().getTime())
					+ " ");
			sb.append(tf.format(obj.getAccountingDate().getTime()));
			sb.append("\n");

		}

		// for (; itrBuffer.isValid(); itrBuffer.advance()) {
		// sb.append(((Accounting) itrBuffer.retrieve()).getReason() + ": ");
		// sb.append(((Accounting) itrBuffer.retrieve()).getSign());
		// sb.append(((Accounting) itrBuffer.retrieve()).getAmount() + " ");
		// sb.append(((Accounting) itrBuffer.retrieve()).getCurrency() + "\n");
		// sb.append("Time: "
		// + df.format(((Accounting) itrBuffer.retrieve())
		// .getAccountingDate().getTime()) + " ");
		// sb.append(tf.format(((Accounting) itrBuffer.retrieve())
		// .getAccountingDate().getTime()));
		//
		// sb.append("\n");
		// }

		return sb.toString();
	}
}