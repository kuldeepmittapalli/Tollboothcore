package play.tollboothcore.controllers;

import java.io.IOException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.tollboothcore.transactions.ChargeGatewayTranslator;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */

@BodyParser.Of(BodyParser.Json.class)
@Component
public class TollboothGateController extends Controller {
	private static final Logger LOG = LoggerFactory.getLogger(TollboothGateController.class);
	
	ChargeGatewayTranslator chargeGatewayTranslator;
	
	@Inject
	public TollboothGateController(ChargeGatewayTranslator chargeGatewayTranslator) {
		this.chargeGatewayTranslator = chargeGatewayTranslator;
	}
	
	public Result oldCharge(String appId, String appType) throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		LOG.info("********** Recieved oldCharge Request at Controller Level. Calling Transalator to process request -->"+json.toString());
		return chargeGatewayTranslator.oldCharge(json);

	}

	public Result prorationCalculation(String appId, String appType)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		LOG.info("********** Recieved prorationCalculation Request at Controller Level. Calling Transalator to process request -->"+json.toString());
		return chargeGatewayTranslator.prorationCalculation(json);

	}
	
	public Result charge(String appId, String appType) throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		LOG.info("********** Recieved Charge Request at Controller Level. Calling Transalator to process request -->"+json.toString());
		return chargeGatewayTranslator.charge(json);

	}

}
