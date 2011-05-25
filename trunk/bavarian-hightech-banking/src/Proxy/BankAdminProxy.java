package Proxy;

import java.io.IOException;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import bavaria.hightech.banking.DepositConditions;
import bavaria.hightech.banking.GiroConditions;
import bavaria.hightech.banking.Interface.*;

public class BankAdminProxy implements BankAdmin {

	private BankAdmin bankA;

	private static Logger logger = Logger.getLogger(BankAdminProxy.class
			.getName());

	public BankAdminProxy(BankAdmin bA) throws SecurityException, IOException {
		this.bankA = bA;
		FileHandler handler = new FileHandler("BankAdmin.log");
		logger.addHandler(handler);
	}

	@Override
	public void addCondition(DepositConditions fk) {
		logger.log(Level.FINE, "Neue Depositkondition " + fk);
		bankA.addCondition(fk);
	}

	@Override
	public void addCondition(GiroConditions gk) {
		logger.log(Level.FINE, "Neue Girokondition " + gk);
		bankA.addCondition(gk);
	}

	@Override
	public void changeGiroInterest(float sollzins, float habezins) {
		logger.log(Level.FINE, "Zinsänderung: " + "Sollzins " + sollzins
				+ " Habezins " + habezins);
		bankA.changeGiroInterest(sollzins, habezins);
	}
	
	@Override
	public void changeDepositInterest(float zins) {
		logger.log(Level.FINE, "Zinsänderung: " + "Zins " + zins);
		bankA.changeDepositInterest(zins);
	}

	@Override
	public String accountsCurrent(int kontoNummer, int key) {
		logger.log(Level.FINE, "Zeig das Konto " + kontoNummer + " sotiert nach " + key);
		return bankA.accountsCurrent(kontoNummer, key);
	}

	@Override
	public void showDepositConditions() {
		logger.log(Level.FINE, "Depositkonditionen anzeigen");
		bankA.showDepositConditions();
	}

}