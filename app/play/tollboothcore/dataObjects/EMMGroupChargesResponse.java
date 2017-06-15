package play.tollboothcore.dataObjects;

import java.util.List;

public class EMMGroupChargesResponse {
	private boolean success;
	private List<EMMChargeResponse> chargeSubscriberResponses;
	private String responseMessage;
	private int responseCode;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<EMMChargeResponse> getChargeSubscriberResponses() {
		return chargeSubscriberResponses;
	}

	public void setChargeSubscriberResponses(List<EMMChargeResponse> chargeSubscriberResponses) {
		this.chargeSubscriberResponses = chargeSubscriberResponses;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
