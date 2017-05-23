package play.tollboothcore.service;

import com.fasterxml.jackson.databind.JsonNode;

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
	
	String charge(JsonNode json);

	
	
	
}
