package org.jjdonov.adintegration.ad.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.jjdonov.adintegration.ad.dao.AccountDAO;
import org.jjdonov.adintegration.ad.model.events.ADEvent;
import org.jjdonov.adintegration.ad.model.events.Account;
import org.jjdonov.adintegration.ad.model.events.EventResult;
import org.jjdonov.adintegration.ad.model.events.SubscriptionEventTypes;
import org.jjdonov.adintegration.ad.model.vo.AccountVO;
import org.jjdonov.adintegration.ad.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Entity;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	AccountDAO accountDao;

	public List<AccountVO> getSomeAccounts() {
		List<AccountVO> acctList = new LinkedList<>();
		List<Entity> entityList =  accountDao.getSomeAccounts();
		for(Entity entity : entityList) {
			acctList.add(new AccountVO(entity));
		}
		return acctList;
	}

	/**
	 * A particularly ugly approach...
	 */
	public EventResult handleEvent(ADEvent event) {
		SubscriptionEventTypes type = SubscriptionEventTypes.valueOf(event
				.getType());
		switch (type) {
		case SUBSCRIPTION_ORDER:
			return handleOrder(event);
		case SUBSCRIPTION_CANCEL:
			return handleCancel(event);
		case SUBSCRIPTION_CHANGE:
			return handleChange(event);
		case SUBSCRIPTION_NOTICE:
			return handleNotice(event);
		default:
			return null;
		}
	}

	private EventResult handleOrder(ADEvent event) {
		String accountId = accountDao.putAccount(event);
		return buildEvent(true, accountId, "successful order");
	}

	private EventResult handleChange(ADEvent event) {
		Account account = event.getPayload().getAccount();
		return buildEvent(true, account.getAccountIdentifier(),
				"successful changel");
	}

	private EventResult handleCancel(ADEvent event) {
		Account account = event.getPayload().getAccount();
		boolean success = accountDao.deleteAccount(account
				.getAccountIdentifier());
		return buildEvent(success, account.getAccountIdentifier(), "");
	}

	private EventResult handleNotice(ADEvent event) {
		return buildEvent(true, "Dummy", "successful notice");
	}
	
	private EventResult buildEvent(boolean success, String acctId,
			String message) {
		EventResult result = new EventResult();
		result.setSuccesful(success);
		result.setAccountIdentifier(acctId);
		result.setMessage(message);
		return result;
	}

}
