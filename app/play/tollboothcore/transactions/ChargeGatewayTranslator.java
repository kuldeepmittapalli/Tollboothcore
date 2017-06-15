package play.tollboothcore.transactions;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpStatus;
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
import play.tollboothcore.dataObjects.ComcastChargesResponse;
import play.tollboothcore.dataObjects.ComcastProrateCalculationRequest;
import play.tollboothcore.dataObjects.ComcastProrateCalculationResponse;
import play.tollboothcore.dataObjects.EMMChargeResponse;
import play.tollboothcore.dataObjects.EMMGroupChargesRequest;
import play.tollboothcore.dataObjects.EMMGroupChargesResponse;
import play.tollboothcore.dataObjects.EmmChargeRequest;
import play.tollboothcore.dataObjects.FutureResult;
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
		LOG.info("********** Processing validations at transaction level for prorationCalculation Request --> "+ json.toString());
		Form<ComcastProrateCalculationRequest> form = formFactory.form(ComcastProrateCalculationRequest.class).bind(json);
		if (form.hasErrors()) {
			return results.badRequest("Invalid Prorate Calculation Request." + form.errors());
		}
		LOG.info("********** Adding Description for prorationCalculation Request --> " + json.toString());
		ComcastProrateCalculationRequest request = Json.fromJson(json, ComcastProrateCalculationRequest.class);
		request.setDescription("Prorate Calculation for Transaction:" + request.getTransactionID());
		JsonNode newJson = Json.toJson(request);

		FutureResult futureResult = chargeGatewayService.prorationCalculation(newJson);
		int status = futureResult.getStatus();
		JsonNode responseJson = futureResult.getJsonNode();
		if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {
			ComcastProrateCalculationResponse response = Json.fromJson(json, ComcastProrateCalculationResponse.class);
			if (response.getEmmServiceResponse() != null
					&& (HttpStatus.SC_OK == response.getEmmServiceResponse().getResponseCode())) {
				response.setSuccess(true);
			} else {
				response.setSuccess(false);
			}
			JsonNode newResponseJson = Json.toJson(response);
			LOG.info("********** Successful prorationCalculation response --> "+newResponseJson.toString());
			return results.ok(newResponseJson);
		} else if (status == HttpStatus.SC_BAD_REQUEST) {
			return results.badRequest(responseJson);
		} else if (status == HttpStatus.SC_UNAUTHORIZED) {
			return results.unauthorized(responseJson);
		} else {
			return results.internalServerError(responseJson);
		}
	}

	public Result oldCharge(JsonNode json) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("********** Processing validations at transaction level for oldCharge Request --> "+json.toString());
		Form<TollboothGateSubscribeRequest> form = formFactory.form(TollboothGateSubscribeRequest.class).bind(json);
		if (form.hasErrors()) {
			return results.badRequest("Invalid Charge Subscribe Request.");
		}
		FutureResult futureResult = chargeGatewayService.prorationCalculation(json);
		int status = futureResult.getStatus();
		if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {
			return results.ok(futureResult.getJsonNode());
		} else if (status == HttpStatus.SC_BAD_REQUEST) {
			return results.badRequest(futureResult.getJsonNode());
		} else if (status == HttpStatus.SC_UNAUTHORIZED) {
			return results.unauthorized(futureResult.getJsonNode());
		} else {
			return results.internalServerError(futureResult.getJsonNode());
		}
	}

	public Result charge(JsonNode json) throws JsonParseException, JsonMappingException, IOException {
		LOG.info("********** Processing validations at transaction level for Charge Request --> " + json.toString());
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
			emmChargeRequest.setChargeTokenID(emmChargeRequest.getTransactionID());
		}
		LOG.info("********** Added ChargeTokenID and convering chargeRequest for EMM ");
		EMMGroupChargesRequest chargeRequest = new EMMGroupChargesRequest();
		chargeRequest.setTransaction(emmChargeRequestList);
		JsonNode newRequestJson = Json.toJson(chargeRequest);
		
		FutureResult futureResult = chargeGatewayService.prorationCalculation(newRequestJson);
		int status = futureResult.getStatus();
		JsonNode responseJson = futureResult.getJsonNode();
		
		if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {
			EMMGroupChargesResponse emmGroupChargesResponse = Json.fromJson(json, EMMGroupChargesResponse.class);
			ComcastChargesResponse comcastChargesResponse = new ComcastChargesResponse();
			
			if (emmGroupChargesResponse != null) {
				List<EMMChargeResponse> emmChargeResponseList = emmGroupChargesResponse.getChargeSubscriberResponses();
				for (EMMChargeResponse emmChargeResponse : emmChargeResponseList) {
					if (emmChargeResponse != null && emmChargeResponse.getEmmServiceResponse() != null
							&& HttpStatus.SC_OK == (emmChargeResponse.getEmmServiceResponse().getResponseCode())) {
						emmChargeResponse.setSuccess(true);
					} else if (emmChargeResponse != null) {
						emmChargeResponse.setSuccess(false);
					}
				}
				comcastChargesResponse.setTransactions(emmGroupChargesResponse.getChargeSubscriberResponses());
			}
			
			if (emmGroupChargesResponse != null && HttpStatus.SC_OK == (emmGroupChargesResponse.getResponseCode())) {
				comcastChargesResponse.setSuccess(true);
			} else {
				comcastChargesResponse.setSuccess(false);
			}
			JsonNode newResponseJson = Json.toJson(comcastChargesResponse);
			LOG.info("********** Successful Charge response --> "+newResponseJson.toString());
			return results.ok(newResponseJson);
		} else if (status == HttpStatus.SC_BAD_REQUEST) {
			return results.badRequest(responseJson);
		} else if (status == HttpStatus.SC_UNAUTHORIZED) {
			return results.unauthorized(responseJson);
		} else {
			return results.internalServerError(responseJson);
		}
	}

}
