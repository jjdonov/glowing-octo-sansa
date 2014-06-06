package org.jjdonov.adintegration.ad.service;


import java.util.List;

import org.jjdonov.adintegration.ad.model.events.ADEvent;
import org.jjdonov.adintegration.ad.model.events.EventResult;
import org.jjdonov.adintegration.ad.model.vo.AccountVO;

public interface SubscriptionService {
	
	public EventResult handleEvent(ADEvent event);
	public List<AccountVO> getSomeAccounts();
	
}
