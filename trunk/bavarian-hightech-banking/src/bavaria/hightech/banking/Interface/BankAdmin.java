package bavaria.hightech.banking.Interface;

import java.util.Comparator;

import Comparatoren.AmountComparator;
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
