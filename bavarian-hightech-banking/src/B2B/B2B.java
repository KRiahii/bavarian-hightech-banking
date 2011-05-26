package B2B;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.AccException;

public interface B2B {

	void transferMoney(long amount, int kNumberFROM, int kNumberTO,
			Currency currencyFROM, Currency currencyTO, String name) throws AccException;

	boolean existAcc(int kNumber);
	
	void addMoney(long amount, int kNumber, Currency currency);
	
	void requestMoney(long amount, int kNumber, Currency currency);
}
