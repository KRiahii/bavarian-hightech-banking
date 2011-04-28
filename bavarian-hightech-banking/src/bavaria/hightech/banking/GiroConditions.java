package bavaria.hightech.banking;

/**
 * 
 * contains all giro conditions
 *
 */
public class GiroConditions {

	private int overpullingFrame;

	private float debitInterest;
	private float creditInterest;

	public GiroConditions(int overpullingFrame) {

		this.overpullingFrame = overpullingFrame;
		this.debitInterest = 5.49f;
		this.creditInterest = 2.34f;
	}

	/**
	 * setInterest()
	 * @param debitInterest
	 * @param creditInterest
	 */
	public void setInterest(float debitInterest, float creditInterest) {

		this.debitInterest = debitInterest;
		this.creditInterest = creditInterest;
	}

	/**
	 * getOverpullingFrame()
	 * @return
	 */
	public int getOverpullingFrame() {
		return this.overpullingFrame;
	}

	/**
	 * toString()
	 */
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("overpulling frame: " + this.overpullingFrame);

		return sb.toString();
	}

	/**
	 * getDebitInterest()
	 * @return
	 */
	public float getDebitInterest() {
		return this.debitInterest;
	}

	/**
	 * getCreditInterest()
	 * @return
	 */
	public float getCreditInterest() {
		return this.creditInterest;
	}
}
