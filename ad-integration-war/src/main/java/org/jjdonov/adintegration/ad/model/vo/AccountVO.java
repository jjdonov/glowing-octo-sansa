package org.jjdonov.adintegration.ad.model.vo;

import com.google.appengine.api.datastore.Entity;

/**
 * As mentioned in the AccountDAO, in a real app we would be better off with a
 * different means of persistence. This class is simply a convenience wrapper
 * for Entities modeling Accounts.
 * 
 * @author jjdonov
 *
 */
public class AccountVO {

	private String fullName;

	public AccountVO(Entity accountEntity) {
		this.fullName = (String) accountEntity.getProperty("fullName");
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



}
