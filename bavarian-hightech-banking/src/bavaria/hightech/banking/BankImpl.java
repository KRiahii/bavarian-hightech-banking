package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.exceptions.TypException;

/**
 * 
 * class to manage bank
 * 
 */
public class BankImpl implements BankCustomerView, BankAdmin {

	private Account[] accounts;
	private int count;

	private GiroConditions[] giro;
	private DepositConditions[] deposit;

	public BankImpl() {

		this.accounts = new Account[20];
		this.count = 0;

		defaultConditions();
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
					kHolder, giro[key]));

		else if (typ.equals("DepositAccount"))
			calculateIndex(2000 + count, new DepositAccount(2000 + count++,
					kHolder, deposit[key]));

		else
			throw new TypException("invalid typ");
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}

		try {
			calculateIndex(kNummerTO).manageMoney("deposit", amount, '+',
					currencyTO);
		} catch (MoneyException e) {
			e.printStackTrace();
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

		for (int i = 0; i < accounts.length; i++)
			if (accounts[i] != null)
				accounts[i].payInterest();
	}

	/**
	 * list()
	 */
	public void list() {

		for (int i = 0; i < accounts.length; i++)
			if (accounts[i] != null) {
				System.out.println("---------------------------");
				System.out.println(accounts[i].toString());
			} else
				return;
	}

	/**
	 * accountsCurrent()
	 * 
	 * @param kontoNummer
	 * @return
	 */
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
		return accounts[kontoNummer - 2000];
	}

	/**
	 * calculateIndex()
	 * 
	 * @param kontoNummer
	 * @param obj
	 */
	private void calculateIndex(int kontoNummer, Account obj) {
		accounts[kontoNummer - 2000] = obj;
	}

	/**
	 * defaultConditions() -create default Conditions
	 */
	private void defaultConditions() {

		this.giro = new GiroConditions[3];
		this.deposit = new DepositConditions[3];

		giro[0] = new GiroConditions(100);
		giro[1] = new GiroConditions(500);
		giro[2] = new GiroConditions(1000);

		deposit[0] = new DepositConditions(1.34f, 2);
		deposit[1] = new DepositConditions(2.34f, 6);
		deposit[2] = new DepositConditions(3.34f, 12);
	}

	/**
	 * showDepsitConditions()
	 */
	public void showDepositConditions() {

		for (int i = 0; i < this.deposit.length; i++)
			System.out.println(deposit[i].toString());
	}

	/**
	 * showGiroConditions()
	 */
	public void showGiroConditions() {

		for (int i = 0; i < this.giro.length; i++)
			System.out.println(giro[i].toString());
	}

	/**
	 * addCondition() -add conditions for deposit accounts
	 */
	public void addCondition(DepositConditions fk) {

		DepositConditions[] buffer = new DepositConditions[this.deposit.length];

		for (int i = 0; i < buffer.length; i++)
			buffer[i] = this.deposit[i];

		this.deposit = new DepositConditions[buffer.length + 1];

		for (int i = 0; i < buffer.length; i++)
			this.deposit[i] = buffer[i];

		this.deposit[buffer.length] = fk;
	}

	/**
	 * addCondition() -add condition for giro accounts
	 */
	public void addCondition(GiroConditions gk) {

		GiroConditions[] buffer = new GiroConditions[this.giro.length];

		for (int i = 0; i < buffer.length; i++)
			buffer[i] = this.giro[i];

		this.giro = new GiroConditions[buffer.length + 1];

		for (int i = 0; i < buffer.length; i++)
			this.giro[i] = buffer[i];

		this.giro[buffer.length] = gk;
	}

	/**
	 * changeInterest()
	 */
	@Override
	public void changeInterest(float debitInterest, float creditInterest) {

		for (int i = 0; i < giro.length; i++)
			giro[i].setInterest(debitInterest, creditInterest);
	}
}