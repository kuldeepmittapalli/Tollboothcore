package play.tollboothcore.controllers;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.tollboothcore.dataObjects.ComcastProrateCalculationRequest;
import play.tollboothcore.dataObjects.TollboothGateSubscribeRequest;
import play.tollboothcore.service.ChargeGatewayService;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
@SuppressWarnings({ "deprecation" })
@Component
public class TollboothGateController extends Controller {

	@Inject
	private ChargeGatewayService chargeGatewayServiceImpl;

	public TollboothGateController() {
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result oldCharge(String appId, String appType) throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		Form<TollboothGateSubscribeRequest> form = Form.form(TollboothGateSubscribeRequest.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest("Invalid Charge Subscribe Request.");
		}
		return ok(chargeGatewayServiceImpl.oldCharge(json));

	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result prorationCalculation(String appId, String appType)
			throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		System.out.println("chargeGatewayService is "+chargeGatewayServiceImpl);
		Form<ComcastProrateCalculationRequest> form = Form.form(ComcastProrateCalculationRequest.class)
				.bindFromRequest();
		return ok(chargeGatewayServiceImpl.prorationCalculation(json));

	}

}
