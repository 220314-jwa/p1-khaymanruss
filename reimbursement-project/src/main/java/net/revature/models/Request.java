package net.revature.models;

import java.util.Date;
import java.util.Objects;

public class Request {


	private int request_id;
	private int employee_id;
	private int event_type_id;
	private int status_id;
	private Date event_date;
	private long cost;
	private String description;
	private String location;
	private Date submitted_at;
	
	public Request() {
		request_id = 0;
		employee_id = 0;
		event_type_id = 0;
		status_id = 0;
		event_date = new Date();
		cost = 0;
		description = "";
		location = "";
		submitted_at = new Date();
		
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getEvent_type_id() {
		return event_type_id;
	}

	public void setEvent_type_id(int event_type_id) {
		this.event_type_id = event_type_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}

	public Date getSubmitted_at() {
		return submitted_at;
	}

	public void setSubmitted_at(Date submitted_at) {
		this.submitted_at = submitted_at;
	}

	@Override
	public String toString() {
		return "Request [request_id=" + request_id + ", employee_id=" + employee_id + ", event_type_id=" + event_type_id
				+ ", status_id=" + status_id + ", event_date=" + event_date + ", cost=" + cost + ", description="
				+ description + ", location=" + location + ", submitted_at=" + submitted_at + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cost, description, employee_id, event_date, event_type_id, location, request_id, status_id,
				submitted_at);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return cost == other.cost && Objects.equals(description, other.description) && employee_id == other.employee_id
				&& Objects.equals(event_date, other.event_date) && event_type_id == other.event_type_id
				&& Objects.equals(location, other.location) && request_id == other.request_id
				&& status_id == other.status_id && Objects.equals(submitted_at, other.submitted_at);
	}

}
