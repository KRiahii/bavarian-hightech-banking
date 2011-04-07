package bavaria.hightech.banking;

public class FestgeldKonditionen {

	private float zins;
	private int laufzeit;

	public float getZins() {
		return zins;
	}

	public FestgeldKonditionen(float zins, int laufzeit) {

		this.laufzeit = laufzeit;
		this.zins = zins;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("Laufzeit: " + this.laufzeit + " Monate");
		sb.append("\n");
		sb.append("Zins: " + this.zins + "%");

		return sb.toString();
	}
}
