package bavaria.hightech.banking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import B2B.*;
import bavaria.hightech.banking.Interface.BankAdmin;
import bavaria.hightech.banking.Interface.BankCustomerView;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.AccException;
import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.exceptions.TypException;
import bavaria.hightech.time.TimeEmitter;
import bavaria.hightech.time.TimeEmitter.Quarz;

/**
 * 
 * class to manage bank
 * 
 */
public class BankImpl implements BankCustomerView, BankAdmin, B2B {

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
	private int count;
	private java.util.List<GiroConditions> giro;
	private java.util.List<DepositConditions> deposit;
	
	private TimeEmitter timeem;
	private Calendar calendar;
	private Calendar revcalendar;

	private BankRegistry br = BankRegistry.getInstance();

	public BankImpl() throws SecurityException, IOException {

		this.accounts = new Hashtable<HashKey, Account>();
		this.count = 0;
		this.giro = new ArrayList<GiroConditions>(3);
		this.deposit = new ArrayList<DepositConditions>(3);
		this.timeem = TimeEmitter.getTimeEmitter();

		defaultConditions();
	}

	public void elapseTime(int time) {
		calendar = timeem.getCalender();
		revcalendar = timeem.getCalender();
		Quarz quarz = null;
		
		for(int i = 0; i <= time; i++) {
			revcalendar.add(Calendar.DATE, 1);
			quarz = timeem.new Quarz();
			quarz.startTimeing();
			while (calendar.before(revcalendar)) {
				calendar = timeem.getCalender();
			}
			try {
				chargeInterest();
			} catch (MoneyException e) {
				e.printStackTrace();
			}
		}
		quarz.getClock().showMe();
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
					kHolder, giro.get(key)));

		else if (typ.equals("DepositAccount"))
			calculateIndex(2000 + count, new DepositAccount(2000 + count++,
					kHolder, (DepositConditions) deposit.get(key)));

		else {
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
		}

		try {
			calculateIndex(kNummerTO).manageMoney("deposit", amount, '+',
					currencyTO);
		} catch (MoneyException e) {
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
		Iterator<HashKey> it = accounts.keySet().iterator();
		while (it.hasNext()) {
			HashKey key = it.next();
			System.out.println("---------------------------");
			System.out.println(accounts.get(key).toString(2));
		}
	}

	/**
	 * accountsCurrent()
	 * 
	 * @param kontoNummer
	 * @return
	 */
	@Override
	public String accountsCurrent(int kontoNummer, int key) {
		return calculateIndex(kontoNummer).toString(key);
	}

	/**
	 * calculateIndex()
	 * 
	 * @param kontoNummer
	 * @return
	 */
	public Account calculateIndex(int kontoNummer) {
		String s = "" + (kontoNummer - 2000);

		return (accounts.containsKey(new HashKey(s))) ? accounts
				.get(new HashKey(s)) : null;

	}

	/**
	 * calculateIndex()
	 * 
	 * @param kontoNummer
	 * @param obj
	 */
	private void calculateIndex(int kontoNummer, Account obj) {
		String s = "" + (kontoNummer - 2000);
		accounts.put(new HashKey(s), obj);
	}

	/**
	 * defaultConditions() -create default Conditions
	 */
	private void defaultConditions() {
		giro.add(0, new GiroConditions(100));
		giro.add(1, new GiroConditions(500));
		giro.add(2, new GiroConditions(1000));

		deposit.add(0, new DepositConditions(1.34f, 2));
		deposit.add(1, new DepositConditions(2.34f, 2));
		deposit.add(2, new DepositConditions(3.34f, 2));
	}

	/**
	 * showDepsitConditions()
	 */
	@Override
	public void showDepositConditions() {
		for (int i = 0; i < this.deposit.size(); i++)
			System.out.println(deposit.get(i).toString());
	}

	/**
	 * showGiroConditions()
	 */
	public void showGiroConditions() {
		for (int i = 0; i < this.giro.size(); i++)
			System.out.println(giro.get(i).toString());
	}

	/**
	 * addCondition() -add conditions for deposit accounts
	 */
	public void addCondition(DepositConditions fk) {
		int i = deposit.size() - 1;
		deposit.add(i, fk);
	}

	/**
	 * addCondition() -add condition for giro accounts
	 */
	public void addCondition(GiroConditions gk) {
		int i = giro.size() - 1;
		giro.add(i, gk);
	}

	/**
	 * changeInterest()
	 */
	@Override
	public void changeGiroInterest(float debitInterest, float creditInterest) {

		for (int i = 0; i < giro.size(); i++)
			((GiroConditions) giro.get(i)).setInterest(debitInterest,
					creditInterest);
	}

	/**
	 * changeInterest()
	 */
	@Override
	public void changeDepositInterest(float Interest) {

		for (int i = 0; i < deposit.size(); i++)
			((DepositConditions) deposit.get(i)).setInterest(Interest);
	}

	@Override
	public boolean existAcc(int kNumber) {
		return calculateIndex(kNumber) != null ? this.accounts
				.contains(calculateIndex(kNumber)) : false;
	}

	@Override
	public void transferMoney(long amount, int kNumberFROM, int kNumberTO,
			Currency currencyFROM, Currency currencyTO, String name)
			throws AccException {

		if (br.lookup(name).existAcc(kNumberTO)) {
			this.requestMoney(amount, kNumberFROM, currencyFROM);
			br.lookup(name).addMoney(amount, kNumberTO, currencyTO);
		} else
			throw new AccException("Invalid Account");
	}
}