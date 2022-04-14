package net.revature.data;

import net.revature.models.Status;

public interface StatusDAO extends GenericDAO<Status> {

	public String getById(String statusName);
	public int getByStatusName(int statusId);
	
	
}
