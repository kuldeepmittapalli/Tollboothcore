package play.tollboothcore.dataObjects;

import java.util.List;

public class EMMGroupChargesRequest {
	
	List<EmmChargeRequest> transaction;



	public List<EmmChargeRequest> getTransaction() {
		return transaction;
	}



	public void setTransaction(List<EmmChargeRequest> transaction) {
		this.transaction = transaction;
	}



	@Override
	public String toString() {
		return "EMMGroupChargesRequest [transaction=" + transaction + "]";
	}
}
