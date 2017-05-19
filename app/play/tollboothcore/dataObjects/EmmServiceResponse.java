package play.tollboothcore.dataObjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EmmServiceResponse {
	
	private List<String> responseMessages;

	private String responseCode;
		
	public String getResponseCode() {
		return responseCode;
	}
	
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public List<String> getResponseMessages() {
		return responseMessages;
	}

	public void setResponseMessages(List<String> responseMessages) {
		this.responseMessages = responseMessages;
	}

	@Override
	public String toString() {
		return "EmmServiceResponse [responseMessages=" + responseMessages + ", responseCode=" + responseCode + "]";
	}
}
    

