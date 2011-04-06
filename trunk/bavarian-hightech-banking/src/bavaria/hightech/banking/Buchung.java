package bavaria.hightech.banking;

public class Buchung {

	private String reason;
	private String amount;
	private char sign;
	private Buchung next;

	Buchung(String reason, String amount, char sign, Buchung next) {
		this.reason = reason;
		this.amount = amount;
		this.next = next;
		this.sign = sign;
	}
	
	public void creatNext(String reason, String amount, char sign, Buchung next){
		
		this.next = new Buchung(reason, amount, sign, next);
	}

	public String getReason() {
		return this.reason;
	}

	public String getAmount() {
		return this.amount;
	}

	public char getSign() {
		return this.sign;
	}

	public Buchung getNext() {
		return this.next;
	}

}
