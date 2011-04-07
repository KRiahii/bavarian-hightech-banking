package bavaria.hightech.banking;

import java.text.NumberFormat;

import bavaria.hightech.banking.Money.Currency;
import bavaria.hightech.exceptions.MoneyException;

/**
 * 
 * Basisklasse Konto mit Attributen für Kontonummer, Kontostand und Kontoinhaber
 * und einer Methode für Zugang bzw. Abgang eines spezifizierten Betrages sowie
 * zum Abfragen der Kontonummer, des Kontoinhabers und des Kontostandes.
 * 
 */

public abstract class Konto {

	private int kNummer;
	protected Money kStand;
	private String kInhaber;
	private Buchungsliste buchungen;

	public Konto(int kNummer, String kInhaber) {

		this.kNummer = kNummer;
		this.kInhaber = kInhaber;
		this.kStand = new Money(0, Money.Currency.EURO);
		this.buchungen = new Buchungsliste();
	}

	/**
	 * get_kInhaber()
	 * 
	 * @return Kontoinhaber
	 */
	public String getInhaber() {
		return this.kInhaber;
	}

	/**
	 * get_kStand()
	 * 
	 * @return Kontostand(double)
	 */
	public long getKStand() {
		return kStand.getValue();
	}

	/**
	 * manageMoney()
	 * 
	 * @param
	 */
	public void manageMoney(String reason, long amount, char sign,
			Currency currency) throws MoneyException {

		if (amount < 0) {

			throw new MoneyException("Ungültiger Betrag " + amount);
		}

		if (sign != '+' && sign != '-') {

			throw new MoneyException("Ungültiges Vorzeichen " + sign);
		}

		verbuchen(reason, amount, sign, currency);
	}

	/**
	 * get_kNummer()
	 * 
	 * @return Kontonummer
	 */
	public int getKnummer() {
		return this.kNummer;
	}

	/**
	 * verbuchen()
	 * 
	 * @param
	 */
	protected void verbuchen(String reason, long amount, char sign,
			Currency currency) throws MoneyException {

		if (amount < 0)
			throw new MoneyException("Ungültiger wert " + amount);

		if (sign == '+')
			this.kStand.add(amount, currency);

		else
			this.kStand.sub(amount, currency);

		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2);
		buchungen.add(reason, n.format(amount), sign, currency);
	}

	@Override
	public String toString() {

		NumberFormat n = NumberFormat.getInstance();
		n.setMaximumFractionDigits(2);

		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + getInhaber()).append("\n");
		sb.append("Kontonummer: " + getKnummer()).append("\n");
		sb.append("Guthaben: " + n.format(getKStand())).append("\n");
		sb.append("Währung: " + this.kStand.getCurrency());
		sb.append("\n--------------------------------------\n");
		sb.append(buchungen.toString());

		return sb.toString();
	}

	abstract public void printTyp();

	abstract public void verzinsen() throws MoneyException;
}