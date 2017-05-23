package play.tollboothcore.transactions;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Results;
import play.tollboothcore.dataObjects.ComcastChargesRequest;
import play.tollboothcore.dataObjects.ComcastProrateCalculationRequest;
import play.tollboothcore.dataObjects.EmmChargeRequest;
import play.tollboothcore.dataObjects.TollboothGateSubscribeRequest;
import play.tollboothcore.service.ChargeGatewayService;

@BodyParser.Of(BodyParser.Json.class)
@SuppressWarnings("static-access")
@Component
public class ChargeGatewayTranslator {
	
	private static final Logger LOG = LoggerFactory.getLogger(ChargeGatewayTranslator.class);

	@Inject
	private ChargeGatewayService chargeGatewayService;

	@Inject
	private Results results;

	FormFactory formFactory;

	@Inject
	public ChargeGatewayTranslator(FormFactory formFactory) {
		this.formFactory = formFactory;
	}

	public Result prorationCalculation(JsonNode json) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("********** Processing validations at transaction level for prorationCalculation Request --> "+json.toString());
		Form<ComcastProrateCalculationRequest> form = formFactory.form(ComcastProrateCalculationRequest.class)
				.bind(json);
		if (form.hasErrors()) {
			return results.badRequest("Invalid Prorate Calculation Request." + form.errors());
		}
		return results.ok(chargeGatewayService.prorationCalculation(json));

	}

	public Result oldCharge(JsonNode json) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("********** Processing validations at transaction level for oldCharge Request --> "+json.toString());
		Form<TollboothGateSubscribeRequest> form = formFactory.form(TollboothGateSubscribeRequest.class).bind(json);
		if (form.hasErrors()) {
			return results.badRequest("Invalid Charge Subscribe Request.");
		}
		return results.ok(chargeGatewayService.oldCharge(json));

	}

	public Result charge(JsonNode json) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("********** Processing validations at transaction level for Charge Request --> "+json.toString());
		Form<ComcastChargesRequest> form = formFactory.form(ComcastChargesRequest.class).bind(json);
		if (form.hasErrors()) {
			return results.badRequest("Invalid Charge Request." + form.errors());
		}
		ComcastChargesRequest comcastChargesRequest = Json.fromJson(json, ComcastChargesRequest.class);
		List<EmmChargeRequest> emmChargeRequestList = comcastChargesRequest.getTransactions();
		for (EmmChargeRequest emmChargeRequest : emmChargeRequestList) {
			JsonNode emmJson = Json.toJson(emmChargeRequest);
			LOG.info("********** Validating inner transaction object in Charge Request --> " + emmJson.toString());
			Form<EmmChargeRequest> emmform = formFactory.form(EmmChargeRequest.class).bind(emmJson);
			if (emmform.hasErrors()) {
				return results.badRequest("Invalid Transactions in Charge Request" + emmform.errors());
			}
		}
		return results.ok(chargeGatewayService.charge(json));
	}

}
