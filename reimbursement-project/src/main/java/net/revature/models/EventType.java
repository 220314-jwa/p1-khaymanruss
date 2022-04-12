package net.revature.models;

import java.util.Objects;

public class EventType {


	private int event_type_id;
	private String event_type_name;
	
	public EventType() {
		event_type_id = 0;
		event_type_name = "";
	}
	public int getEvent_type_id() {
		return event_type_id;
	}
	public void setEvent_type_id(int event_type_id) {
		this.event_type_id = event_type_id;
	}
	public String getEvent_type_name() {
		return event_type_name;
	}
	public void setEvent_type_name(String event_type_name) {
		this.event_type_name = event_type_name;
	}
	
	@Override
	public String toString() {
		return "EventType [event_type_id=" + event_type_id + ", event_type_name=" + event_type_name + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(event_type_id, event_type_name);
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
		return event_type_id == other.event_type_id && Objects.equals(event_type_name, other.event_type_name);
	}
}
