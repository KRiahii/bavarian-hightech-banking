package JUnitTest;

import java.io.IOException;

import org.junit.Before;

import org.junit.Test;

import bavaria.hightech.banking.BankImpl;

import static org.junit.Assert.*;

import B2B.B2B;
import B2B.BankRegistry;

public class BankRegistryTest {

	BankRegistry br1;

	@Before
	public void setUp() {

		br1 = BankRegistry.getInstance();
	}

	/**
	 * only one instance?
	 */
	@Test
	public void testIsSame() {
		
		BankRegistry br2 = BankRegistry.getInstance();
		
		assertEquals(br1, br2);
	}
	
	/**
	 * binds the bank name
	 * @throws SecurityException
	 * @throws IOException
	 */
	@Test
	public void testBind() throws SecurityException, IOException{
		BankImpl test = new BankImpl();

		br1.bind("1st", test);
		br1.bind("2nd", new BankImpl());
		br1.bind("3rd", new BankImpl());
		br1.bind("4th", new BankImpl());
		
		assertTrue(br1.getHashtable().containsKey("1st"));
		assertTrue(br1.getHashtable().contains(test));
		
		assertTrue(br1.getHashtable().containsKey("2nd"));
		assertTrue(br1.getHashtable().containsKey("3rd"));
		assertTrue(br1.getHashtable().containsKey("4th"));
	}
	
	/**
	 * searching for one bank
	 * @throws SecurityException
	 * @throws IOException
	 */
	@Test
	public void testLookup() throws SecurityException, IOException{
		B2B obj = new BankImpl();
		
		br1.bind("1st", obj);
		assertEquals(obj,br1.lookup("1st"));
	}

}
