/**
 * 
 */
package play.tollboothcore.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

/**
 * @author
 *
 */
@Service
public class ChargeGatewayServiceImpl implements ChargeGatewayService {

	@Inject
	WSClient ws;

	private static final Logger LOG = LoggerFactory.getLogger(ChargeGatewayServiceImpl.class);
	public static final String TB_EMM_SERVICE_BEAN_ID = "TollboothEMMPaymentGateway";

	public static void checkCompleted(CompletionStage<?> stage) {
		if (!stage.toCompletableFuture().isDone()) {
			throw new IllegalStateException("future was not completed");
		}
	}

	@Override
	public String oldCharge(JsonNode json) {
		LOG.info("Processing Charge started from TollboothCore to Tollbooth EMM for " + json.toString());
		String url = "http://example.com";
		CompletionStage<WSResponse> responsePromise = ws.url(url).post(json);
		CompletableFuture<WSResponse> future = responsePromise.toCompletableFuture();
		LOG.info("Response from Tollbooth EMM for Charge --> " + future.join().getBody());
		String jsonResponse = future.join().getBody();
		return jsonResponse;

	}
	
	
	@Override
	public String prorationCalculation(JsonNode json) {
		LOG.info("Processing Proration calculation request from TollboothCore to Tollbooth EMM for " + json.toString());
		String url = "http://localhost:8081/tollboothemm/api/watchable/stb/products/prorationCalculation";
		CompletionStage<WSResponse> responsePromise = ws.url(url).post(json);
		CompletableFuture<WSResponse> future = responsePromise.toCompletableFuture();
		LOG.info("Response from Tollbooth EMM for Proration calculation -->" + future.join().getBody());
		String jsonResponse = future.join().getBody();
		return jsonResponse;

	}

}
