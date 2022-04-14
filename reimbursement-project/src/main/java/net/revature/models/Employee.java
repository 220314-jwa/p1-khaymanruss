package net.revature.models;

import java.util.Objects;

public class Employee {
	

	


	private int employeeId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int managerId;
	private int deptId;
	
	
	public Employee() {
		employeeId = 0;
		username = "khayman";
		password = "pass";
		firstName = "";
		lastName = "";
		managerId = 0;
		deptId = 0;
		
	}
	

	public int getemployeeId() {
		return employeeId;
	}
	public void setemployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	public int getmanagerId() {
		return managerId;
	}
	public void setmanagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getdeptId() {
		return deptId;
	}
	public void setdeptId(int deptId) {
		this.deptId = deptId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", managerId=" + managerId + ", deptId="
				+ deptId + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(deptId, employeeId, firstName, lastName, managerId, password, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return deptId == other.deptId && employeeId == other.employeeId && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && managerId == other.managerId
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

}