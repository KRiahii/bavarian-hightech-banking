package bavaria.hightech.banking;

public interface BankAdministration {

	void addKondition(FestgeldKonditionen fk);
	void addKondition(GiroKonditionen gk);
	void changeZins(float sollzins, float habezins);
}
