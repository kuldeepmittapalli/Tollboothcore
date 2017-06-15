/**
 * 
 */
package play.tollboothcore.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.ws.WSClient;
import play.tollboothcore.dataObjects.FutureResult;

/**
 * @author
 *
 */
@Component
public class ChargeGatewayServiceImpl implements ChargeGatewayService {

	@Inject
	WSClient ws;

	@Inject
	ProcessClient processClient;

	private static final Logger LOG = LoggerFactory.getLogger(ChargeGatewayServiceImpl.class);
	public static final String TB_EMM_SERVICE_BEAN_ID = "TollboothEMMPaymentGateway";


	@Override
	public FutureResult oldCharge(JsonNode json) {
		LOG.info("********** Processing Charge started from TollboothCore to Tollbooth EMM for " + json.toString());
		String url = "http://example.com";
		LOG.info("********** Processing POST request to Tollbooth EMM");
		return processClient.processPostForUrl(url, json);

	}

	@Override
	public FutureResult prorationCalculation(JsonNode json) {
		LOG.info("********** Processing Proration calculation request from TollboothCore to Tollbooth EMM for " + json.toString());
		String url = "http://localhost:8081/tollboothemm/api/watchable/stb/products/prorationCalculation";
		LOG.info("********** Processing ProrationCalculation POST request to Tollbooth EMM");
		return processClient.processPostForUrl(url, json);

	}

	@Override
	public FutureResult charge(JsonNode json) {
		LOG.info("********** Processing charge request from TollboothCore to Tollbooth EMM for " + json.toString());
		String url = "http://localhost:8081/tollboothemm/api/watchable/stb/products/charge";
		LOG.info("********** Processing Charge POST request to Tollbooth EMM");
		return processClient.processPostForUrl(url, json);

	}

}
