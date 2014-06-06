package org.jjdonov.adintegration.ad.endpoints;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jjdonov.adintegration.ad.service.impl.OpenIdConsumerService;
import org.jjdonov.adintegration.ad.service.impl.OpenIdException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.message.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Simple Controller to handle the login.jsp page and populate w/ appropriate
 * information on error or login failure.
 * 
 * 
 * @author jjdonov
 *
 */
@Controller
@RequestMapping("/auth")
public class LoginLogoutController extends AbstractADEndpoint {

	@Autowired
	OpenIdConsumerService consumerService;

	private static Logger log = Logger.getLogger(LoginLogoutController.class
			.getName());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {
		log.info("Received request to show login page");
		if (error == true) {
			model.put("errorMsg", "Invalid username or password.");
		}
		return "login";
	}

	@RequestMapping(value = "/ad/login", method = RequestMethod.GET)
	public String doLogin(@RequestParam String openId,
			HttpServletRequest request, HttpServletResponse response) {
		log.info("Received request from AppDirect to log in :" + openId);
		try {
			consumerService.authRequest(openId, request, response);
		} catch (DiscoveryException | MessageException | ConsumerException
				| IOException e) {
			log.log(Level.WARNING,
					"Exception when authenticating via controller from AppDirect");
		}
		return "login";
	}

	@RequestMapping(value = "/ad/login/verify", method = RequestMethod.GET)
	public String verifyLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "accessdenied";
		log.info("Received recieved back from AppDirect to log verify user");
		result = consumerService.verifyAndPutInSession(request);
		return result;
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getAccessDeniedPage() {
		return "accessdenied";
	}

}
