package bavaria.hightech.banking;

import java.text.DateFormat;
import GenList.GenList;

import bavaria.hightech.banking.Money.Currency;

/**
 * 
 * class to manage the booking
 * 
 */
public class Booking {

	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);

	GenList<Accounting> h = new GenList<Accounting>();

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
		h.insert(new Accounting(reason, amount, sign, currency));
	}

	/**
	 * clear() -empty the booking list
	 */
	public void clear() {

		h = new GenList<Accounting>();
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();

		GenList<Accounting> buffer = h;
		buffer.zero();
		
		while(buffer.next()){
			sb.append(buffer.getElement().getReason() + ": ");
			sb.append(buffer.getElement().getSign());
			sb.append(buffer.getElement().getAmount() + " ");
			sb.append(buffer.getElement().getCurrency() + "\n");
			sb.append("Time: " + df.format(buffer.getElement().getAccountingDate().getTime()) + " ");
			sb.append(tf.format(buffer.getElement().getAccountingDate().getTime()));
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
}