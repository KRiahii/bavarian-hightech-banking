package bavaria.hightech.banking;

public interface BankAdmin {

	void addCondition(DepositConditions fk);

	void addCondition(GiroConditions gk);

	void changeInterest(float sollzins, float habezins);
	
	String accountsCurrent(int kontoNummer);

	void showDepositConditions();
}
