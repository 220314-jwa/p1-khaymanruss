package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import net.revature.models.Employee;


class EmployeeDAOImplTest {
	private static EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	private static Employee testEmployee = new Employee();
	private static Employee testNewEmployee = new Employee();

	@BeforeAll
	public static void setUp() {
		testEmployee.setfirst_name("test");
		testEmployee.setUsername("test1");
		Random rand = new Random();
		testNewEmployee.setfirst_name("test" + rand.nextLong());
		testEmployee.setemployee_id(employeeDAO.create(testEmployee));
	}
	@AfterAll
	public static void cleanUp() {
		employeeDAO.delete(testEmployee);
	}
	
	@Test
	public void createEmployeeSuccessfully() {
		int id = employeeDAO.create(testNewEmployee);
		testNewEmployee.setemployee_id(id);
		assertNotEquals(0, id);
	}
	@Test
	public void getAll() {
		assertNotNull(employeeDAO.getAll());
	}
	
	@Test
	public void getByIdExists() {
		
		int employee_id = testEmployee.getemployee_id();
		Employee employee = employeeDAO.getById(employee_id);
		
		assertEquals(testEmployee, employee);
	}
	
	@Test
	public void getByIdDoesNotExist() {
		Employee employee = employeeDAO.getById(0);
		assertNull(employee);
	}
	
	@Test
	public void getByUsernameExists() {
		Employee employee = employeeDAO.getByUsername("test1");
		assertEquals(testEmployee, employee);
	}

	@Test
	public void getByUsernameDoesNotExist() {
		Employee employee = employeeDAO.getByUsername("wrongonedummy");
	}
	
	@Test
	public void updateEmployeeExists() {
		assertDoesNotThrow(() ->{
			employeeDAO.update(testEmployee);
		});
	}
	
	@Test
	public void deleteEmployeeExists() {
		assertDoesNotThrow(() -> {
			employeeDAO.delete(testEmployee);
		});
	}
	
	@Test
	public void getByEmployee_idExists() {
		Employee employee = employeeDAO.getByEmployee_id("test1");
		assertEquals(testEmployee, employee);
	}
}
