package play.tollboothcore.dataObjects;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;

public class EmmChargeRequest {
	@Required
	private String transactionID;

	@Required
	private String chargeTokenID;

	@Required
	private String epcProductCode;

	@Required
	@Formats.DateTime(pattern = "MM/dd/yyyy")
	@JsonFormat(pattern =  "MM/dd/yyyy")
	private Date serviceStartDate;

	@Required
	@Formats.DateTime(pattern = "MM/dd/yyyy")
	@JsonFormat(pattern =  "MM/dd/yyyy")
	private Date serviceEndDate;

	@Required
	private BigDecimal chargeAmount;

	@Required
	private String description;

	@Required
	private String uben;

	@Required
	private String providerCode;

	@Required
	private String titlePaid;

	@Required
	private String obfuscatedAccountToken;

	private boolean prorate;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getChargeTokenID() {
		return chargeTokenID;
	}

	public void setChargeTokenID(String chargeTokenID) {
		this.chargeTokenID = chargeTokenID;
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

	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
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

	public String getUben() {
		return uben;
	}

	public void setUben(String uben) {
		this.uben = uben;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getTitlePaid() {
		return titlePaid;
	}

	public void setTitlePaid(String titlePaid) {
		this.titlePaid = titlePaid;
	}

	public String getObfuscatedAccountToken() {
		return obfuscatedAccountToken;
	}

	public void setObfuscatedAccountToken(String obfuscatedAccountToken) {
		this.obfuscatedAccountToken = obfuscatedAccountToken;
	}

	public boolean isProrate() {
		return prorate;
	}

	public void setProrate(boolean prorate) {
		this.prorate = prorate;
	}

	@Override
	public String toString() {
		return "EmmChargeRequest [transactionID=" + transactionID + ", chargeTokenID=" + chargeTokenID
				+ ", epcProductCode=" + epcProductCode + ", serviceStartDate=" + serviceStartDate + ", serviceEndDate="
				+ serviceEndDate + ", chargeAmount=" + chargeAmount + ", description=" + description + ", uben=" + uben
				+ ", providerCode=" + providerCode + ", titlePaid=" + titlePaid + ", obfuscatedAccountToken="
				+ obfuscatedAccountToken + ", prorate=" + prorate + "]";
	}
}
