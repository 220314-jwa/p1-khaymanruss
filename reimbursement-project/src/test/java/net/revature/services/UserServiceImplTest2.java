package net.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.exceptions.IncorrectCredentialsException;

import net.revature.data.EmployeeDAO;
import net.revature.data.RequestDAO;
import net.revature.data.StatusDAO;
import net.revature.models.Employee;
import net.revature.models.Request;

@ExtendWith(MockitoExtension.class) 
class UserServiceImplTest2 {
	
	@Mock
	private EmployeeDAO employeeDAO;
	@Mock
	private RequestDAO requestDAO;
	@Mock
	private StatusDAO statusDAO;
	@InjectMocks
	private UserService userServ = new UserServiceImpl();

	

	@Test
	public void logInSuccessfully() throws IncorrectCredentialsException{
		//System.out.println("the test has run");
		String username = "kman";
		String password = "pword";
		
		//Employee result = userServ.logIn(username, password);
		//Employee expected = userServ.logIn("kman", "password");
		//Employee actual = userServ.logIn("kman", "pass");
		
		//assertNotNull(username, result.getUsername());
		
		Employee mockEmployee = new Employee();
		mockEmployee.setusername(username);
		mockEmployee.setpassword(password);
		
		when(employeeDAO.getByUsername(username)).thenReturn(mockEmployee);
		
		Employee actualEmployee = userServ.logIn(username, password);
		
		assertEquals(mockEmployee, actualEmployee);
		
		
	}

	/*
	@Test
	public void SubmitRequestSuccessfully() throws Exception{
		//Employee testEmployee = new Employee();
		Request testRequest = new Request();
		testRequest.setRequest_id(1);
		
		
		when(requestDAO.getById(testRequest.getRequest_id())).thenReturn(testRequest);
		//when(employeeDAO.getById(testEmployee.getemployee_id())).thenReturn(testEmployee);
		
		doNothing().when(requestDAO).update(any(Request.class));
		Request result = userServ.submitRequest(testRequest);
		//doNothing().when(employeeDAO).update(any(Employee.class));
		//doNothing().when(requestDAO).updateRequest(testRequest.getRequest_id(), testEmployee.getemployee_id());
		//Employee result = userServ.submitRequest(testEmployee, testRequest);
		
		testRequest.setStatus_id(1);
		
		verify(requestDAO, times(1)).update(testRequest);
		
		
		
		
	}*/
	@Test
	public void submitRequestAlreadyExists() throws SQLException {
		//Employee testEmployee = new Employee();
		Request testRequest = new Request();
		testRequest.setStatusId(1);
		
		//when(requestDAO.getById(testRequest.getRequest_id())).thenReturn(testRequest);
		
		assertThrows(Exception.class, () -> {
			userServ.submitRequest(testRequest);
		});
		verify(requestDAO, never()).update(any(Request.class));
	}

	@Test
	public void viewRequestsSuccessfully() {
		List<Request> requests = userServ.viewRequests();
		assertNotNull(requests);
	}

	@Test
	public void editRequestSuccessfully() {
		Request testRequest = new Request();
		testRequest.setRequestId(2);
		testRequest.setGrade("A");
		
		when(requestDAO.getById(2)).thenReturn(testRequest);
		doNothing().when(requestDAO).update(Mockito.any(Request.class));
		
		Request actualRequest = userServ.editRequest(testRequest);
		assertEquals(testRequest, actualRequest);
	}
	
	@Test
	public void editRequestDoesNotExist() {
		when(requestDAO.getById(2)).thenReturn(null);
		
		Request testRequest = new Request();
		testRequest.setRequestId(2);
		
		Request actualRequest = userServ.editRequest(testRequest);
		
		assertNull(actualRequest);
		verify(requestDAO, times(0)).update(Mockito.any(Request.class));
	}

	@Test
	public void SubmitRequestSuccessfully() throws Exception{
		Request testRequest = new Request();
		when(requestDAO.create(testRequest)).thenReturn(1);
		
		Request result = userServ.submitRequest(testRequest);
		assertNotEquals(0, result.getRequestId());
	}
}
