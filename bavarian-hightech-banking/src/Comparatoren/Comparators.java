package Comparatoren;

import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import bavaria.hightech.banking.Accounting;

public class Comparators {

	private Map<Integer, Comparator<Accounting>> map;

	public Comparators() {
		Comparator<Accounting> am = new AmountComparator();
		Comparator<Accounting> tm = new TimeComparator();
		Comparator<Accounting> an = new AlphaNumComparator();

		this.map = new HashMap<Integer, Comparator<Accounting>>();

		map.put(1, am);
		map.put(2, tm);
		map.put(3, an);
	}

	public Comparator<Accounting> getComparator(int key) {
		return (Comparator<Accounting>) map.get(key);
	}

	class AmountComparator implements Comparator<Accounting> {
		public int compare(Accounting o1, Accounting o2) {
			Long n1 = o1.getAmount();
			Long n2 = o2.getAmount();
			int lastCmp = n1.compareTo(n2);
			return (lastCmp != 0 ? lastCmp : n1.compareTo(n2));
		}
	}

	class TimeComparator implements Comparator<Accounting> {
		public int compare(Accounting o1, Accounting o2) {
			Calendar n1 = o1.getAccountingDate();
			Calendar n2 = o2.getAccountingDate();
			int lastCmp = n1.compareTo(n2);
			return (lastCmp != 0 ? lastCmp : n1.compareTo(n2));
		}
	}

	class AlphaNumComparator implements Comparator<Accounting> {
		public int compare(Accounting o1, Accounting o2) {
			String n1 = o1.getReason();
			String n2 = o2.getReason();
			int lastCmp = n1.compareTo(n2);
			return (lastCmp != 0 ? lastCmp : n1.compareTo(n2));
		}
	}
}