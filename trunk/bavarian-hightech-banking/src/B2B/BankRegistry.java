package B2B;

import java.util.*;

public class BankRegistry {

	private static BankRegistry instance = null;

	private BankRegistry() {
	}

	public static BankRegistry getInstance() {
		if (instance == null) {
			instance = new BankRegistry();
		}
		return instance;
	}

	Hashtable<String, B2B> map = new Hashtable<String, B2B>();

	public void bind(String name, B2B bank) {
		map.put(name, bank);
	}

	public B2B lookup(String name) {
		return map.contains(map.get(name)) ? map.get(name) : null;
	}

	public Hashtable<String, B2B> getHashtable() {
		return map;
	}

}