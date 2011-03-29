package bavaria.hightech.banking;

public class BankImpl implements BankKundenSicht{
	
	private Konto[] konten;
	private int count;

	public BankImpl(){
		
		this.konten = new Konto[20];
		this.count = 0;
	}
	
	@Override
	public void createAcc(String typ, float zins, String kInhaber) {
		
		if( typ.equals("GiroKonto") )
			konten[calculateIndex(2000+count)] = new GiroKonto(zins, 2000+count++, kInhaber);

		else if( typ.equals("FestgeldKonto") )
			konten[calculateIndex(2000+count)] = new FestgeldKonto(zins, 2000+count++, kInhaber);
		
		else{
			
			System.out.println(">> Ungültiger Typ!");
			return;
		}
			
	}
	
	@Override
	public void addMoney(double amount, int kNummer) {
		
		konten[calculateIndex(kNummer)].manageMoney("Eingezahlt", amount, '+');
	}

	@Override
	public void requestMoney(double amount, int kNummer) {
		
		konten[calculateIndex(kNummer)].manageMoney("Abgehoben", amount, '-');
	}

	@Override
	public void transferMoney(double amount, int kNummerFROM, int kNummerTO) {
		
		konten[calculateIndex(kNummerFROM)].manageMoney("Überweisung", amount, '-');
		
		konten[calculateIndex(kNummerTO)].manageMoney("Einzahlung", amount, '+');
	}
	
	@Override
	public void showMoney(int kNummer){
		
		System.out.println(">> " + konten[calculateIndex(kNummer)].getKStand() + " Konto: " + konten[calculateIndex(kNummer)].getKnummer());
	}

	public void verzinsen(){
		
		for(int i = 0; i < konten.length; i++)
			if(konten[i] != null)
			konten[i].verzinsen();
	}
	
	public void list(){
		
		for(int i = 0; i < konten.length; i++)
			if(konten[i] != null){
			System.out.println("---------------------------");	
			System.out.println(konten[i].toString());
			}
			else
				return;
	}
	
	private int calculateIndex(int kontoNummer){ return kontoNummer-2000; }

}
