package play.tollboothcore.dataObjects;

public class RecurringCharges {
	private String obfuscatedAccountToken;
	private String chargeAmount;
	private String serviceStartDate;
	private String serviceEndDate;

	public String getObfuscatedAccountToken() {
		return obfuscatedAccountToken;
	}

	public void setObfuscatedAccountToken(String obfuscatedAccountToken) {
		this.obfuscatedAccountToken = obfuscatedAccountToken;
	}

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public String getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	@Override
	public String toString() {
		return "RecurringCharges [obfuscatedAccountToken=" + obfuscatedAccountToken + ", chargeAmount=" + chargeAmount
				+ ", serviceStartDate=" + serviceStartDate + ", serviceEndDate=" + serviceEndDate + "]";
	}

}
