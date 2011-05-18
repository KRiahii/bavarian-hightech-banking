package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;

public interface BankCustomerView {

	void createAcc(String typ, String kHolder, int key);

	void showMoney(int kNumber);

	void addMoney(long amount, int kNumber, Currency currency);

	void requestMoney(long amount, int kNumber, Currency currency);

	void transferMoney(long amount, int kNumberFrom, int kNumberTo,
			Currency currencyFrom, Currency currencyTo);
}