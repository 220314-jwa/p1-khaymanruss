package net.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.exceptions.IncorrectCredentialsException;

import net.revature.data.EmployeeDAO;
import net.revature.data.RequestDAO;
import net.revature.data.StatusDAO;
import net.revature.models.Employee;

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
		mockEmployee.setUsername(username);
		mockEmployee.setPassword(password);
		
		when(employeeDAO.getByUsername(username)).thenReturn(mockEmployee);
		
		Employee actualEmployee = userServ.logIn(username, password);
		
		assertEquals(mockEmployee, actualEmployee);
		
		
	}

	@Test
	void testSubmitRequest() {
		System.out.println("TEST WORKS HOE");
	}

	@Test
	void testGetRequestByrequest_id() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateRequestStatus() {
		fail("Not yet implemented");
	}

}
