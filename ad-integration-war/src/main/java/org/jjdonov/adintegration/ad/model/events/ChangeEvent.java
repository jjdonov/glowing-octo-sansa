package org.jjdonov.adintegration.ad.model.events;

public class ChangeEvent extends ADEvent implements ActionableEvent {

	private ADEvent event;

	public ChangeEvent(ADEvent event) {
		this.event = event;
	}

	@Override
	public EventResult handleEvent() {
		// TODO Auto-generated method stub
		return null;
	}
}
