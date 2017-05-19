package play.tollboothcore.dataObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TollboothGateSubscribeResponse {

	private EmmServiceResponse emmserviceResponse;

	public EmmServiceResponse getEmmserviceResponse() {
		return emmserviceResponse;
	}
	
	public void setString() {
		
	}

	public void setEmmserviceResponse(EmmServiceResponse emmserviceResponse) {
		this.emmserviceResponse = emmserviceResponse;
	}

	@Override
	public String toString() {
		return "ComcastPurchaseResponse [emmserviceResponse=" + emmserviceResponse + "]";
	}

}
