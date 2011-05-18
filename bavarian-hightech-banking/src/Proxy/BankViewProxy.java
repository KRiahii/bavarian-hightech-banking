package Proxy;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import bavaria.hightech.banking.BankCustomerView;
import bavaria.hightech.banking.Money.Currency;

public class BankViewProxy implements BankCustomerView {

	private BankCustomerView bankV;
	private static Logger logger = Logger.getLogger(BankViewProxy.class
			.getName());

	public BankViewProxy(BankCustomerView bV) throws SecurityException,
			IOException {
		this.bankV = bV;
		FileHandler handler = new FileHandler("BankView.log");
		logger.addHandler(handler);
	}

	@Override
	public void createAcc(String typ, String kHolder, int key) {
		logger.log(Level.FINE, "Neues Konot " + typ + " Name " + kHolder
				+ " Nummer " + key);
		bankV.createAcc(typ, kHolder, key);
	}

	@Override
	public void showMoney(int kNumber) {
		logger.log(Level.FINE, "Zeige Geld von Konto" + kNumber);
		bankV.showMoney(kNumber);
	}

	@Override
	public void addMoney(long amount, int kNumber, Currency currency) {
		if (amount >= 1000000) {
			logger.log(Level.INFO, "Einzahlen auf Konto" + kNumber + " "
					+ amount + " " + currency);
			bankV.addMoney(amount, kNumber, currency);
		} else {
			logger.log(Level.FINE, "Einzahlen auf Konto" + kNumber + " "
					+ amount + " " + currency);
			bankV.addMoney(amount, kNumber, currency);
		}
	}

	@Override
	public void requestMoney(long amount, int kNumber, Currency currency) {
		logger.log(Level.FINE, "Abheben von Konto" + kNumber + " " + amount
				+ " " + currency);
		bankV.requestMoney(amount, kNumber, currency);
	}

	@Override
	public void transferMoney(long amount, int kNumberFrom, int kNumberTo,
			Currency currencyFrom, Currency currencyTo) {
		logger.log(Level.FINE, "Überweisen von Konto" + kNumberFrom + " "
				+ currencyFrom + " nach " + kNumberTo + " " + currencyTo + " "
				+ amount);
		bankV.transferMoney(amount, kNumberFrom, kNumberTo, currencyFrom,
				currencyTo);
	}
}