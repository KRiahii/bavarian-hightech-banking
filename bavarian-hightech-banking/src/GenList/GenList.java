package GenList;

public class GenList<E> {

	private LinkElement<E> head;
	private LinkedListIterator itr;
	
    /**
     * Construct the list
     */
	public GenList(){
		this.head = new LinkElement<E>(null, null);
		zero();
	}
	
    /**
     * @return true if empty
     */
    public boolean isEmpty( ) {
        return head.next == null;
    }
    
    /**
     * Make the list empty.
     */
    public void makeEmpty( ) {
        head.next = null;
    }
    
    /**
     * @return the current element
     */
    public E getElement(){
    	return itr.retrieve();
    }
    
    /**
     * Return an iterator representing the header node.
     */
    public void zero( ) {
        itr =  new LinkedListIterator( head );
    }
    
    /**
     * Insert after E.
     */
    public void insert( E element ) {
        if( itr != null && itr.current != null )
            itr.current.next = new LinkElement<E>( element, itr.current.next );
    }
    
    /**
     * Set Iterator to next element.
     * @return true if the next element is valid
     */
    public boolean next(){
    	itr.advance();
    	
    	return itr.isValid();
    }

    private class LinkedListIterator {
    	
	    LinkElement<E> current;    // Current position

	    LinkedListIterator( LinkElement<E> current ) {
	        this.current = current;
	    }
	    
	    /**
	     * @return true if the current position is valid.
	     */
	    public boolean isValid( ) {
	        return current != null;
	    }
	    
	    /**
	     * @return the stored item or null.
	     */
	    public E retrieve( ) {
	        return isValid( ) ? current.element : null;
	    }
	    
	    /**
	     * Advance the current position to the next node in the list.
	     */
	    public void advance( ) {
	        if( isValid( ) )
	            current = current.next;
	    }
	}
	
    /**
     * Element holder class.
     */
	private static class LinkElement<E> {
		E element;
		LinkElement<E> next;

		LinkElement(E element, LinkElement<E> next) {
			this.element = element;
			this.next = next;
		}
	}
}
