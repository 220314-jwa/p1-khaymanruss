package net.revature.models;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Request {


	private int requestId;
	private int employeeId;
	private int eventTypeId;
	private int statusId;
	private Date eventDate;
	private long cost;
	private String description;
	private String location;
	private Date submittedAt;
	private String grade;
	
	public Request() {
		requestId = 0;
		employeeId = 0;
		eventTypeId = 0;
		statusId = 0;
		eventDate = new Date();
		cost = 0;
		description = "";
		location = "";
		submittedAt = new Date();
		grade = "";
		
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

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(Date submittedAt) {
		this.submittedAt = submittedAt;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", employeeId=" + employeeId + ", eventTypeId=" + eventTypeId
				+ ", statusId=" + statusId + ", eventDate=" + eventDate + ", cost=" + cost + ", description="
				+ description + ", location=" + location + ", submittedAt=" + submittedAt + ", grade=" + grade + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cost, description, employeeId, eventDate, eventTypeId, grade, location, requestId, statusId,
				submittedAt);
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
		return cost == other.cost && Objects.equals(description, other.description) && employeeId == other.employeeId
				&& Objects.equals(eventDate, other.eventDate) && eventTypeId == other.eventTypeId
				&& Objects.equals(grade, other.grade) && Objects.equals(location, other.location)
				&& requestId == other.requestId && statusId == other.statusId
				&& Objects.equals(submittedAt, other.submittedAt);
	}
}
