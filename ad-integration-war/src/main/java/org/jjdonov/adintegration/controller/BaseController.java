package org.jjdonov.adintegration.controller;

import java.util.List;

import org.jjdonov.adintegration.ad.model.vo.AccountVO;
import org.jjdonov.adintegration.ad.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Another simple controller to help render index.jsp
 * Build's model for the page. As of now only a list of user's 
 * for which we've recieved and persisted Order Events for.
 *  
 * @author jjdonov
 *
 */
@Controller
public class BaseController {

	@Autowired
	SubscriptionService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String landingPage(ModelMap model) {
		List<AccountVO> accounts = service.getSomeAccounts();
		model.addAttribute("message", "John's ADIntegration Dummy App");
		model.put("userList", accounts);
		return "index";
	}
}
