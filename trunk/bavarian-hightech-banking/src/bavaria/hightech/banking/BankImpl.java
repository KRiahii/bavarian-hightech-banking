package bavaria.hightech.banking;

import bavaria.hightech.exceptions.MoneyException;
import bavaria.hightech.exceptions.TypException;

public class BankImpl implements BankKundenSicht, BankAdministration {

	private Konto[] konten;
	private int count;

	private GiroKonditionen[] giro;
	private FestgeldKonditionen[] festgeld;

	public BankImpl() {

		this.konten = new Konto[20];
		this.count = 0;
		
		defaultKonditionen();
	}

	@Override
	public void createAcc(String typ, String kInhaber, int key) {

		if (typ.equals("GiroKonto"))
			calculateIndex(2000 + count, new GiroKonto(2000 + count++,
					kInhaber, giro[key]));

		else if (typ.equals("FestgeldKonto"))
			calculateIndex(2000 + count, new FestgeldKonto(2000 + count++,
					kInhaber, festgeld[key]));

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
	public void showMoney(int kNummer) {

		System.out.println(">> " + calculateIndex(kNummer).getKStand()
				+ " Konto: " + calculateIndex(kNummer).getKnummer());
	}

	public void verzinsen() throws MoneyException {

		for (int i = 0; i < konten.length; i++)
			if (konten[i] != null)
				konten[i].verzinsen();
	}

	public void list() {

		for (int i = 0; i < konten.length; i++)
			if (konten[i] != null) {
				System.out.println("---------------------------");
				System.out.println(konten[i].toString());
			} else
				return;
	}

	public String kontoAuszug(int kontoNummer) {
		return calculateIndex(kontoNummer).toString();
	}

	private Konto calculateIndex(int kontoNummer) {
		return konten[kontoNummer - 2000];
	}

	private void calculateIndex(int kontoNummer, Konto obj) {
		konten[kontoNummer - 2000] = obj;
	}

	private void defaultKonditionen() {

		this.giro = new GiroKonditionen[3];
		this.festgeld = new FestgeldKonditionen[3];

		giro[0] = new GiroKonditionen(100);
		giro[1] = new GiroKonditionen(500);
		giro[2] = new GiroKonditionen(1000);

		festgeld[0] = new FestgeldKonditionen(1.34f, 2);
		festgeld[1] = new FestgeldKonditionen(2.34f, 6);
		festgeld[2] = new FestgeldKonditionen(3.34f, 12);
	}

	public void showFestgeldkonditionen() {

		for (int i = 0; i < this.festgeld.length; i++)
			System.out.println(festgeld[i].toString());
	}

	public void showGirokonditionen() {

		for (int i = 0; i < this.giro.length; i++)
			System.out.println(giro[i].toString());
	}

	public void addKondition(FestgeldKonditionen fk) {

		FestgeldKonditionen[] buffer = new FestgeldKonditionen[this.festgeld.length];
		
		for(int i = 0; i < buffer.length; i++)
			buffer[i] = this.festgeld[i];

		this.festgeld = new FestgeldKonditionen[buffer.length + 1];

		for (int i = 0; i < buffer.length; i++)
			this.festgeld[i] = buffer[i];

		this.festgeld[buffer.length] = fk;
	}

	public void addKondition(GiroKonditionen gk) {

		GiroKonditionen[] buffer = new GiroKonditionen[this.giro.length];
		
		for(int i = 0; i < buffer.length; i++)
			buffer[i] = this.giro[i];

		this.giro = new GiroKonditionen[buffer.length + 1];

		for (int i = 0; i < buffer.length; i++)
			this.giro[i] = buffer[i];

		this.giro[buffer.length] = gk;
	}

	@Override
	public void changeZins(float sollzins, float habezins) {
		
		for(int i = 0; i < giro.length; i++)
			giro[i].SetZins(sollzins, habezins);
	}
}