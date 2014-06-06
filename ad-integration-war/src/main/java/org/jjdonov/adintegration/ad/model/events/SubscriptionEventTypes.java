package org.jjdonov.adintegration.ad.model.events;

public enum SubscriptionEventTypes {
	SUBSCRIPTION_ORDER ("SUBSCRIPTION_ORDER"),
	SUBSCRIPTION_CHANGE ("SUBSCRIPTION_CHANGE"),
	SUBSCRIPTION_CANCEL ("SUBSCRIPTION_CANCEL"),
	SUBSCRIPTION_NOTICE ("SUBSCRIPTION_NOTICE"),
	USER_ASSIGNMENT ("USER_ASSIGNMENT"),
	USER_UNASSIGNMENT ("USER_UNASSIGNMENT");
	
	private String type;
	
	private SubscriptionEventTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
