package net.revature.data;

import net.revature.models.Employee;

public interface EmployeeDAO extends GenericDAO<Employee> {

	public Employee getByEmployee_id(String username);
	public int getByUsername(int employee_id);
	public Employee getByUsername(String username);
}
