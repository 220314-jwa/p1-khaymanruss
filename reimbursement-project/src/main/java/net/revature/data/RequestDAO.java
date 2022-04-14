package net.revature.data;


import java.util.Date;

import net.revature.models.Request;

public interface RequestDAO extends GenericDAO<Request> {

	public Request getById(int requestId);
	//public int getByEmployee_id(int request_id);
	
	
}
