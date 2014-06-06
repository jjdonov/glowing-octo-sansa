package org.jjdonov.adintegration.apis;

/**
 * Error codes supported by the AppDirect API. When returning
 * an EventResult to AppDirect with success = false, one of these
 * should be provided.
 * @author jjdonov
 *
 */
public class AppDirectError {
	public static String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
	public static String USER_NOT_FOUND = "USER_NOT_FOUND";
	public static String ACCOUNT_NOT_FOUND = "ACCOUNT_NOT_FOUND";
	public static String MAX_USERS_REACHED = "MAX_USERS_REACHED";
	public static String UNAUTHORIZED = "UNAUTHORIZED";
	public static String OPERATION_CANCELED = "OPERATION_CANCELED";
	public static String CONFIGURATION_ERROR = "CONFIGURATION_ERROR";
	public static String INVALID_RESPONSE = "INVALID_RESPONSE";
	public static String UNKNOWN_ERROR = "UNKNOWN_ERROR";
}
