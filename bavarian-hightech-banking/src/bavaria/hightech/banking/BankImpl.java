package bavaria.hightech.banking;

import bavaria.hightech.banking.Konto.MoneyException;

public class BankImpl implements BankKundenSicht{
	
	@SuppressWarnings("serial")
	public class TypException extends RuntimeException {
		
		public TypException() { 
			super(); 
		}
		
	    public TypException(String s) { 
	    	super(s); 
	    }
	}
	
	private Konto[] konten;
	private int count;

	public BankImpl(){
		
		this.konten = new Konto[20];
		this.count = 0;
	}
	
	@Override
	public void createAcc(String typ, float zins, String kInhaber) {
		
		if( typ.equals("GiroKonto") )
			calculateIndex(2000+count, new GiroKonto(zins, 2000+count++, kInhaber));

		else if( typ.equals("FestgeldKonto") )
			calculateIndex(2000+count, new FestgeldKonto(zins, 2000+count++, kInhaber));
		
		else
			throw new TypException("Ungültiger Typ");	
	}
	
	@Override
	public void addMoney(double amount, int kNummer) {
		
		try {
			calculateIndex(kNummer).manageMoney("Eingezahlt", amount, '+');
		} catch (MoneyException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void requestMoney(double amount, int kNummer) {
		
		try {
			calculateIndex(kNummer).manageMoney("Abgehoben", amount, '-');
		} catch (MoneyException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void transferMoney(double amount, int kNummerFROM, int kNummerTO) {
		
		try {
			calculateIndex(kNummerFROM).manageMoney("Überweisung", amount, '-');
		} catch (MoneyException e) {
			e.printStackTrace();
		}
		
		try {
			calculateIndex(kNummerTO).manageMoney("Einzahlung", amount, '+');
		} catch (MoneyException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showMoney(int kNummer){
		
		System.out.println(">> " + calculateIndex(kNummer).getKStand() + " Konto: " + calculateIndex(kNummer).getKnummer());
	}

	public void verzinsen() throws MoneyException {
		
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
	
	public String kontoAuszug(int kontoNummer){ return calculateIndex(kontoNummer).toString(); }
	
	private Konto calculateIndex(int kontoNummer){ return konten[kontoNummer-2000]; }
	
	private void calculateIndex(int kontoNummer, Konto obj){ konten[kontoNummer-2000] = obj; }
}