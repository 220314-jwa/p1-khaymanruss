package net.revature.models;

import java.util.Objects;

public class EventType {


	
	private int eventTypeId;
	private String eventTypeName;
	
	public EventType() {
		eventTypeId = 0;
		eventTypeName = "";
	}
	public int getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	public String getEventTypeName() {
		return eventTypeName;
	}
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}
	
	@Override
	public String toString() {
		return "EventType [eventTypeId=" + eventTypeId + ", eventTypeName=" + eventTypeName + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(eventTypeId, eventTypeName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventType other = (EventType) obj;
		return eventTypeId == other.eventTypeId && Objects.equals(eventTypeName, other.eventTypeName);
	}
}
