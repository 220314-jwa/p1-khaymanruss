package net.revature.services;

import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.RequestAlreadySubmittedException;

import net.revature.models.Employee;
import net.revature.models.Request;
import net.revature.models.Status;

public interface UserService {

	public Employee logIn(String username, String password) throws IncorrectCredentialsException;
	/**
	 * returns the User if username and password are correct. 
	 * otherwise throws an IncorrectCredentialsException.
	 * 
	 * @param username
	 * @param password
	 * @return User matching the given username/password
	 */

	
	public int submitRequest(Request newRequest) throws RequestAlreadySubmittedException;
	/**
	 * Allows employee to submit request
	 * 
	 * @param employee
	 * @param request
	 * @return status_id
	 */

	public Request getRequest(int request_id);
	/**
	 * 
	 * @param request_id
	 * @return request with the request_id
	 */

	public Status updateRequestStatus(Status statusToUpdate);
	/**
	 * 
	 * @param request
	 * @param status_id
	 * @return status_id
	 * 
	 */
	
	
}
