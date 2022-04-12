package net.revature.users;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.exceptions.IncorrectCredentialsException;

import net.revature.models.Employee;
import net.revature.services.UserService;


public class UsersTest {
	
	private UserService employee;
	
	//or
	
	
	//need a field for the class that we are testing

	
	@Test
	public void logInSuccessfully() throws IncorrectCredentialsException {
		//need to connect to database?
		String username = "khayman";
		String password = "pass";
		
		//employee.logIn(username, password);
		//manager.logIn(username, password);
		//department.logIn(username, password);
		Employee result = employee.logIn(username, password);
		
		assertNotNull(username, result.getUsername());
		assertNotNull(password, result.getPassword());
		
	}
	
	@Test
	public void logInWrongUsername() throws IncorrectCredentialsException{
		String username = "kman";
		String password = "passwrd";
		
		Employee result = employee.logIn(username, password);
		
		assertEquals(username, result.getUsername());
	}

	
	@Test
	public void submitReimbursementRequest() {
		//assertNotNull?
		//fileNotFound?
		//IOException?
	}
	
	@Test
	public void addFinalGrade() {
		
	}
	
	@Test
	public void accept() {
		//exception being reject?
	}
	@Test
	public void approved() {
		//exception being rejected?
	}
	
	
}
