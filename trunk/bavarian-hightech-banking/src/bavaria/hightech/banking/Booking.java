package bavaria.hightech.banking;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Locale;
import java.util.ResourceBundle;

import bavaria.hightech.banking.Money.Currency;
import Comparatoren.Comparators;

/**
 * 
 * class to manage the booking
 * 
 */
public class Booking {

	DateFormat dateFormat;
	DateFormat timeFormat;
	NumberFormat numberFormat;

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
		Collections.sort(list, (Comparator) comp.getComparator(key));
	}

	public String toString(Locale currentLocale) {

		dateFormat = DateFormat.getDateInstance(DateFormat.FULL, currentLocale);
		timeFormat = DateFormat.getTimeInstance(DateFormat.FULL, currentLocale);
		numberFormat = NumberFormat.getNumberInstance(currentLocale);

		ResourceBundle bank = ResourceBundle.getBundle("i18n/BankBundle",
				currentLocale);

		StringBuilder sb = new StringBuilder();

		ListIterator<Accounting> itrBuffer = list.listIterator();

		while (itrBuffer.hasNext()) {
			Accounting obj = (Accounting) itrBuffer.next();
			sb.append(obj.getReason() + ": ");
			sb.append(obj.getSign());
			sb.append(numberFormat.format(obj.getAmount()) + " ");
			sb.append(obj.getCurrency() + "\n");
			sb.append(bank.getString("time") + ": "
					+ dateFormat.format(obj.getAccountingDate().getTime())
					+ " ");
			sb.append(timeFormat.format(obj.getAccountingDate().getTime()));
			sb.append("\n");

		}
		return sb.toString();
	}
}