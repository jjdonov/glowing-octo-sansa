package org.jjdonov.adintegration.ad.dao.impl;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 * We should check to see if DataStoreService is threadsafe...
 * 
 * Note: mvn clean install will erase the local datastore. User accounts
 * 		 will not be persisted locally between builds.
 * 
 * @author jjdonov
 *
 */
public abstract class BaseDAO {
	private static DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
	public static DatastoreService getDS() {
		return ds;
	}
	
}
