package bavaria.hightech.banking;

public class Money {
	private long value;
	private Currency currency;

	public Money(long value, Currency currency) {
		this.setValue(value);
		this.setCurrency(currency);
	}

	public void setValue(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

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

	public Currency getCurrency() {
		return currency;
	}

	public enum Currency {
		EURO(1.0000), USDOLLAR(0.7000), SCHWEIZERFRANKEN(0.7598), JAPANISCHERYEN(
				0.0082), BRITISCHESPFUND(1.1419);

		private double factor;

		public void setFactor(double factor) {
			this.factor = factor;
		}

		public double getFactor() {
			return factor;
		}

		Currency(double factor) {
			this.factor = factor;
		}
	}

	public void addMoney(long value, Currency currency) {
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
	}

	public void subMoney(long value, Currency currency) {
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
	}

	public boolean equalMoney(long value) {
		if (this.value == value) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBigger(long value) {
		if (this.value > value) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSmaler(long value) {
		if (this.value < value) {
			return true;
		} else {
			return false;
		}
	}

	public void changeCurrency(Currency currency) {
		value = (long) ((value * this.currency.getFactor()) / currency
				.getFactor());
		setCurrency(currency);
	}

}