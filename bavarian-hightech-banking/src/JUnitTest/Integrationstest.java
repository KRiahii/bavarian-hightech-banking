package JUnitTest;

import java.io.IOException;

import org.junit.Before;

import org.junit.Test;

import B2B.BankRegistry;
import bavaria.hightech.banking.BankImpl;
import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.AccException;

import static org.junit.Assert.*;

public class Integrationstest {
	
	BankImpl bank1;
	BankImpl bank2;
	
	BankRegistry br;

	@Before
	public void setUp() throws SecurityException, IOException {
		bank1 = new BankImpl();
		bank2 = new BankImpl();
		
		br = BankRegistry.getInstance();
		br.bind("bank1", bank1);
		br.bind("bank2", bank2);
	}
	
	/**
	 * acc exist?
	 */
	@Test
	public void testAccExist(){

		bank1.createAcc("DepositAccount", "A", 0);
		bank2.createAcc("DepositAccount", "A", 1);
		
		
		assertTrue(bank1.existAcc(2000));
		assertTrue(bank2.existAcc(2000));
	}
	
	/**
	 * test transfer Money
	 * @throws AccException 
	 */
	@Test
	public void testTransferMoney() throws AccException{
		
		bank1.createAcc("DepositAccount", "A", 0);
		bank2.createAcc("DepositAccount", "A", 1);
		
		bank1.addMoney(5000, 2000, Currency.EURO);
		bank1.showMoney(2000);
		
		assertEquals(bank1.calculateIndex(2000).getAccountBalance(), 5000);
		assertEquals(bank2.calculateIndex(2000).getAccountBalance(), 0);
		
		bank2.showMoney(2000);
		
		System.out.println("------------------------------");
		
		bank1.transferMoney(5000, 2000, 2000, Currency.EURO, Currency.EURO, "bank2");
		
		bank1.showMoney(2000);
		bank2.showMoney(2000);
		
		assertEquals(bank1.calculateIndex(2000).getAccountBalance(), 0);
		assertEquals(bank2.calculateIndex(2000).getAccountBalance(), 5000);
		
		
		
	}

}
