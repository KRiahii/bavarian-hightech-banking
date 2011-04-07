package bavaria.hightech.banking;

public class GiroKonditionen {

	private int ueberziehungsrahmen;

	private float sollzins;
	private float habezins;

	public GiroKonditionen(int ueberziehungsrahmen) {

		this.ueberziehungsrahmen = ueberziehungsrahmen;
		this.sollzins = 5.49f;
		this.habezins = 2.34f;
	}

	public void SetZins(float sollzins, float habezins) {

		this.sollzins = sollzins;
		this.habezins = habezins;
	}

	public int getUeberziehungsrahmen() {
		return this.ueberziehungsrahmen;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("Überziehungsrahmen: " + this.ueberziehungsrahmen);

		return sb.toString();
	}

	public float getSollzins() {
		return this.sollzins;
	}

	public float getHabezins() {
		return this.habezins;
	}
}
