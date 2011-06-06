package formater;

import java.util.Locale;
import java.util.ResourceBundle;

import bavaria.hightech.banking.Account;

public class AccFormaterHTML implements AccFormater {

	@Override
	public String addAccData(Account acc, int key, Locale currentLocale) {

		ResourceBundle bank = ResourceBundle.getBundle("i18n/BankBundle",
				currentLocale);

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(bank.getString("creationDate") + ": "
				+ acc.getCreation(currentLocale) + "<br>");
		sb.append(bank.getString("name") + ": " + acc.getName() + "<br>");
		sb.append(bank.getString("accountnumber") + ": " + acc.getAccNumber()
				+ "<br>");
		sb.append(bank.getString("creditAccount") + ": "
				+ acc.getCredit(currentLocale) + "<br>");
		sb.append(bank.getString("currency") + ": " + acc.getCurrency() + "<br>");
		sb.append("-------------------------" + "<br>");
		sb.append(acc.getEntry(currentLocale));
		sb.append("</html>");

		return sb.toString();
	}

}
