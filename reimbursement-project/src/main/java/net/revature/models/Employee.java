package net.revature.models;

import java.util.Objects;

public class Employee {
	

	private int employee_id;
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private int manager_id;
	private int dept_id;
	
	
	public Employee() {
		employee_id = 0;
		username = "khayman";
		password = "pass";
		first_name = "";
		last_name = "";
		manager_id = 0;
		dept_id = 0;
		
	}
	

	public int getemployee_id() {
		return employee_id;
	}
	public void setemployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getfirst_name() {
		return first_name;
	}
	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getlast_name() {
		return last_name;
	}
	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getmanager_id() {
		return manager_id;
	}
	public void setmanager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getdept_id() {
		return dept_id;
	}
	public void setdept_id(int dept_id) {
		this.dept_id = dept_id;
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
		return "Employee [employee_id=" + employee_id + ", username=" + username + ", password=" + password
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", manager_id=" + manager_id
				+ ", dept_id=" + dept_id + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(dept_id, employee_id, first_name, last_name, manager_id, password, username);
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
		return dept_id == other.dept_id && employee_id == other.employee_id
				&& Objects.equals(first_name, other.first_name) && Objects.equals(last_name, other.last_name)
				&& manager_id == other.manager_id && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
}