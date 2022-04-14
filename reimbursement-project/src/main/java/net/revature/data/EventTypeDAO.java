package net.revature.data;

import net.revature.models.EventType;

public interface EventTypeDAO extends GenericDAO<EventType>{

	public String getByEventTypeId(String eventTypeName);
	public int getByEventTypeName(int eventTypeId);
	
}
