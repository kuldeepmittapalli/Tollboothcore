package play.tollboothcore.dataObjects;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EMMChargeResponse {
	private boolean success;
	private String transactionID;
	private String accountID;
	private String billingCycle;
	private String nxtReccSubsChargeDate;
	private BigDecimal chargeSubmitted;
	private List<RecurringCharges> recurringCharges;
	private EmmServiceResponse emmServiceResponse;
	private String error;
	private String error_description;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getNxtReccSubsChargeDate() {
		return nxtReccSubsChargeDate;
	}

	public void setNxtReccSubsChargeDate(String nxtReccSubsChargeDate) {
		this.nxtReccSubsChargeDate = nxtReccSubsChargeDate;
	}

	public BigDecimal getChargeSubmitted() {
		return chargeSubmitted;
	}

	public void setChargeSubmitted(BigDecimal chargeSubmitted) {
		this.chargeSubmitted = chargeSubmitted;
	}

	public List<RecurringCharges> getRecurringCharges() {
		return recurringCharges;
	}

	public void setRecurringCharges(List<RecurringCharges> recurringCharges) {
		this.recurringCharges = recurringCharges;
	}


	public EmmServiceResponse getEmmServiceResponse() {
		return emmServiceResponse;
	}

	public void setEmmServiceResponse(EmmServiceResponse emmServiceResponse) {
		this.emmServiceResponse = emmServiceResponse;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "EMMChargeResponse [success=" + success + ", transactionID=" + transactionID + ", accountID=" + accountID
				+ ", billingCycle=" + billingCycle + ", nxtReccSubsChargeDate=" + nxtReccSubsChargeDate
				+ ", chargeSubmitted=" + chargeSubmitted + ", recurringCharges=" + recurringCharges
				+ ", emmServiceResponse=" + emmServiceResponse + ", error=" + error + ", error_description="
				+ error_description + "]";
	}




}
