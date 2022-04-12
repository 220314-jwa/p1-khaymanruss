package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import net.revature.models.Employee;
import net.revature.models.EventType;
import net.revature.models.Status;

@ExtendWith(MockitoExtension.class)
class RequestDAOImplTest {
	private RequestDAO requestDAO = new RequestDAOImpl();
	private StatusDAO statusDAO = new StatusDAOImpl();
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	private EventTypeDAO eventTypeDAO = new EventTypeDAOImpl();
	private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	
	private static int request_id;
	private static Employee employee_id;
	private static EventType event_type_id;
	private static Status status;
	
	
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testRequestDAOImpl() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
