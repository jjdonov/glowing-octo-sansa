package org.jjdonov.adintegration.ad.endpoints;

import org.jjdonov.adintegration.ad.model.events.EventResult;
import org.jjdonov.adintegration.ad.model.events.builder.ADEventCreationException;

public interface ADSubscriptionMgmtEventHandler {

	public EventResult handleEvent(String url) throws ADEventCreationException;

}
