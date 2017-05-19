package play.tollboothcore.service;

import com.fasterxml.jackson.databind.JsonNode;

import play.tollboothcore.dataObjects.TollboothGateSubscribeRequest;

/**
 * Interface for integrating with a Payment Gateway
 * 
 */

public interface ChargeGatewayService {

	
	
	/**
	 * need to add notes
	 * need to add notes
	 * @param request
	 * @return
	 */
	String oldCharge(JsonNode json);

	String prorationCalculation(JsonNode json);

	
	
	
}
