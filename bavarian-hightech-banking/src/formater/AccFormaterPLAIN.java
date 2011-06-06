package formater;

import java.util.Locale;
import java.util.ResourceBundle;

import bavaria.hightech.banking.Account;

public class AccFormaterPLAIN implements AccFormater {

	@Override
	public String addAccData(Account acc, int key, Locale currentLocale) {

		ResourceBundle bank = ResourceBundle.getBundle("i18n/BankBundle",
				currentLocale);

		StringBuilder sb = new StringBuilder();
		sb.append(bank.getString("creationDate") + ": "
				+ acc.getCreation(currentLocale) + System.getProperty("line.separator"));
		
		sb.append(bank.getString("name") + ": " + acc.getName() + System.getProperty("line.separator"));
		sb.append(bank.getString("accountnumber") + ": " + acc.getAccNumber()
				+ System.getProperty("line.separator"));
		sb.append(bank.getString("creditAccount") + ": "
				+ acc.getCredit(currentLocale) + System.getProperty("line.separator"));
		sb.append(bank.getString("currency") + ": " + acc.getCurrency() + System.getProperty("line.separator"));
		sb.append("-------------------------" + System.getProperty("line.separator"));
		sb.append(acc.getEntry(currentLocale));
		return sb.toString();
	}
}
