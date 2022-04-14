package net.revature.data;

import net.revature.models.Department;

public interface DepartmentDAO extends GenericDAO<Department> {

	public int getByDeptId(int deptId);
	public Department getByDeptName(String deptName);
	public Department getByDeptHeadId(int deptHeadId);
	
	
}
