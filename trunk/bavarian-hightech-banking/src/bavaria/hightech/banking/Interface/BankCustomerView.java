package bavaria.hightech.banking.Interface;

import java.io.IOException;
import java.io.OutputStream;

import bavaria.hightech.banking.Money.Currency;

public interface BankCustomerView {

	void createAcc(String typ, String kHolder, int key);

	void showMoney(int kNumber);

	void addMoney(long amount, int kNumber, Currency currency);

	void requestMoney(long amount, int kNumber, Currency currency);

	void transferMoney(long amount, int kNumberFrom, int kNumberTo,
			Currency currencyFrom, Currency currencyTo);

	void getStatement(int kontoNummer, int key, OutputStream os,
			String mimeTyp) throws IOException;
}