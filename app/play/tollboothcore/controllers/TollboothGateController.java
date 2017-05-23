package play.tollboothcore.controllers;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.tollboothcore.dataObjects.ComcastChargesRequest;
import play.tollboothcore.dataObjects.ComcastProrateCalculationRequest;
import play.tollboothcore.dataObjects.EmmChargeRequest;
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
	
	
	FormFactory formFactory;
	
	@Inject
	public TollboothGateController(FormFactory formFactory) {
		this.formFactory = formFactory;
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
		Form<ComcastProrateCalculationRequest> form = formFactory.form(ComcastProrateCalculationRequest.class)
				.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest("Invalid Prorate Calculation Request." +form.errors());
		}
		return ok(chargeGatewayServiceImpl.prorationCalculation(json));

	}
	
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result charge(String appId, String appType) throws JsonParseException, JsonMappingException, IOException {
		JsonNode json = request().body().asJson();
		Form<ComcastChargesRequest> form = formFactory.form(ComcastChargesRequest.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest("Invalid Charge Request."+form.errors());
		}
		ComcastChargesRequest comcastChargesRequest = Json.fromJson(json, ComcastChargesRequest.class);
		List<EmmChargeRequest> emmChargeRequestList = comcastChargesRequest.getTransactions();
		for (EmmChargeRequest emmChargeRequest : emmChargeRequestList) {
			JsonNode emmJson = Json.toJson(emmChargeRequest);
			System.out.println(" emmjson is  "+emmJson.toString());
			Form<EmmChargeRequest> emmform = formFactory.form(EmmChargeRequest.class).bind(emmJson);
			if (emmform.hasErrors()) {
				return badRequest("Invalid Charge Request..."+emmform.errors());
			}
		}

		return ok(chargeGatewayServiceImpl.charge(json));

	}

}
