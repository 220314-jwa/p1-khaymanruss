package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

//import net.revature.models.Employee;
//import net.revature.models.EventType;
import net.revature.models.Request;
//import net.revature.models.Status;

@ExtendWith(MockitoExtension.class)
class RequestDAOImplTest {

	private static RequestDAO requestDAO = DAOFactory.getRequestDAO();
	private static Request testRequest = new Request();
	private static Request testNewRequest = new Request();
	
	@BeforeAll
	static void setUpBeforeClass(){
		testRequest.setEmployeeId(123);
		Random rand = new Random();
		testNewRequest.setRequestId(123 + rand.nextInt());
		testRequest.setRequestId(requestDAO.create(testRequest));
	}
	@AfterAll
	public static void cleanup() throws SQLException{
		requestDAO.delete(testRequest);
	}
	

	@Test
	@Disabled
	void testRequestDAOImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void createRequestSuccessfully() {
		int id = requestDAO.create(testNewRequest);
		testNewRequest.setRequestId(id);
		
		assertNotEquals(0, id);
	}

	@Test
	public void getByIdExists() {
		int id = testRequest.getRequestId();
		Request request = requestDAO.getById(id);
		assertEquals(testRequest, request);
	}

	@Test
	public void getByIdDoesNotExist() {
		Request request = requestDAO.getById(0);
		assertNull(request);
	}
	
	@Test
	public void GetAll() {
		assertNotNull(requestDAO.getAll());
	}

	@Test
	public void updateRequestExists() {
		assertDoesNotThrow(() ->{
			requestDAO.update(testRequest);
		});
	}

	@Test
	public void deleteRequestExists() {
		assertDoesNotThrow(() -> {
			requestDAO.delete(testRequest);
		});
	}

}
