package org.jjdonov.adintegration.ad.dao;

import java.util.List;

import org.jjdonov.adintegration.ad.model.events.ADEvent;

import com.google.appengine.api.datastore.Entity;

public interface AccountDAO {

	public String putAccount(ADEvent event);

	public boolean deleteAccount(String accountId);

	public List<Entity> getSomeAccounts();

}
