package org.jjdonov.adintegration.ad.endpoints;

import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Logger;

import org.jjdonov.adintegration.ad.model.events.ADEvent;
import org.jjdonov.adintegration.ad.model.events.EventResult;
import org.jjdonov.adintegration.ad.model.events.builder.ADEventBuildder;
import org.jjdonov.adintegration.ad.model.events.builder.ADEventCreationException;
import org.jjdonov.adintegration.ad.service.SubscriptionService;
import org.jjdonov.adintegration.apis.AppDirectError;
import org.scribe.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles all the Event Integration Endpoints from AppDirect.
 * 
 * @author jjdonov
 */
@Controller
public class SubscriptionManagementEndpoint extends AbstractADEndpoint implements
		ADSubscriptionMgmtEventHandler {

	@Autowired
	private SubscriptionService subscriptionService;
	@Autowired
	private ADEventBuildder eventBuilder;

	private static Logger log = Logger.getLogger(SubscriptionManagementEndpoint.class.getName());
	
	private static final String eventUrlTemplate = "https://www.appdirect.com/api/integration/v1/events/%s";
	
	@RequestMapping(value = "/event", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody EventResult handleEvent(@RequestParam String token) throws ADEventCreationException {
		String tokenUrl = String.format(eventUrlTemplate, token);
		Response adResponse = sendSignedRequest(tokenUrl);
		if (adResponse.getCode() != 200) {
			log.warning("Unauthorized to access resource for token: " + token);
			return errorResult(AppDirectError.UNAUTHORIZED, "Failed to authorize with AppDirect");
		}
		Reader reader = new StringReader(adResponse.getBody());
		ADEvent event = null;
		event = eventBuilder.buildEvent(reader);
		return subscriptionService.handleEvent(event);
	}
	
	@ExceptionHandler(ADEventCreationException.class)
	public @ResponseBody EventResult handleExceptions(Exception e) {
		log.warning("Unable to create actionable event");
		return errorResult(AppDirectError.UNKNOWN_ERROR, e.getClass().toString());
	}
	
}
