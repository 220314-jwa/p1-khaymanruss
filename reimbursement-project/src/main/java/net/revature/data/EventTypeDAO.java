package net.revature.data;

import net.revature.models.EventType;

public interface EventTypeDAO extends GenericDAO<EventType>{

	public String getByEvent_type_id(String event_type_name);
	public int getByEvent_type_name(int event_type_id);
	
}
