package bavaria.hightech.banking;

public class GiroConditions {

	private int overpullingFrame;

	private float debitInterest;
	private float creditInterest;

	public GiroConditions(int overpullingFrame) {

		this.overpullingFrame = overpullingFrame;
		this.debitInterest = 5.49f;
		this.creditInterest = 2.34f;
	}

	public void setInterest(float debitInterest, float creditInterest) {

		this.debitInterest = debitInterest;
		this.creditInterest = creditInterest;
	}

	public int getOverpullingFrame() {
		return this.overpullingFrame;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("overpulling frame: " + this.overpullingFrame);

		return sb.toString();
	}

	public float getDebitInterest() {
		return this.debitInterest;
	}

	public float getCreditInterest() {
		return this.creditInterest;
	}
}
