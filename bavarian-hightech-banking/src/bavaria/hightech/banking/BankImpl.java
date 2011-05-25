package bavaria.hightech.banking;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import bavaria.hightech.banking.Interface.BankAdmin;
import bavaria.hightech.banking.Interface.BankCustomerView;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.exceptions.TypException;

/**
 * 
 * class to manage bank
 * 
 */
public class BankImpl implements BankCustomerView, BankAdmin {

	class HashKey {
		private String str;

		public HashKey(String str) {
			this.str = str;
		}

		public String toString() {
			return str;
		}

		public int hashCode() {
			return str.hashCode();
		}

		public boolean equals(Object o) {
			if (o instanceof HashKey)
				return (str.equals(((HashKey) o).str));
			return false;
		}
	}

	private Hashtable<HashKey, Account> accounts;
	// private Account[] accounts;
	private int count;

	// private GiroConditions[] giro;
	private Vector<GiroConditions> giro;

	// private DepositConditions[] deposit;
	private Vector<DepositConditions> deposit;

	private static Logger logger = Logger.getLogger(BankImpl.class.getName());

	public BankImpl() throws SecurityException, IOException {

		// this.accounts = new Account[20];
		this.accounts = new Hashtable<HashKey, Account>();
		this.count = 0;

		defaultConditions();

		FileHandler handler = new FileHandler("BankImpl.log");
		logger.addHandler(handler);
	}

	/**
	 * createAcc()
	 * 
	 * @param
	 */
	@Override
	public void createAcc(String typ, String kHolder, int key) {

		if (typ.equals("GiroAccount"))
			calculateIndex(2000 + count, new GiroAccount(2000 + count++,
					kHolder, (GiroConditions) giro.elementAt(key)));

		else if (typ.equals("DepositAccount"))
			calculateIndex(2000 + count, new DepositAccount(2000 + count++,
					kHolder, (DepositConditions) deposit.elementAt(key)));

		else {
			logger.log(Level.WARNING, "TypException invalid typ " + typ);
			throw new TypException("invalid typ");
		}
	}

	/**
	 * addMoney()
	 * 
	 * @param
	 */
	@Override
	public void addMoney(long amount, int kNumber, Currency currency) {

		try {
			calculateIndex(kNumber).manageMoney("deposited", amount, '+',
					currency);
		} catch (MoneyException e) {
			logger.log(Level.WARNING, "invalid amount " + amount, e);
		}
	}

	/**
	 * requestMoney()
	 * 
	 * @param
	 */
	@Override
	public void requestMoney(long amount, int kNumber, Currency currency) {

		try {
			calculateIndex(kNumber).manageMoney("detached", amount, '-',
					currency);
		} catch (MoneyException e) {
			logger.log(Level.WARNING, "invalid amount " + amount, e);
		}
	}

	/**
	 * transferMoney()
	 * 
	 * @param
	 */
	@Override
	public void transferMoney(long amount, int kNummerFROM, int kNummerTO,
			Currency currencyFROM, Currency currencyTO) {

		try {
			calculateIndex(kNummerFROM).manageMoney("bank transfer", amount,
					'-', currencyFROM);
		} catch (MoneyException e) {
			logger.log(Level.WARNING, "invalid amount " + amount, e);
		}

		try {
			calculateIndex(kNummerTO).manageMoney("deposit", amount, '+',
					currencyTO);
		} catch (MoneyException e) {
			logger.log(Level.WARNING, "invalid amount " + amount, e);
		}
	}

	/**
	 * showMoney()
	 * 
	 * @param
	 */
	@Override
	public void showMoney(int kNumber) {

		System.out.println(">> " + calculateIndex(kNumber).getAccountBalance()
				+ " Account: " + calculateIndex(kNumber).getKnumber());
	}

	/**
	 * chargeInterest()
	 * 
	 * @throws MoneyException
	 */
	public void chargeInterest() throws MoneyException {

		// for (int i = 0; i < accounts.length; i++)
		// if (accounts[i] != null)
		// accounts[i].payInterest();

		Iterator<HashKey> it = accounts.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			((Account) accounts.get(key)).payInterest();
		}
	}

	/**
	 * list()
	 */
	public void list() {

		// for (int i = 0; i < accounts.length; i++)
		// if (accounts[i] != null) {
		// System.out.println("---------------------------");
		// System.out.println(accounts[i].toString());
		// } else
		// return;

		Iterator<HashKey> it = accounts.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();
			System.out.println("---------------------------");
			System.out.println(accounts.get(key).toString());
		}
	}

	/**
	 * accountsCurrent()
	 * 
	 * @param kontoNummer
	 * @return
	 */
	@Override
	public String accountsCurrent(int kontoNummer) {
		return calculateIndex(kontoNummer).toString();
	}

	/**
	 * calculateIndex()
	 * 
	 * @param kontoNummer
	 * @return
	 */
	private Account calculateIndex(int kontoNummer) {
		// return accounts[kontoNummer - 2000];
		String s = "" + (kontoNummer - 2000);
		return accounts.get(new HashKey(s));
	}

	/**
	 * calculateIndex()
	 * 
	 * @param kontoNummer
	 * @param obj
	 */
	private void calculateIndex(int kontoNummer, Account obj) {
		// accounts[kontoNummer - 2000] = obj;
		String s = "" + (kontoNummer - 2000);
		accounts.put(new HashKey(s), obj);
	}

	/**
	 * defaultConditions() -create default Conditions
	 */
	private void defaultConditions() {

		// this.giro = new GiroConditions[3];
		this.giro = new Vector<GiroConditions>(3, 1);
		giro.setSize(3);

		// this.deposit = new DepositConditions[3];
		this.deposit = new Vector<DepositConditions>(3, 1);
		deposit.setSize(3);

		// giro[0] = new GiroConditions(100);
		giro.setElementAt(new GiroConditions(100), 0);

		// giro[1] = new GiroConditions(500);
		giro.setElementAt(new GiroConditions(500), 1);

		// giro[2] = new GiroConditions(1000);
		giro.setElementAt(new GiroConditions(1000), 2);

		// deposit[0] = new DepositConditions(1.34f, 2);
		deposit.setElementAt(new DepositConditions(1.34f, 2), 0);

		// deposit[1] = new DepositConditions(2.34f, 6);
		deposit.setElementAt(new DepositConditions(2.34f, 2), 1);

		// deposit[2] = new DepositConditions(3.34f, 12);
		deposit.setElementAt(new DepositConditions(3.34f, 2), 2);

	}

	/**
	 * showDepsitConditions()
	 */
	@Override
	public void showDepositConditions() {

		for (int i = 0; i < this.deposit.size(); i++)
			System.out.println(deposit.elementAt(i).toString());
	}

	/**
	 * showGiroConditions()
	 */
	public void showGiroConditions() {

		for (int i = 0; i < this.giro.size(); i++)
			System.out.println(giro.elementAt(i).toString());
	}

	/**
	 * addCondition() -add conditions for deposit accounts
	 */
	public void addCondition(DepositConditions fk) {

		// DepositConditions[] buffer = new
		// DepositConditions[this.deposit.length];

		// for (int i = 0; i < buffer.length; i++)
		// buffer[i] = this.deposit[i];

		// this.deposit = new DepositConditions[buffer.length + 1];

		// for (int i = 0; i < buffer.length; i++)
		// this.deposit[i] = buffer[i];

		// this.deposit[buffer.length] = fk;

		int i = deposit.size() - 1;
		deposit.setElementAt(fk, i);

	}

	/**
	 * addCondition() -add condition for giro accounts
	 */
	public void addCondition(GiroConditions gk) {

		// GiroConditions[] buffer = new GiroConditions[this.giro.length];

		// for (int i = 0; i < buffer.length; i++)
		// buffer[i] = this.giro[i];

		// this.giro = new GiroConditions[buffer.length + 1];

		// for (int i = 0; i < buffer.length; i++)
		// this.giro[i] = buffer[i];

		// this.giro[buffer.length] = gk;

		int i = giro.size() - 1;
		giro.setElementAt(gk, i);
	}

	/**
	 * changeInterest()
	 */
	@Override
	public void changeGiroInterest(float debitInterest, float creditInterest) {

		for (int i = 0; i < giro.size(); i++)
			((GiroConditions) giro.elementAt(i)).setInterest(debitInterest,
					creditInterest);
	}

	/**
	 * changeInterest()
	 */
	@Override
	public void changeDepositInterest(float Interest) {

		for (int i = 0; i < deposit.size(); i++)
			((DepositConditions) deposit.elementAt(i)).setInterest(Interest);
	}

}