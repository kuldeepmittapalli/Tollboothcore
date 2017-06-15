package play.tollboothcore.dataObjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EmmServiceResponse {
	
	private List<String> responseMessages;

	private int responseCode;

	public List<String> getResponseMessages() {
		return responseMessages;
	}

	public void setResponseMessages(List<String> responseMessages) {
		this.responseMessages = responseMessages;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	@Override
	public String toString() {
		return "EmmServiceResponse [responseMessages=" + responseMessages + ", responseCode=" + responseCode + "]";
	}
		

}
    

