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
		testEmployee.setfirstName("test");
		testEmployee.setUsername("test1");
		Random rand = new Random();
		testNewEmployee.setfirstName("test" + rand.nextLong());
		testEmployee.setemployeeId(employeeDAO.create(testEmployee));
	}
	@AfterAll
	public static void cleanUp() {
		employeeDAO.delete(testEmployee);
	}
	
	@Test
	public void createEmployeeSuccessfully() {
		int id = employeeDAO.create(testNewEmployee);
		testNewEmployee.setemployeeId(id);
		assertNotEquals(0, id);
	}
	@Test
	public void getAll() {
		assertNotNull(employeeDAO.getAll());
	}
	
	@Test
	public void getByIdExists() {
		
		int employeeId = testEmployee.getemployeeId();
		Employee employee = employeeDAO.getById(employeeId);
		
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
		Employee employee = employeeDAO.getByEmployeeId("test1");
		assertEquals(testEmployee, employee);
	}
}
