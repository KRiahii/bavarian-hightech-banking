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
	
	@Test
	public void testIsEmpty(){
		assertTrue(theList.isEmpty());
	}
	
	@Test
	public void testMakeEmpty(){
		theList.insert(1, theItr);
		theList.makeEmpty();
		assertTrue(theList.isEmpty());
	}
	
	@Test
	public void testRetrieve(){
		
		for(int i = 0; i < 10; i++){
			theList.insert(new Integer(i), theItr);
			theList.printList(theList);
			theItr.advance();
		}
		
		LinkedListIterator<Integer> itrBuffer = theList.first();
		for(int i = 0; i < 4; i++)
		itrBuffer.advance();
		
		assertEquals(new Integer(4), itrBuffer.retrieve());
	}
	
}
