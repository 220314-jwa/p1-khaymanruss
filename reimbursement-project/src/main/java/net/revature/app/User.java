package net.revature.app;

public class User {
	int employeeId;
	String firstName;
	String lastName;
	int managerId;
	int deptId;
	
	
	public String toString() {
		return "User [employeeId= "+ employeeId + ", firstName= " + firstName + 
				", lastName= " + lastName + ", managerId " + managerId + ",deptId " + deptId 
				 + "]";
		
	}
}
