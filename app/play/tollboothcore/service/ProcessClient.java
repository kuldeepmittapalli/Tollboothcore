package play.tollboothcore.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

public class ProcessClient {
	
	@Inject
	WSClient ws;

	private static final Logger LOG = LoggerFactory.getLogger(ProcessClient.class);

	public static void checkCompleted(CompletionStage<?> stage) {
		if (!stage.toCompletableFuture().isDone()) {
			throw new IllegalStateException("future was not completed");
		}
	}

	public String processPostForUrl(String url, JsonNode json) {
		LOG.info("********** Processing POST request to URL: "+url);
		CompletionStage<WSResponse> responsePromise = ws.url(url).post(json);
		CompletableFuture<WSResponse> future = responsePromise.toCompletableFuture();
		String jsonResponse = future.join().getBody();
		LOG.info("********** Recieved response for  POST request: "+jsonResponse);
		return jsonResponse;

	}

}
