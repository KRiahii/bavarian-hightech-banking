package bavaria.hightech.banking;

public class Buchung {

	private Buchungsliste h;
	private Buchungsliste pos;
	
	public Buchung(){
		
		this.h = new Buchungsliste(null, null, ' ', null);
		this.pos = h;
	}
	
	public void add(String reason, String amount, char sign){
		
		Buchungsliste obj = h;
		while(obj.next != null)
			obj = obj.next;
		
		obj.next = new Buchungsliste(reason, amount, sign, null);
	}
	
	public void clear(){ this.h.next = null; }
	
	public Buchungsliste next(){ 
		
		if(pos.next != null)
			this.pos = pos.next;
		else
			return null;
		
		return pos;
		}
	
	public void resetIteration(){ this.pos = this.h; }


	private static class Buchungsliste {
		String reason;
		String amount;
		char sign;
		Buchungsliste next;
 
		Buchungsliste(String reason, String amount, char sign, Buchungsliste next) {
			this.reason = reason;
			this.amount = amount;
			this.next = next;
			this.sign = sign;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Buchungsliste lnk = h.next;
		while (lnk != null) {
			sb.append("> " + lnk.reason + ": " + lnk.sign + lnk.amount );
			sb.append('\n');
			lnk = lnk.next;
		}
		return sb.toString();
	}

	
}