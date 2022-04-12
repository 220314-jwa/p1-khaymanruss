package net.revature.models;

import java.util.Objects;

public class Status {
	private int status_id;
	private String status_name;
	
	
	public Status() {
		status_id = 0;
		status_name = "";
		
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	@Override
	public String toString() {
		return "Status [status_id=" + status_id + ", status_name=" + status_name + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(status_id, status_name);
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
		return status_id == other.status_id && Objects.equals(status_name, other.status_name);
	}

}
