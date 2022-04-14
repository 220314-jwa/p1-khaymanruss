package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.revature.models.Status;

class StatusDAOImplTest {

	private static StatusDAO statusDAO = DAOFactory.getStatusDAO();
	private static Status testStatus = new Status();
	private static Status testNewStatus = new Status();
	
	@BeforeAll
	static void setUpBeforeClass() {
		testStatus.setStatusName("test");
		Random rand = new Random();
		testNewStatus.setStatusName("test" + rand.nextLong());
		testStatus.setStatusId(statusDAO.create(testStatus));
	}
	@AfterAll
	public static void cleanUp() throws SQLException{
		statusDAO.delete(testStatus);
	}

	@Test
	public void getByStatusIdExists() {
		int id = testStatus.getStatusId();
		Status status = statusDAO.getById(id);
		assertEquals(testStatus, status);
	}

}
