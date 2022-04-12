package net.revature.data;

import net.revature.models.Status;

public interface StatusDAO extends GenericDAO<Status> {

	public String getById(String status_name);
	public int getByStatus_name(int status_id);
	
	
}
