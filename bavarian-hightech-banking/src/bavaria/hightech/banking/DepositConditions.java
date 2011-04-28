package bavaria.hightech.banking;

public class DepositConditions {

	private float interest;
	private int contractPeriod;

	public float getInterest() {
		return interest;
	}

	public DepositConditions(float interest, int contractPeriod) {

		this.contractPeriod = contractPeriod;
		this.interest = interest;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("contract period: " + this.contractPeriod + " months");
		sb.append("\n");
		sb.append("interest: " + this.interest + "%");

		return sb.toString();
	}
}
