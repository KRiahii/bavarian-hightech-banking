package bavaria.hightech.banking;

public class Buchungsliste {

	private Buchung h;
	private Buchung pos;

	public Buchungsliste() {

		this.h = new Buchung(null, null, ' ', null);
		this.pos = h;
	}

	public void add(String reason, String amount, char sign) {

		Buchung obj = h;

		while (obj.getNext() != null)
			obj = obj.getNext();

		obj.creatNext(reason, amount, sign, null);
	}

	public void clear() {
		this.h = new Buchung(null, null, ' ', null);
	}

	Buchung next() {

		if (pos.getNext() != null)
			this.pos = pos.getNext();

		else
			return null;

		return pos;
	}

	public void resetIteration() {
		this.pos = this.h;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Buchung lnk = h.getNext();
		while (lnk != null) {
			sb.append("> " + lnk.getReason() + ": " + lnk.getSign() + lnk.getAmount());
			sb.append('\n');
			lnk = lnk.getNext();
		}
		return sb.toString();
	}
}