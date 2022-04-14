package net.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import net.revature.models.EventType;

@ExtendWith(MockitoExtension.class)
class EventTypeDAOImplTest {
	private static EventTypeDAO eventTypeDAO = new EventTypeDAOImpl();
	private static EventType testEventType = new EventType();
	private static EventType testNewEventType = new EventType();
	
	@BeforeAll
	public static void setUp() {
		testEventType.setEventTypeName("test");
		Random rand = new Random();
		testNewEventType.setEventTypeName("test" + rand.nextLong());
		testEventType.setEventTypeId(eventTypeDAO.create(testEventType));
	}
	@AfterAll
	public static void cleanUp() throws SQLException{
		eventTypeDAO.delete(testEventType);
	}

	@Test
	public void getByEventTypeIdExists() {
		int id = testEventType.getEventTypeId();
		EventType eventType = eventTypeDAO.getById(id);
		assertEquals(testEventType, eventType);
	}

}
