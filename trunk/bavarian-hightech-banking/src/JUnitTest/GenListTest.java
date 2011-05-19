package JUnitTest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import GenList.GenList;
import GenList.LinkedListIterator;

public class GenListTest {

	GenList<Integer> theList;
	LinkedListIterator<Integer> theItr;

	@Before
	public void setUp() {
		this.theList = new GenList<Integer>();
		this.theItr = theList.inception();
	}

	/**
	 * Empty list?
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(theList.isEmpty());
	}

	/**
	 * empty again?
	 */
	@Test
	public void testMakeEmpty() {
		theList.insert(1, theItr);
		theList.makeEmpty();
		assertTrue(theList.isEmpty());
	}

	/**
	 * get the first element
	 */
	@Test
	public void testFirst() {
		fill();
		theList.printList(theList);
		assertEquals(new Integer(0), theList.first().retrieve());
	}

	/**
	 * get the right element
	 */
	@Test
	public void testRetrieve() {

		fill();

		LinkedListIterator<Integer> itrBuffer = theList.first();
		for (int i = 0; i < 4; i++)
			itrBuffer.advance();

		assertEquals(new Integer(4), itrBuffer.retrieve());
	}

	/**
	 * fill the list
	 */
	private void fill() {
		for (int i = 0; i < 10; i++) {
			theList.insert(new Integer(i), theItr);
			theItr.advance();
		}
	}

}
