package play.tollboothcore.dataObjects;

import com.fasterxml.jackson.databind.JsonNode;

public class FutureResult {

	private JsonNode jsonNode;

	private int status;

	public JsonNode getJsonNode() {
		return jsonNode;
	}

	public void setJsonNode(JsonNode jsonNode) {
		this.jsonNode = jsonNode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}	

}
