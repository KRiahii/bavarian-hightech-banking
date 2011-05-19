package GenList;

public class LinkedListIterator<E> {

	LinkElement<E> current; // Current position

	/**
	 * Construct the list iterator
	 */
	LinkedListIterator(LinkElement<E> node) {
		current = node;
	}

	/**
	 * @return true if the current position is valid.
	 */
	public boolean isValid() {
		return current != null;
	}

	/**
	 * @return the stored item or null
	 */
	public Object retrieve() {
		return isValid() ? current.element : null;
	}

	/**
	 * Advance the current position to the next node in the list.
	 */
	public void advance() {
		if (isValid())
			current = current.next;
	}
}