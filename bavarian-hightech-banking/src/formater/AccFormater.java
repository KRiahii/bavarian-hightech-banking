package formater;

import java.util.Locale;

import bavaria.hightech.banking.Account;

public interface AccFormater {

	String addAccData(Account acc, int key, Locale currentLocale);
}
