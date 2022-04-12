package net.revature.services;

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

	
	@Override
	public int submitRequest(Request newRequest) throws RequestAlreadySubmittedException {
		
		return requestDAO.create(newRequest);
	}

	
	@Override
	public Status updateRequestStatus(Status statusToUpdate) {
		if(statusDAO.getById(statusToUpdate.getStatus_id()) != null){
			statusDAO.update(statusToUpdate);
			statusToUpdate = statusDAO.getById(statusToUpdate.getStatus_id());
			return statusToUpdate;
		}
		return null;
	}


	@Override
	public Request getRequest(int request_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
