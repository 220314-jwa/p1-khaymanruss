package net.revature.data;

import net.revature.models.Employee;

public interface EmployeeDAO extends GenericDAO<Employee> {

	public Employee getByEmployeeId(String username);
	public int getByUsername(int employeeId);
	public Employee getByUsername(String username);
}
