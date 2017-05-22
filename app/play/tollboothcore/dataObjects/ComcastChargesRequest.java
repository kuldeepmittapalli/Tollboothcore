package play.tollboothcore.dataObjects;

import java.util.List;

import play.data.validation.Constraints.Required;

public class ComcastChargesRequest {
	
	@Required
	List<EmmChargeRequest> transactions;

	public List<EmmChargeRequest> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<EmmChargeRequest> transactions) {
		this.transactions = transactions;
	}
   
	@Override
	public String toString() {
		return "EMMGroupChargesRequest [transactions=" + transactions + "]";
	}
}
