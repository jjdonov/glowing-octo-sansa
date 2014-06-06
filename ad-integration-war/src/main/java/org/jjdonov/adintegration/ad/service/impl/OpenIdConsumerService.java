package org.jjdonov.adintegration.ad.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service layer to Handle OpenId Auth
 * @author jjdonov
 *
 */
@Service
public class OpenIdConsumerService {

	Logger log = Logger.getLogger(OpenIdConsumerService.class.getName());

	@Autowired
	ConsumerManager consumerManager;

	/**
	 * OpenID related vars. For the full list of URI's for OpendId Attributes
	 * 
	 * @see {@link http://info.appdirect.com/developers/docs/event_references/user_attributes}
	 */
	private static final String OPEN_ID_DISC = "openid-disc";
	private static final String AD_OPEN_ID_NS_AX = "http://openid.net/srv/ax/1.0";
	private static final String AD_FULL_NAME_URI = "http://axschema.org/namePerson";
	private static final String AD_EMAIL_URI = "http://axschema.org/contact/email";

	public String authRequest(String openId, HttpServletRequest request,
			HttpServletResponse response) throws DiscoveryException,
			MessageException, ConsumerException, IOException {
		String returnToUrl = request.getRequestURL().append("/verify")
				.toString();
		@SuppressWarnings("rawtypes")
		List discoveries = consumerManager.discover(openId);
		DiscoveryInformation discovered = consumerManager
				.associate(discoveries);
		request.getSession().setAttribute(OPEN_ID_DISC, discovered);
		AuthRequest authRequest = consumerManager.authenticate(discovered,
				returnToUrl);
		FetchRequest fetch = FetchRequest.createFetchRequest();
		fetch.addAttribute("fullName", AD_FULL_NAME_URI, true);
		fetch.addAttribute("email", AD_EMAIL_URI, true);
		authRequest.addExtension(fetch);
		response.sendRedirect(authRequest.getDestinationUrl(true));
		return null;
	}

	public String verifyAndPutInSession(HttpServletRequest request) {
		String resultingRedirect = "accessdenied";
		try {
			VerificationResult verification = verifyResponse(request);
			resultingRedirect = handleVerifiedId(request, verification);
		} catch (OpenIdException e) {
			log.log(Level.WARNING, "Exception when verifying request");
		}
		return resultingRedirect;
	}

	private VerificationResult verifyResponse(HttpServletRequest request)
			throws OpenIdException {
		VerificationResult verification = null;
		ParameterList openIdResponse = new ParameterList(
				request.getParameterMap());
		DiscoveryInformation discovered = (DiscoveryInformation) request
				.getSession().getAttribute(OPEN_ID_DISC);
		StringBuffer recievingUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			recievingUrl.append("?").append(request.getQueryString());
		}
		try {
			verification = consumerManager.verify(recievingUrl.toString(),
					openIdResponse, discovered);

		} catch (MessageException | DiscoveryException | AssociationException e) {
			log.log(Level.WARNING, "Exception when verifying openId response",
					e);
			throw new OpenIdException("Unable to verify response.");
		}
		return verification;
	}

	private String handleVerifiedId(HttpServletRequest request,
			VerificationResult verification)  {
		String result = "accessdenied"; // assume failure
		Identifier verifiedId = verification.getVerifiedId();
		if (verifiedId != null) {
			AuthSuccess authSuccess = (AuthSuccess) verification
					.getAuthResponse();
			if (authSuccess.hasExtension(AD_OPEN_ID_NS_AX)) {
				try {
					FetchResponse fetchResp = (FetchResponse) authSuccess
							.getExtension(AD_OPEN_ID_NS_AX);
					String fullName = fetchResp.getAttributeValue("fullName");
					String email = fetchResp.getAttributeValue("email");
					request.getSession().setAttribute("ad_full_name", fullName);
					request.getSession().setAttribute("ad_email_addr", email);
				} catch (MessageException e) {
					log.warning("Failed to get attributes from OpenId");
				}
			}
			/*
			 * Authenticating w/ OpenId was successful, lets redirect the user
			 * to the home page
			 */
			return "index";
		}
		return result;
	}

}
