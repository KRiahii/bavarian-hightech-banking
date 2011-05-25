package Comparatoren;

import java.util.Comparator;

import bavaria.hightech.banking.Accounting;

public class AmountComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		Integer n1 = (int) (((Accounting) o1).getAmount());
		Integer n2 = (int) ((Accounting) o2).getAmount();
		int lastCmp = n1.compareTo(n2);
		return (lastCmp != 0 ? lastCmp : n1.compareTo(n2));
	}
}
