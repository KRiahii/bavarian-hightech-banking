package Comparatoren;

import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import bavaria.hightech.banking.Accounting;

public class Comparators {

	private Map<Integer, Comparator<?>> map;

	public Comparators() {
		Comparator<?> am = new AmountComparator();
		Comparator<?> tm = new TimeComparator();
		Comparator<?> an = new AlphaNumComparator(); 

		this.map = new HashMap<Integer, Comparator<?>>();

		map.put(1, am);
		map.put(2, tm);
		map.put(3, an);
	}

	public Comparator<?> getComparator(int key) {
		return (Comparator<?>) map.get(key);
	}

	class AmountComparator implements Comparator<Object> {
		public int compare(Object o1, Object o2) {
			Integer n1 = (int) ((Accounting) o1).getAmount();
			Integer n2 = (int) ((Accounting) o2).getAmount();
			int lastCmp = n1.compareTo(n2);
			return (lastCmp != 0 ? lastCmp : n1.compareTo(n2));
		}
	}
	
	class TimeComparator implements Comparator<Object> {
		public int compare(Object o1, Object o2) {
			Calendar n1 = (Calendar) ((Accounting) o1).getAccountingDate();
			Calendar n2 = (Calendar) ((Accounting) o2).getAccountingDate();
			int lastCmp = n1.compareTo(n2);
			return (lastCmp != 0 ? lastCmp : n1.compareTo(n2));
		}
	}
	
	class AlphaNumComparator implements Comparator<Object> {
		public int compare(Object o1, Object o2) {
			String n1 = (String) ((Accounting) o1).getReason();
			String n2 = (String) ((Accounting) o2).getReason();
			int lastCmp = n1.compareTo(n2);
			return (lastCmp != 0 ? lastCmp : n1.compareTo(n2));
		}
	}
}
