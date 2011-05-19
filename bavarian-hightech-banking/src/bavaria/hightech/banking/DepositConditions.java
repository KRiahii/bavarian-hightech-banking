package bavaria.hightech.banking;

/**
 * 
 * contains all deposits
 * 
 */
public class DepositConditions {

	private float interest;
	private int contractPeriod;

	/**
	 * getInterest()
	 * 
	 * @return
	 */
	public float getInterest() {
		return interest;
	}

	/**
	 * DepsoitConditions()
	 * 
	 * @param interest
	 * @param contractPeriod
	 */
	public DepositConditions(float interest, int contractPeriod) {

		this.contractPeriod = contractPeriod;
		this.interest = interest;
	}

	/**
	 * toString()
	 */
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("contract period: " + this.contractPeriod + " months");
		sb.append("\n");
		sb.append("interest: " + this.interest + "%");

		return sb.toString();
	}
}
