package org.jjdonov.adintegration.apis;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Since AppDirect is using 2-legged OAuth,
 * these methods are irrelevant. This is a dummy
 * class for use in the OAuthServiceBuilder
 * provided by Scribe
 * 
 * @author jjdonov
 *
 */
public class AppDirectApi extends DefaultApi10a {

	@Override
	public String getAccessTokenEndpoint() {
		return null;
	}

	@Override
	public String getAuthorizationUrl(Token arg0) {
		return null;
	}

	@Override
	public String getRequestTokenEndpoint() {
		return null;
	}

}
