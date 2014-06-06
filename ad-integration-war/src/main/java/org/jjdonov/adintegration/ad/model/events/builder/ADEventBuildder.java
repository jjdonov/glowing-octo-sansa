package org.jjdonov.adintegration.ad.model.events.builder;

import java.io.Reader;

import org.jjdonov.adintegration.ad.model.events.ADEvent;

public interface ADEventBuildder {

//	public ActionableEvent buildEvent(Reader reader) throws ADEventCreationException;
	public ADEvent buildEvent(Reader reader) throws ADEventCreationException;
	
}
