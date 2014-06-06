package org.jjdonov.adintegration.ad.endpoints;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	private static Logger log = Logger.getLogger(AuthInterceptor.class.getName());

	private static final String oauth_nonce = "oauth_nonce";
	private static final String oauth_timestamp = "oauth_timestamp";
	private static final String oauth_consumer_key = "oauth_consumer_key";
	private static final String oauth_consumer_secret = "oauth_consumer_secret";
	private static final String oauth_signature_method = "oauth_signature_method";
	private static final String oauth_version = "oauth_version";
	private static final String oauth_signature = "oauth_signature";

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.info("Validating OAuth Here");
		printHeaders(request);
		return true;
//		if(isValid(request)) {
//			return true;
//		} else {
//			response.sendError(401); // UNAUTHORIZED
//			return false;
//		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private boolean isValid(HttpServletRequest request) {
		Map<String, String> hMap = getHeaderMap(request);
		if(hasAllOAuthHeaders(hMap)) {
			
		}
		return false;
	}
	

	private boolean hasAllOAuthHeaders(Map<String, String> hMap) {
		return hMap.containsKey(oauth_nonce)
				&& hMap.containsKey(oauth_timestamp)
				&& hMap.containsKey(oauth_consumer_key)
				&& hMap.containsKey(oauth_signature_method)
				&& hMap.containsKey(oauth_consumer_secret)
				&& hMap.containsKey(oauth_version)
				&& hMap.containsKey(oauth_signature);
	}

	private Map<String, String> getHeaderMap(HttpServletRequest request) {
		Enumeration<String> hNames = request.getHeaderNames();
		Map<String, String> hMap = new HashMap<>();
		String header;
		while (hNames.hasMoreElements()) {
			header = hNames.nextElement();
			hMap.put(header, request.getHeader(header));
		}
		return hMap;
	}
	
	private void printHeaders(HttpServletRequest request) {
		Enumeration<String> hNames = request.getHeaderNames();
		log.info("header from request: ");
		while (hNames.hasMoreElements()) {
			log.info(hNames.nextElement());
		}
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object Handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
	}

}
