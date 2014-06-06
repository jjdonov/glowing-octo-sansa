package org.jjdonov.adintegration.ad.endpoints;

import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.jjdonov.adintegration.ad.model.events.EventResult;
import org.jjdonov.adintegration.apis.AppDirectApi;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;

/**
 * As we add more Endpoints for AppDirect, we don't want to re-implement 
 * the OAtuh process for each.
 * @author jjdonov
 *
 */
public class AbstractADEndpoint {

	private static Logger log = Logger.getLogger(AbstractADEndpoint.class.getName());

	@Value("${oauth.key}")
	private String CONSUMER_KEY;
	@Value("${oauth.secret}")
	private String CONSUMER_SECRET;


	protected EventResult errorResult(String errorType, String errorMsg) {
		EventResult result = new EventResult();
		result.setSuccesful(false);
		result.setErrorCode(errorType);
		result.setMessage(errorMsg);
		return result;
	}

	protected Response sendSignedRequest(String url) {
		log.info("Signing request with " + CONSUMER_KEY + ":" + CONSUMER_SECRET
				+ "to url " + url);
		OAuthService service = new ServiceBuilder()
				.provider(AppDirectApi.class).apiKey(CONSUMER_KEY)
				.apiSecret(CONSUMER_SECRET).build();
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		Token accessToken = new Token("", "");
		service.signRequest(accessToken, request);
		log.info("after signing the oauth headers are:");
		logHeaders(request.getHeaders());
		return request.send();
	}

	protected void logHeaders(Map<String, String> headerMap) {
		log.info("printing headers");
		for (Entry<String, String> entry : headerMap.entrySet()) {
			log.info("Header->" + entry.getKey() + ":" + entry.getValue());
		}
	}

	/* Getters and Setters */

	public String getCONSUMER_KEY() {
		return CONSUMER_KEY;
	}

	public void setCONSUMER_KEY(String consumer_key) {
		CONSUMER_KEY = consumer_key;
	}

	public String getCONSUMER_SECRET() {
		return CONSUMER_SECRET;
	}

	public void setCONSUMER_SECRET(String consumer_secret) {
		CONSUMER_SECRET = consumer_secret;
	}
}
