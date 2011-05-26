package bavaria.hightech.testit;

import java.io.IOException;

import B2B.BankRegistry;
import Proxy.BankAdminProxy;
import Proxy.BankViewProxy;
import bavaria.hightech.banking.*;
import bavaria.hightech.banking.Interface.BankAdmin;
import bavaria.hightech.banking.Interface.BankCustomerView;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.AccException;
import bavaria.hightech.exceptions.TypException;
import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.time.TimeEmitter;

/**
 * 
 * test class
 * 
 */

public class Test {

	public static BankImpl bank;

	public static void main(String[] args) throws MoneyException, TypException,
			SecurityException, IOException, AccException {
		bank = new BankImpl();
		BankCustomerView bV = new BankViewProxy(bank);
		BankAdmin bA = new BankAdminProxy(bank);

		bV.createAcc("DepositAccount", "A", 1);
		bV.createAcc("DepositAccount", "B", 0);
		bV.createAcc("GiroAccount", "C", 0);
		bV.createAcc("GiroAccount", "D", 1);
		bV.createAcc("GiroAccount", "E", 2);

		bV.addMoney(12432, 2000, Money.Currency.BRITISCHESPFUND);
		bV.addMoney(3200, 2001, Money.Currency.EURO);
		bV.addMoney(46345, 2002, Money.Currency.JAPANISCHERYEN);
		bV.addMoney(313254, 2003, Money.Currency.USDOLLAR);
		bV.addMoney(4040, 2004, Money.Currency.BRITISCHESPFUND);

		bV.transferMoney(500, 2000, 2001, Money.Currency.EURO,
				Money.Currency.JAPANISCHERYEN);

		System.out.println();
		System.out.println("-------------------------");
		bV.showMoney(2000);
		bV.showMoney(2001);

		System.out.println("--------------------------");
		System.out.println(bA.accountsCurrent(2001, 1));

		System.out.println("--------------------------");
		bV.addMoney(1, 2000, Money.Currency.JAPANISCHERYEN);
		bV.addMoney(2, 2000, Money.Currency.USDOLLAR);
		bV.addMoney(300, 2000, Money.Currency.USDOLLAR);
		bV.addMoney(4, 2000, Money.Currency.EURO);
		bV.requestMoney(4, 2000, Money.Currency.SCHWEIZERFRANKEN);
		System.out.println(bA.accountsCurrent(2000, 1));

		System.out.println();
		System.out.println("--------------------------");
		bA.showDepositConditions();

		DepositConditions obj = new DepositConditions(7.23f, 99);
		bA.addCondition(obj);

		System.out.println();
		System.out.println("--------------------------");
		bA.showDepositConditions();
		TimeEmitter.getTimeEmitter().elapstime(5);
		bV.requestMoney(4, 2000, Money.Currency.SCHWEIZERFRANKEN);
		System.out.println(bA.accountsCurrent(2002, 1));

		bV.requestMoney(5000, 2004, Money.Currency.EURO);
		TimeEmitter.getTimeEmitter().elapstime(25);
		System.out.println(bA.accountsCurrent(2004, 1));
		//bank.list();
		
		BankImpl bank1 = new BankImpl();
		BankImpl bank2 = new BankImpl();
		
		BankRegistry br = BankRegistry.getInstance();

		br.bind("bank1", bank1);
		br.bind("bank2", bank2);
		
		bank1.createAcc("DepositAccount", "A", 0);
		bank2.createAcc("DepositAccount", "A", 1);
		
		bank1.addMoney(5000, 2000, Currency.EURO);
		bank1.showMoney(2000);
		
		bank2.showMoney(2000);
		
		System.out.println("------------------------------");
		
		bank1.transferMoney(5000, 2000, 2000, Currency.EURO, Currency.EURO, "bank2");
		
		bank1.showMoney(2000);
		bank2.showMoney(2000);
	}
}
