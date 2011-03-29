package bavaria.hightech.banking;

import java.text.NumberFormat;

/**
 * 
 * Basisklasse Konto mit Attributen für 
 * Kontonummer, Kontostand und Kontoinhaber
 * und einer Methode für Zugang bzw. 
 * Abgang eines spezifizierten Betrages sowie 
 * zum Abfragen der Kontonummer, 
 * des Kontoinhabers und des Kontostandes.
 *
 */

public abstract class Konto {

	private int kNummer;
	private double kStand;
	private String kInhaber;
	private Buchung buchungen;
	
	public Konto(int kNummer, String kInhaber){
	
		this.kNummer = kNummer;
		this.kInhaber = kInhaber;
		this.kStand = 0;
		
		this.buchungen = new Buchung();
	}
	
	/**get_kInhaber()
	 * @return Kontoinhaber
	 */
	public String getInhaber(){ return this.kInhaber; } 
	
	/**get_kStand()
	 * @return Kontostand(double)
	 */
	public double getKStand(){ return this.kStand; }
	
	/**set_kStand()
	 * @param
	 */
	public void manageMoney(String reason, double amount, char sign){
	
		if( amount < 0 ){
			
			System.out.println("Ungültiger Betrag");
			return;
		}
		
		if( sign != '+' && sign != '-' ){
			
			System.out.println();
			System.out.println(">> Ungültiges Vorzeichen");
			System.out.println();
			return;
		}
		
		verbuchen(reason, amount, sign);
	}
	
	/**get_kNummer()
	 * @return Kontonummer
	 */
	public int getKnummer(){ return this.kNummer; }
	
	/**verbuchen()
	 * @param
	 */
	protected void verbuchen(String reason, double amount, char sign){
		
		if( amount < 0 )
			return;
		
		if( sign == '+' )
			this.kStand += amount;
		
		else
			this.kStand -= amount;

		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2);
		buchungen.add(reason, n.format(amount), sign);
	}
	
	@Override
	public String toString(){
		
		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + getInhaber()).append("\n");
		sb.append("Kontonummer: " + getKnummer()).append("\n");
		sb.append("Guthaben: " + n.format(getKStand()));
		sb.append("\n--------------------------------------\n");
		sb.append(buchungen.toString());

		return sb.toString();
	}
	
	abstract public void printTyp();
	abstract public void verzinsen();
}
