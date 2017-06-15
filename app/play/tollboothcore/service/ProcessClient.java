package play.tollboothcore.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.tollboothcore.dataObjects.FutureResult;

public class ProcessClient {
	
	@Inject
	WSClient ws;

	private static final Logger LOG = LoggerFactory.getLogger(ProcessClient.class);

	public static void checkCompleted(CompletionStage<?> stage) {
		if (!stage.toCompletableFuture().isDone()) {
			throw new IllegalStateException("future was not completed");
		}
	}

	public FutureResult processPostForUrl(String url, JsonNode json) {
		LOG.info("********** Processing POST request to URL: "+url);
		CompletionStage<WSResponse> responsePromise = ws.url(url).post(json);
		CompletableFuture<WSResponse> future = responsePromise.toCompletableFuture();
		FutureResult futureResult = new FutureResult();
		int headerStatus;
		try {
			headerStatus = future.get().getStatus();
			futureResult.setStatus(headerStatus);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonNode jsonResponse = future.join().asJson();
		futureResult.setJsonNode(jsonResponse);
		LOG.info("********** Recieved response for  POST request: "+jsonResponse.toString());
		return futureResult;
	}

	
}
