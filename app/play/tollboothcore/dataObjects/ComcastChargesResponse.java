package play.tollboothcore.dataObjects;

import java.util.List;

public class ComcastChargesResponse {
	private boolean success;
	private List<EMMChargeResponse> transactions;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<EMMChargeResponse> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<EMMChargeResponse> transactions) {
		this.transactions = transactions;
	}
	
	
}
