package bavaria.hightech.banking;

public interface BankKundenSicht {

	void requestMoney(double amount, int kNummer);

	void transferMoney(double amount, int kNummerFROM, int kNummerTO);

	void createAcc(String typ, float zins, String kInhaber);

	void addMoney(double amount, int kNummer);
	
	void showMoney(int kNummer);

}
