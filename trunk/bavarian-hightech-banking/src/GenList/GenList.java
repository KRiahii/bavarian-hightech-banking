package GenList;

public class GenList<E> {

	private LinkElement<E> header;

	/**
	 * Construct the list
	 */
	public GenList() {
		header = new LinkElement<E>(null, null);
	}

	/**
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return header.next == null;
	}

	/**
	 * Make the list empty.
	 */
	public void makeEmpty() {
		header.next = null;
	}

	/**
	 * @return an iterator representing the header node.
	 */
	public LinkedListIterator<E> zeroth() {
		return new LinkedListIterator<E>(header);
	}

	/**
	 * @return an iterator representing the first node in the list.
	 */
	public LinkedListIterator<E> first() {
		return new LinkedListIterator<E>(header.next);
	}

	/**
	 * Insert after p.
	 */
	public void insert(E x, LinkedListIterator<E> p) {
		if (p != null && p.current != null)
			p.current.next = new LinkElement<E>(x, p.current.next);
	}

	/**
	 * Prints out the list.
	 */
	public void printList(GenList<?> theList) {
		if (theList.isEmpty())
			System.out.print("Empty list");
		else {
			LinkedListIterator<?> itr = theList.first();
			for (; itr.isValid(); itr.advance())
				System.out.print(itr.retrieve() + " ");
		}

		System.out.println();
	}
}

/**
 * Element holder class.
 */
class LinkElement<E> {
	E element;
	LinkElement<E> next;

	LinkElement(E element, LinkElement<E> next) {
		this.element = element;
		this.next = next;
	}
}