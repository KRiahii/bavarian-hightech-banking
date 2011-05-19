package bavaria.hightech.banking.Interface;

import bavaria.hightech.banking.DepositConditions;
import bavaria.hightech.banking.GiroConditions;

public interface BankAdmin {

	void addCondition(DepositConditions fk);

	void addCondition(GiroConditions gk);

	void changeInterest(float sollzins, float habezins);

	String accountsCurrent(int kontoNummer);

	void showDepositConditions();
}
