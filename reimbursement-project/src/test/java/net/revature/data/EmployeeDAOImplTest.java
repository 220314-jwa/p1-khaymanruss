package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import net.revature.models.Employee;

@TestMethodOrder(OrderAnnotation.class)
class EmployeeDAOImplTest {
	private static EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
	private static Employee testEmployee = new Employee();
	private static Employee testNewEmployee = new Employee();

	@BeforeAll
	public static void setUp() {
		testEmployee.setfirst_name("test");
		Random rand = new Random();
		testNewEmployee.setfirst_name("test_" + rand.nextLong());
		testEmployee.setemployee_id(employeeDAO.create(testEmployee));
	}
	@AfterAll
	public static void cleanUp() {
		employeeDAO.delete(testEmployee);
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
	
	

}
