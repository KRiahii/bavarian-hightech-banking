
package JUnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import bavaria.hightech.banking.Money;

/**
 * 
 * JUnit MoneyTest class
 *
 */
public class MoneyTest {

	private Money money1;

	/**
	 * Construct the attributes before every test.
	 */
	@Before
	public void setUp() {
		money1 = new Money(30, Money.Currency.EURO);
	}

	/**
	 * what to do after every test?
	 */
	@After
	public void tearDown() {
	}

	/**
	 * 30+20=50
	 */
	@Test
	public void testAdd() {
		assertEquals(50, money1.addMoney(20, Money.Currency.EURO));
	}

	/**
	 * 30-20=10
	 */
	@Test
	public void testNegativeMoney() {
		assertEquals(10, money1.subMoney(20, Money.Currency.EURO));
	}
	
	/**
	 * expected 40
	 */
	@Test
	public void testSetValue(){
		assertEquals(40,money1.setValue(40));
	}
	
	/**
	 * expected 10
	 */
	@Test
	public void testGetValue(){
		money1.setValue(10);
		assertEquals(10,money1.getValue());
	}
	
	/**
	 * expected JAPANISCHERYEN
	 */
	@Test
	public void testSetCurrency(){
		money1.setCurrency(Money.Currency.JAPANISCHERYEN);
		assertEquals(Money.Currency.JAPANISCHERYEN, money1.getCurrency());
	}
	
	/**
	 * expected true
	 */
	@Test
	public void testIsBigger(){
		assertTrue(money1.isBigger(10));
	}
	
	/**
	 * expected true
	 */
	@Test
	public void testIsSmaler(){
		assertTrue(money1.isSmaler(100));
	}

	/**
	 * new factor expected
	 */
	@Test
	public void testGetFactor(){
		Money.Currency.BRITISCHESPFUND.setFactor(1.48);
		assertTrue(1.48 == Money.Currency.BRITISCHESPFUND.getFactor());
	}
	
	/**
	 * change Currency successful?
	 */
	@Test
	public void testChangeCurrency(){
		
		Money money2 = new Money(money1.getValue(), money1.getCurrency());
		money1.changeCurrency(Money.Currency.USDOLLAR);
		
		long value = (long) (money2.getValue() * Money.Currency.EURO.getFactor() / Money.Currency.USDOLLAR.getFactor());
		
		assertTrue(money1.getValue() == value);
	}
}
