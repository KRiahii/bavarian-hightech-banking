package bavaria.hightech.banking;

import bavaria.hightech.banking.Money.Currency;

public interface BankKundenSicht {

	void createAcc(String typ, String kInhaber, int key);

	void showMoney(int kNummer);

	void addMoney(long amount, int kNummer, Currency currency);

	void requestMoney(long amount, int kNummer, Currency currency);

	void transferMoney(long amount, int kNummerFROM, int kNummerTO,
			Currency currencyFROM, Currency currenyTO);
}