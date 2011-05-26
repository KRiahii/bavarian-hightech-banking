package bavaria.hightech.banking.Interface;

import bavaria.hightech.banking.DepositConditions;
import bavaria.hightech.banking.GiroConditions;

public interface BankAdmin {

	void addCondition(DepositConditions fk);

	void addCondition(GiroConditions gk);

	String accountsCurrent(int kontoNummer, int key);

	void showDepositConditions();

	void changeGiroInterest(float sollzins, float habezins);

	void changeDepositInterest(float zins);
}
