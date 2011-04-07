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

		Konto konto1 = new FestgeldKonto(2.34f, 202973324, "Herr Bauer");

		System.out.println("-------------------------");

		konto1.printTyp();
		konto1.manageMoney("Gutschrift", 240.77, '+');
		konto1.manageMoney("Rechnung", 120, '-');
		konto1.manageMoney("Gehalt", 3200.00, '+');
		konto1.verzinsen();
		konto1.verzinsen();
		konto1.verzinsen();
		konto1.verzinsen();
		// konto1.manageMoney("test", 4000, '-');

		System.out.println(konto1.toString());

		System.out.println("-------------------------");

		Konto konto2 = new GiroKonto(5.49f, 202973998, "Frau M�ller");

		konto2.printTyp();
		konto2.manageMoney("Gehalt", 2800, '+');
		konto2.manageMoney("Miete", 780, '-');
		konto2.manageMoney("Urlaub", 3800, '-');
		konto2.verzinsen();
		konto2.manageMoney("Gehalt", 2800, '+');
		System.out.println();
		System.out.println(konto2.toString());

		BankImpl bank = new BankImpl();

		bank.createAcc("FestgeldKonto", 2.34f, "A");
		bank.createAcc("FestgeldKonto", 2.34f, "B");
		bank.createAcc("GiroKonto", 5.49f, "C");
		bank.createAcc("GiroKonto", 5.49f, "D");
		bank.createAcc("GiroKonto", 5.49f, "E");

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

		Money geld = new Money(100, Money.Currency.USDOLLAR);
		geld.add(10000, Money.Currency.JAPANISCHERYEN);
		System.out.print(geld.getValue());
		System.out.print(geld.getCurrency());
		geld.changeCurrency(Money.Currency.SCHWEIZERFRANKEN);
		System.out.print(geld.getValue());
		System.out.print(geld.getCurrency());
	}
}