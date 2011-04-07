package bavaria.hightech.testit;

import bavaria.hightech.banking.*;
import bavaria.hightech.exceptions.TypException;
import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * Klasse zum Austesten der der "Bank" Implementierungen
 * 
 */

public class Test {

	public static void main(String[] args) throws MoneyException, TypException {

		BankImpl bank = new BankImpl();

		bank.createAcc("FestgeldKonto", "A", 1);
		bank.createAcc("FestgeldKonto", "B", 0);
		bank.createAcc("GiroKonto", "C", 0);
		bank.createAcc("GiroKonto", "D", 1);
		bank.createAcc("GiroKonto", "E", 2);

		bank.addMoney(12432, 2000);
		bank.addMoney(3200, 2001);
		bank.addMoney(46345, 2002);
		bank.addMoney(313254, 2003);
		bank.addMoney(4040, 2004);

		bank.transferMoney(500, 2000, 2001);

		System.out.println();
		System.out.println("-------------------------");
		bank.showMoney(2000);
		bank.showMoney(2001);

		bank.list();

		System.out.println("--------------------------");
		System.out.println(bank.kontoAuszug(2001));
		
		System.out.println("--------------------------");
		bank.addMoney(1, 2000);
		bank.addMoney(2, 2000);
		bank.addMoney(3, 2000);
		bank.addMoney(4, 2000);
		bank.requestMoney(4, 2000);
		System.out.println(bank.kontoAuszug(2000));
		
		System.out.println();
		System.out.println("--------------------------");
		bank.showFestgeldkonditionen();
		
		FestgeldKonditionen obj = new FestgeldKonditionen(7.23f, 99);
		bank.addKondition(obj);
		
		System.out.println();
		System.out.println("--------------------------");
		bank.showFestgeldkonditionen();


	}
}