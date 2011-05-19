package bavaria.hightech.banking;

/**
 * 
 * class to manage currency
 * 
 */
public class Money {
	private long value;
	private Currency currency;

	public Money(long value, Currency currency) {
		this.setValue(value);
		this.setCurrency(currency);
	}

	/**
	 * setValue()
	 * 
	 * @param value
	 */
	public long setValue(long value) {
		return this.value = value;
	}

	/**
	 * getValue()
	 * 
	 * @return
	 */
	public long getValue() {
		return value;
	}

	/**
	 * setCurrency()
	 * 
	 * @param currency
	 */
	public void setCurrency(Currency currency) {
		switch (currency) {
		case EURO:
			this.currency = Currency.EURO;
			break;
		case USDOLLAR:
			this.currency = Currency.USDOLLAR;
			break;
		case SCHWEIZERFRANKEN:
			this.currency = Currency.SCHWEIZERFRANKEN;
			break;
		case JAPANISCHERYEN:
			this.currency = Currency.JAPANISCHERYEN;
			break;
		case BRITISCHESPFUND:
			this.currency = Currency.BRITISCHESPFUND;
			break;
		}
	}

	/**
	 * getCurrency()
	 * 
	 * @return
	 */
	public Currency getCurrency() {
		return currency;
	}

	public enum Currency {
		EURO(1.0000), USDOLLAR(0.7000), SCHWEIZERFRANKEN(0.7598), JAPANISCHERYEN(
				0.0082), BRITISCHESPFUND(1.1419);

		private double factor;

		/**
		 * setFactor()
		 * 
		 * @param factor
		 */
		public void setFactor(double factor) {
			this.factor = factor;
		}

		/**
		 * getFactor()
		 * 
		 * @return
		 */
		public double getFactor() {
			return factor;
		}

		Currency(double factor) {
			this.factor = factor;
		}
	}

	/**
	 * addMoney()
	 * 
	 * @param value
	 * @param currency
	 */
	public long addMoney(long value, Currency currency) {
		switch (currency) {
		case EURO:
			this.value = (long) (this.value + (value * Currency.EURO
					.getFactor())
					/ this.currency.getFactor());
			break;
		case USDOLLAR:
			this.value = (long) (this.value + (value * Currency.USDOLLAR
					.getFactor())
					/ this.currency.getFactor());
			break;
		case SCHWEIZERFRANKEN:
			this.value = (long) (this.value + (value * Currency.SCHWEIZERFRANKEN
					.getFactor())
					/ this.currency.getFactor());
			break;
		case JAPANISCHERYEN:
			this.value = (long) (this.value + (value * Currency.JAPANISCHERYEN
					.getFactor())
					/ this.currency.getFactor());
			break;
		case BRITISCHESPFUND:
			this.value = (long) (this.value + (value * Currency.BRITISCHESPFUND
					.getFactor())
					/ this.currency.getFactor());
			break;
		}

		return this.value;
	}

	/**
	 * subMoney()
	 * 
	 * @param value
	 * @param currency
	 */
	public long subMoney(long value, Currency currency) {
		switch (currency) {
		case EURO:
			this.value = (long) (this.value - (value * Currency.EURO
					.getFactor())
					/ this.currency.getFactor());
			break;
		case USDOLLAR:
			this.value = (long) (this.value - (value * Currency.USDOLLAR
					.getFactor())
					/ this.currency.getFactor());
			break;
		case SCHWEIZERFRANKEN:
			this.value = (long) (this.value - (value * Currency.SCHWEIZERFRANKEN
					.getFactor())
					/ this.currency.getFactor());
			break;
		case JAPANISCHERYEN:
			this.value = (long) (this.value - (value * Currency.JAPANISCHERYEN
					.getFactor())
					/ this.currency.getFactor());
			break;
		case BRITISCHESPFUND:
			this.value = (long) (this.value - (value * Currency.BRITISCHESPFUND
					.getFactor())
					/ this.currency.getFactor());
			break;
		}
		return this.value;
	}

	/**
	 * equalMoney()
	 * 
	 * @param value
	 * @return
	 */
	public boolean equalMoney(long value) {
		if (this.value == value) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * isBigger()
	 * 
	 * @param value
	 * @return
	 */
	public boolean isBigger(long value) {
		if (this.value > value) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * isSmaler()
	 * 
	 * @param value
	 * @return
	 */
	public boolean isSmaler(long value) {
		if (this.value < value) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * changeCurrency()
	 * 
	 * @param currency
	 */
	public void changeCurrency(Currency currency) {
		value = (long) ((value * this.currency.getFactor()) / currency
				.getFactor());
		setCurrency(currency);
	}

}