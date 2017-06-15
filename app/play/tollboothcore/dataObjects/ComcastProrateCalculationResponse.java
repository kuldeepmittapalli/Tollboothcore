package play.tollboothcore.dataObjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComcastProrateCalculationResponse {
	private boolean success;
	private EmmServiceResponse emmServiceResponse;
	private int billingCycle;
	private String nxtReccSubsChargeDate;
	private List<RecurringCharges> recurringCharges;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public EmmServiceResponse getEmmServiceResponse() {
		return emmServiceResponse;
	}

	public void setEmmServiceResponse(EmmServiceResponse emmServiceResponse) {
		this.emmServiceResponse = emmServiceResponse;
	}

	public int getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(int billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getNxtReccSubsChargeDate() {
		return nxtReccSubsChargeDate;
	}

	public void setNxtReccSubsChargeDate(String nxtReccSubsChargeDate) {
		this.nxtReccSubsChargeDate = nxtReccSubsChargeDate;
	}

	public List<RecurringCharges> getRecurringCharges() {
		return recurringCharges;
	}

	public void setRecurringCharges(List<RecurringCharges> recurringCharges) {
		this.recurringCharges = recurringCharges;
	}

	@Override
	public String toString() {
		return "ComcastProrateCalculationResponse [success=" + success + ", emmServiceResponse=" + emmServiceResponse
				+ ", billingCycle=" + billingCycle + ", nxtReccSubsChargeDate=" + nxtReccSubsChargeDate
				+ ", recurringCharges=" + recurringCharges + "]";
	}

}
