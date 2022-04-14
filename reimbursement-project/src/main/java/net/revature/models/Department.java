package net.revature.models;

import java.util.Objects;

public class Department {

	
	private int deptId;
	private String deptName;
	private int deptHeadId;
	
	public Department() {
		deptId = 0;
		deptName = "";
		deptHeadId = 0;
	}
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getDeptHeadId() {
		return deptHeadId;
	}
	public void setDeptHeadId(int deptHeadId) {
		this.deptHeadId = deptHeadId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(deptHeadId, deptId, deptName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return deptHeadId == other.deptHeadId && deptId == other.deptId && Objects.equals(deptName, other.deptName);
	}
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", deptHeadId=" + deptHeadId + "]";
	}
	
}
