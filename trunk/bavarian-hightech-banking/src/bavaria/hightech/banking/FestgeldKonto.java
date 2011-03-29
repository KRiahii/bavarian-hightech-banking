package bavaria.hightech.banking;

/**
 * 
 * FestgeldKonto mit einem HabeZins Attribut
 * und entsprechender Verzinsungsmethode
 * !!!!kein negativer Kontostand möglich!!!!
 *
 */

public class FestgeldKonto extends Konto {
	
	private float zins;

	/**
	 * FestgeldKonto()
	 * @param zins
	 * @param kNummer
	 * @param kInhaber
	 */
	public FestgeldKonto(float zins, int kNummer, String kInhaber){
		
		super(kNummer, kInhaber);
		this.zins = zins;
	}
	
	@Override
	public void verzinsen(){
		
		double amount = (this.getKStand()/100)*this.zins;
		this.manageMoney("Zins", amount, '+');
	}
	
	@Override
	public void printTyp(){ System.out.println("FestgeldKonto:"); }
	
	/**set_kStand()
	 * @param
	 */
	
	@Override
	public void manageMoney(String reason, double amount, char sign){
		
		if( sign == '-' )
			if( this.getKStand() - amount < 0 ){
				
				System.out.println();
				System.out.println(">> Nicht genug Geld vorhanden!");
				System.out.println();
				return;
			}
		
		super.verbuchen(reason, amount, sign);
	}
}
