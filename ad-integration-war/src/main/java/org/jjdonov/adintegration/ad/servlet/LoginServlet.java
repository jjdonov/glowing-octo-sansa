package org.jjdonov.adintegration.ad.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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

/**
 * Servlet to handle the authentication with OpenId via AppDirect.
 * This servlet only works for "form style" login (e.g. what is provided on login.jsp)
 * 
 * @see org.jjdonov.adintegration.ad.endpoints.LoginLogoutController on how to handle
 * the automatic login when launching the application from AppDirect "My Apps".
 * 
 * @author jjdonov
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class.getClass().getName());

	@SuppressWarnings("unused")
	private ServletContext context;
	private ConsumerManager manager;
	private static final String OPEN_ID_DISC = "openid-disc";
	private static final String AD_OPEN_ID_NS_AX = "http://openid.net/srv/ax/1.0";
	private static final String AD_FULL_NAME_URI = "http://axschema.org/namePerson";
	private static final String AD_EMAIL_URI = "http://axschema.org/contact/email";

	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("initializing servlet");
		super.init(config);
		manager = new ConsumerManager();
		context = config.getServletContext();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Authenticating openId....");
		String openId = request.getParameter("openId");
		log.info("authenticating for: " + openId);
		try {
			authRequest(openId, request, response);
		} catch (DiscoveryException | MessageException | ConsumerException e) {
			log.log(Level.WARNING, "Exception when trying to authorize via OpenId", e);
			response.sendRedirect("/auth/login?error=true");
		}
	}

	private String authRequest(String openId, HttpServletRequest request,
			HttpServletResponse response) throws DiscoveryException,
			MessageException, ConsumerException, IOException {
		String returnToUrl = request.getRequestURL().toString();
		@SuppressWarnings("rawtypes")
		List discoveries = manager.discover(openId);
		DiscoveryInformation discovered = manager.associate(discoveries);
		request.getSession().setAttribute(OPEN_ID_DISC, discovered);
		AuthRequest authRequest = manager.authenticate(discovered, returnToUrl);
		FetchRequest fetch = FetchRequest.createFetchRequest();
		fetch.addAttribute("fullName", AD_FULL_NAME_URI, true);
		fetch.addAttribute("email", AD_EMAIL_URI, true);
		authRequest.addExtension(fetch);
		response.sendRedirect(authRequest.getDestinationUrl(true));
		return null;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("verifying response...");
		VerificationResult verification = verifyResponse(request);
		Identifier verifiedId = verification.getVerifiedId();
		handleVerifiedId(request, response, verification, verifiedId);
	}

	public VerificationResult verifyResponse(HttpServletRequest request) {
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
			verification = manager.verify(recievingUrl.toString(),
					openIdResponse, discovered);

		} catch (MessageException | DiscoveryException | AssociationException e) {
			log.log(Level.WARNING,"Exception when verifying openId response", e);
		}
		return verification;
	}

	private void handleVerifiedId(HttpServletRequest request,
			HttpServletResponse response, VerificationResult verification,
			Identifier verifiedId) throws IOException, ServletException {
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
					//log.warning("Failed to get attributes from AD OpenId");
					log.warning("Failed to get attributes from OpenId");
				}
			}
			/*
			 * Authenticating w/ OpenId was successful, lets redirect the user
			 * to the home page
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			/*
			 * Authenticating w/ OpenId was unsuccessful, lets redirect the user
			 * to a login page where they can try to present their OpenId again
			 */
			response.sendRedirect("/auth/login?error=true");
		}
	}

}
