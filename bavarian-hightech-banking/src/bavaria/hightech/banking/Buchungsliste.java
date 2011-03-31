package bavaria.hightech.banking;

public class Buchungsliste {
	
	private Buchung h;
	private Buchung pos;
	
	public Buchungsliste(){
		
		this.h = new Buchung(null, null, ' ', null);
		this.pos = h;
	}
	
	public void add(String reason, String amount, char sign){
		
		Buchung obj = h;
		
		while(obj.next != null)
			obj = obj.next;
		
		obj.next = new Buchung(reason, amount, sign, null);
	}
	
	public void clear(){ this.h.next = null; }
	
	public Buchung next() { 
		
		if(pos.next != null)
			this.pos = pos.next;
		
		else
			return null;
		
		return pos;
		}
	
	public void resetIteration(){ this.pos = this.h; }

	private static class Buchung {
		String reason;
		String amount;
		char sign;
		Buchung next;
 
		Buchung(String reason, String amount, char sign, Buchung next) {
			this.reason = reason;
			this.amount = amount;
			this.next = next;
			this.sign = sign;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Buchung lnk = h.next;
		while (lnk != null) {
			sb.append("> " + lnk.reason + ": " + lnk.sign + lnk.amount );
			sb.append('\n');
			lnk = lnk.next;
		}
		return sb.toString();
	}
}