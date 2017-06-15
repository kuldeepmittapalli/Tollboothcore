package play.tollboothcore.service;

import com.fasterxml.jackson.databind.JsonNode;

import play.tollboothcore.dataObjects.FutureResult;

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
	FutureResult oldCharge(JsonNode json);

	FutureResult prorationCalculation(JsonNode json);
	
	FutureResult charge(JsonNode json);

	
	
	
}
