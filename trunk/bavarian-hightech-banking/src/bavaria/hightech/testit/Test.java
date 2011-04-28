package bavaria.hightech.testit;

import bavaria.hightech.banking.*;
import bavaria.hightech.exceptions.TypException;
import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * test class
 * 
 */

public class Test {

	public static void main(String[] args) throws MoneyException, TypException {

		BankImpl bank = new BankImpl();

		bank.createAcc("DepositAccount", "A", 1);
		bank.createAcc("DepositAccount", "B", 0);
		bank.createAcc("GiroAccount", "C", 0);
		bank.createAcc("GiroAccount", "D", 1);
		bank.createAcc("GiroAccount", "E", 2);

		bank.addMoney(12432, 2000, Money.Currency.BRITISCHESPFUND);
		bank.addMoney(3200, 2001, Money.Currency.EURO);
		bank.addMoney(46345, 2002, Money.Currency.JAPANISCHERYEN);
		bank.addMoney(313254, 2003, Money.Currency.USDOLLAR);
		bank.addMoney(4040, 2004, Money.Currency.BRITISCHESPFUND);

		bank.transferMoney(500, 2000, 2001, Money.Currency.EURO,
				Money.Currency.JAPANISCHERYEN);

		System.out.println();
		System.out.println("-------------------------");
		bank.showMoney(2000);
		bank.showMoney(2001);

		bank.list();

		System.out.println("--------------------------");
		System.out.println(bank.accountsCurrent(2001));

		System.out.println("--------------------------");
		bank.addMoney(1, 2000, Money.Currency.JAPANISCHERYEN);
		bank.addMoney(2, 2000, Money.Currency.USDOLLAR);
		bank.addMoney(300, 2000, Money.Currency.USDOLLAR);
		bank.addMoney(4, 2000, Money.Currency.EURO);
		bank.requestMoney(4, 2000, Money.Currency.SCHWEIZERFRANKEN);
		System.out.println(bank.accountsCurrent(2000));

		System.out.println();
		System.out.println("--------------------------");
		bank.showDepositConditions();

		DepositConditions obj = new DepositConditions(7.23f, 99);
		bank.addCondition(obj);

		System.out.println();
		System.out.println("--------------------------");
		bank.showDepositConditions();
	}
}