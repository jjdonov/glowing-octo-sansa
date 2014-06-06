package org.jjdonov.adintegration.ad.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.jjdonov.adintegration.ad.dao.AccountDAO;
import org.jjdonov.adintegration.ad.model.events.ADEvent;
import org.jjdonov.adintegration.ad.model.events.Company;
import org.jjdonov.adintegration.ad.model.events.Creator;
import org.jjdonov.adintegration.ad.model.events.Order;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * This is a bare bones implementation of a dao. For a real app, JPA/JDO or
 * other GAE supported persistence should be leveraged.
 * 
 * @author jjdonov
 *
 */
@Repository
public class AccountDAOImpl extends BaseDAO implements AccountDAO {
	DatastoreService service;

	private static Logger log = Logger.getLogger(AccountDAOImpl.class.getName());

	public String putAccount(ADEvent event) {
		Key accountKey = KeyFactory.createKey("Account", event.getPayload()
				.getCompany().getUuid());
		Creator creator = event.getCreator();
		Order order = event.getPayload().getOrder();
		Company company = event.getPayload().getCompany();
		Entity account = new Entity("Account", accountKey);
		account.setProperty("fullName", creator.getFirstName() + " " + creator.getLastName());
		account.setProperty("date", new Date());
		account.setProperty("editionCode", order.getEditionCode());
		account.setProperty("pricingDuration", order.getPricingDuration());
		account.setProperty("companyUuid", company.getUuid());
		Key savedKey = getDS().put(account);
		return KeyFactory.keyToString(savedKey);
	}

	public boolean deleteAccount(String accountId) {
		boolean success = true;
		try {
			Key key = KeyFactory.stringToKey(accountId);
			getDS().delete(key);
		} catch (IllegalArgumentException e) {
			log.warning("Failed to cancel subscription. Could not parse AccountId"
					+ accountId);
			success = false;
		}
		return success;
	}

	public List<Entity> getSomeAccounts() {
		Query gaQuery = new Query("Account");
		PreparedQuery pq = getDS().prepare(gaQuery);
		List<Entity> accountList = pq.asList(FetchOptions.Builder
				.withDefaults());
		return accountList;
	}

}
