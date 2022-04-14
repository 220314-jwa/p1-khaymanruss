package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Random;

import net.revature.models.Department;

class DepartmentDAOImplTest {

	private static DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
	private static Department testDept = new Department();
	private static Department testNewDept = new Department();
	
	@BeforeAll
	public static void setUp() throws SQLException {
		testDept.setDept_name("test");
		Random rand = new Random();
		testNewDept.setDept_name("test_" + rand.nextLong());
		testDept.setDept_head_id(departmentDAO.create(testDept));
	}
	@AfterAll
	public static void cleanUp() throws SQLException {
		
		departmentDAO.delete(testDept);
	}
/*
	@Test
	public void create() {
		//want to get the newObj(dept) dept_id 
		//assertNotNull? assertNotEquals?
		Department department = new Department();
		assertNotEquals(0, departmentDAO.create(department));
	}

	@Test
	public void testGetByDept_idDoesNotExist() {
		int dept = departmentDAO.getByDept_id(0);
		assertNull(testDept);
	}

	@Test
	public void testGetByDept_name() {
		Department department = departmentDAO.getByDept_name("thisguy");
		assertNotNull(testDept);
	}

	@Test
	public void testGetByDept_head_id() {
		Department department = departmentDAO.getByDept_head_id(1234);
		assertNull(testDept);
	}
*/
	@Test
	public void getByIdExists() {
		int id = testDept.getDept_id();
		Department department = departmentDAO.getById(id);
		assertEquals(testDept, department);
	}

}
