package B2B;

import bavaria.hightech.banking.Account;
import bavaria.hightech.banking.Interface.BankCustomerView;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.AccException;

public interface B2B extends BankCustomerView {

	void transferMoney(long amount, int kNumberFROM, int kNumberTO,
			Currency currencyFROM, Currency currencyTO, String name)
			throws AccException;

	boolean existAcc(int kNumber);

	public Account calculateIndex(int kontoNummer);

}