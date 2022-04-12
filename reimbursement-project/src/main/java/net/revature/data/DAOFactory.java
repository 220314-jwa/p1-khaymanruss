package net.revature.data;

public class DAOFactory {

	private static EmployeeDAO employeeDAO = null;
	private static DepartmentDAO departmentDAO = null;
	private static EventTypeDAO eventTypeDAO = null;
	private static StatusDAO statusDAO = null;
	private static RequestDAO requestDAO = null;
	
	private DAOFactory() {
		
	}
	
	public static EmployeeDAO getEmployeeDAO() {
		if(employeeDAO == null) {
			employeeDAO = new EmployeeDAOImpl();
		}
		return employeeDAO;
	}
	
	public static DepartmentDAO getDepartmentDAO() {
		if(departmentDAO ==null) {
			departmentDAO = new DepartmentDAOImpl();
		}
		return departmentDAO;
	}
	
	public static EventTypeDAO getEventTypeDAO() {
		if(eventTypeDAO == null) {
			eventTypeDAO = new EventTypeDAOImpl();
		}
		return eventTypeDAO;
	}
	
	public static StatusDAO getStatusDAO() {
		if(statusDAO == null) {
			statusDAO = new StatusDAOImpl();
		}
		return statusDAO;
	}
	
	public static RequestDAO getRequestDAO() {
		if(requestDAO == null) {
			requestDAO = new RequestDAOImpl();
		}
		return requestDAO;
	}
}
