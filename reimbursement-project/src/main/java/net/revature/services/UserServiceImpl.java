package net.revature.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.RequestAlreadySubmittedException;

import net.revature.data.DAOFactory;
import net.revature.data.EmployeeDAO;
import net.revature.data.EmployeeDAOImpl;
import net.revature.data.RequestDAO;
import net.revature.data.RequestDAOImpl;
import net.revature.data.StatusDAO;
import net.revature.data.StatusDAOImpl;
import net.revature.models.Employee;
import net.revature.models.Request;
import net.revature.models.Status;

public class UserServiceImpl implements UserService{
	private EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	private RequestDAO requestDAO = DAOFactory.getRequestDAO();
	private StatusDAO statusDAO = DAOFactory.getStatusDAO();

	@Override
	public Employee logIn(String username, String password) throws IncorrectCredentialsException {
		Employee employee = employeeDAO.getByUsername(username);
		if(employee != null && employee.getPassword().equals(password)) {
			return employee;
		}else {
			throw new IncorrectCredentialsException();
		}
		
	}

	/*
	@Override
	public Request submitRequest(Request requestToSubmit) throws RequestAlreadySubmittedException {
		requestToSubmit = requestDAO.getById(requestToSubmit.getRequest_id());
		//we want to submit request
		//also want to make sure the request hasnt already been submitted
		if(requestToSubmit.getStatus_id() == (0)) {
			System.out.println("No request was submitted");
			//throw new RequestAlreadySubmittedException();
		}else {
			//check employee to make sure the account is valid
			//employee = employeeDAO.getById(employee.getemployee_id());
			//if(employee != null) {
			if(requestToSubmit != null) {
				
			requestToSubmit.setStatus_id(1);
			//this is where she added user.getPets which was a field of her user
			//maybe add something for request for employee?
			try {
				requestDAO.update(requestToSubmit);
			}
			
		}finally {
			System.out.println("request has been submitted?");
		}
		
		return requestToSubmit;
	}
	
*/
	
	/*@Override
	public Status updateRequestStatus(Status statusToUpdate) {
		if(statusDAO.getById(statusToUpdate.getStatus_id()) != null){
			statusDAO.update(statusToUpdate);
			statusToUpdate = statusDAO.getById(statusToUpdate.getStatus_id());
			return statusToUpdate;
		}
		return statusToUpdate;
	}*/




	@Override
	public List<Request> viewRequests() {
		List<Request> requests = requestDAO.getAll();
		return requests;
	}


	@Override
	public Request editRequest(Request requestToEdit) {
		Request request = requestDAO.getById(requestToEdit.getRequestId());
		if(request != null) {
			requestDAO.update(requestToEdit);
			return requestDAO.getById(requestToEdit.getRequestId());
		}
		return request;
	}

	@Override
	public Request submitRequest(Request newRequest) throws RequestAlreadySubmittedException {
		int requestId = requestDAO.create(newRequest);
		if(requestId != 0) {
			newRequest.setRequestId(requestId);
			return newRequest;
		}else {
			throw new RequestAlreadySubmittedException();
		}
		
	}

	
}
