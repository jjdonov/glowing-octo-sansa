package org.jjdonov.adintegration.ad.model.events;

import org.jjdonov.adintegration.ad.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderEvent extends ADEvent implements ActionableEvent{
	
	@Autowired
	SubscriptionService service;
	
	private ADEvent event;
	
	public OrderEvent(ADEvent event) {
		this.event = event;
	}
	
	public EventResult handleEvent() {
		return service.handleEvent(this);
	}
	
	
}
