package net.revature.models;

import java.util.Objects;

public class Status {
	private int statusId;
	private String statusName;
	
	
	public Status() {
		statusId = 0;
		statusName = "";
		
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	@Override
	public String toString() {
		return "Status [status_id=" + statusName + ", status_name=" + statusName + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(statusName, statusName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return statusName == other.statusName && Objects.equals(statusName, other.statusName);
	}

}
