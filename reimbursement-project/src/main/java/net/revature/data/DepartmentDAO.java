package net.revature.data;

import net.revature.models.Department;

public interface DepartmentDAO extends GenericDAO<Department> {

	public int getByDept_id(int dept_id);
	public Department getByDept_name(String dept_name);
	public Department getByDept_head_id(int dept_head_id);
	
	
}
