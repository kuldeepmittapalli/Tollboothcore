package play.tollboothcore.dataObjects;

import java.math.BigDecimal;
import java.util.Date;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;

public class ComcastProrateCalculationRequest {
	@Required
	private String transactionID;
	
	@Required
	private String epcProductCode;
	
	@Required
	@Formats.DateTime(pattern="MM/dd/yyyy")
	private Date serviceStartDate;
	
	@Required
	@Formats.DateTime(pattern="MM/dd/yyyy")
	private Date serviceEndDate;
	
	@Required
	private BigDecimal chargeAmount;
	
	@Required
	private String description;
	
	@Required
	private String obfuscatedAccountToken;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getEpcProductCode() {
		return epcProductCode;
	}

	public void setEpcProductCode(String epcProductCode) {
		this.epcProductCode = epcProductCode;
	}

	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}


	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObfuscatedAccountToken() {
		return obfuscatedAccountToken;
	}

	public void setObfuscatedAccountToken(String obfuscatedAccountToken) {
		this.obfuscatedAccountToken = obfuscatedAccountToken;
	}

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	@Override
	public String toString() {
		return "ComcastProrateCalculationRequest [transactionID=" + transactionID + ", epcProductCode=" + epcProductCode
				+ ", serviceStartDate=" + serviceStartDate + ", serviceEndDate=" + serviceEndDate + ", chargeAmount="
				+ chargeAmount + ", description=" + description + ", obfuscatedAccountToken=" + obfuscatedAccountToken
				+ "]";
	}



	
}
