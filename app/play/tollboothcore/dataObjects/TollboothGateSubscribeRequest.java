package play.tollboothcore.dataObjects;

import java.math.BigDecimal;
import java.util.Date;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;

public class TollboothGateSubscribeRequest {
	@Required
	private String obfuscatedAccountToken;

	@Required
	private BigDecimal amount;

	@Required
	private String description;

	@Required
	@Formats.DateTime(pattern="MM/dd/yyyy")
	private Date serviceEndDate;

	@Required
	private String transactionId;

	@Required
	@Formats.DateTime(pattern="MM/dd/yyyy")
	private Date serviceStartDate;


	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getObfuscatedAccountToken() {
		return obfuscatedAccountToken;
	}

	public void setObfuscatedAccountToken(String obfuscatedAccountToken) {
		this.obfuscatedAccountToken = obfuscatedAccountToken;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	@Override
	public String toString() {
		return "TollboothGateSubscribeRequest [obfuscatedAccountToken=" + obfuscatedAccountToken + ", amount=" + amount
				+ ", description=" + description + ", serviceEndDate=" + serviceEndDate + ", transactionId="
				+ transactionId + ", serviceStartDate=" + serviceStartDate + "]";
	}

	

}
