package net.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.revature.models.Request;
import net.revature.services.ConnectionFactory;

public class RequestDAOImpl implements RequestDAO {

	Connection connection;
	
	public RequestDAOImpl() {
		connection = ConnectionFactory.getConnection();
	}


	@Override
	public int create(Request newObj) {
		String sql = "INSERT into reimbursement_request (request_id, employee_id, event_type_id, status_id, event_date, cost, description, location, submitted_at, grade)" +
				"values (default,?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			//create a prepared statement and pass in the sql command
			//also the return generated keys flag so that we can get that id that is generated
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			//set the fields
			preparedStatement.setDate(4, new java.sql.Date(newObj.getEventDate().getTime()));
			preparedStatement.setLong(5, newObj.getCost());
			preparedStatement.setString(6, newObj.getDescription());
			preparedStatement.setString(7, newObj.getLocation());
			preparedStatement.setDate(8, new java.sql.Date(newObj.getSubmittedAt().getTime()));
			preparedStatement.setString(9, newObj.getGrade());
			//instantiating employeeDAO into Request
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			//passing in the employeeDAO username string and returning the int employee_id
			preparedStatement.setInt(1, employeeDAO.getByUsername(newObj.getEmployeeId()));
			//instantiating
			EventTypeDAO eventTypeDAO = DAOFactory.getEventTypeDAO();
			preparedStatement.setInt(2, eventTypeDAO.getByEventTypeName(newObj.getEventTypeId()));
			StatusDAO statusDAO = DAOFactory.getStatusDAO();
			preparedStatement.setInt(3, statusDAO.getByStatusName(newObj.getStatusId()));
			
			int count = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(count>0) {
				System.out.println("Request has been added");
				resultSet.next();
				int id = resultSet.getInt(1);
				newObj.setRequestId(id);;
				connection.commit();
			}
			else {
				System.out.println("Request has not been added");
				connection.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newObj.getRequestId();
	}

	@Override
	public Request getById(int RequestId) {
		Request request = null;
		
		String sql = "SELECT request_id, event_date, cost, description, location, submitted_at, grade FROM reimbursement_request" +
		"left join employee_id on employee.id=employee.employee_id" +
		"left join event_type_id on event_type.id=event_type.event_type_id" +
		"left join status_id on status.id=request_status.status_id" +
		"WHERE request_id = ?";
		
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, RequestId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				request = parseResultSet(resultSet);
			}else {
				System.out.println("Something went wrong");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	public List<Request> getAll() {
		List<Request> requests = new ArrayList<Request>();
		String sql = "SELECT * FROM request";
		try (Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
			Request request = parseResultSet(resultSet);
			requests.add(request);
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	private Request parseResultSet(ResultSet resultSet) throws SQLException{
		Request request = new Request();
		request.setEmployeeId(resultSet.getInt(1));
		request.setEventTypeId(resultSet.getInt(2));
		request.setStatusId(resultSet.getInt(3));
		request.setEventDate(resultSet.getDate(4));
		request.setCost(resultSet.getLong(5));
		request.setDescription(resultSet.getString(6));
		request.setLocation(resultSet.getString(7));
		request.setSubmittedAt(resultSet.getDate(8));
		request.setGrade(resultSet.getString(9));
		return request;

	}
	@Override
	public void update(Request updateObj) {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "update request set employee_id = ?, event_type_id = ?, status_id = ?, event_date = ?, cost = ?, description = ?, location = ?, submitted_at = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			preparedStatement.setInt(1, updateObj.getEmployeeId());
			EventTypeDAO eventTypeDAO = DAOFactory.getEventTypeDAO();
			preparedStatement.setInt(2, updateObj.getEventTypeId());
			StatusDAO statusDAO = DAOFactory.getStatusDAO();
			preparedStatement.setInt(3, updateObj.getStatusId());
			preparedStatement.setDate(4, new java.sql.Date(updateObj.getEventDate().getTime()));
			//might be bad that i commented this out. just trying. nvm
			preparedStatement.setLong(5, updateObj.getCost());
			preparedStatement.setString(6, updateObj.getDescription());
			preparedStatement.setString(7, updateObj.getDescription());
			preparedStatement.setDate(8, new java.sql.Date(updateObj.getSubmittedAt().getTime()));
			preparedStatement.setString(9, updateObj.getGrade());
			
			connection.setAutoCommit(false);
			
			int count = preparedStatement.executeUpdate();
			if(count != 1) {
				System.out.println("Something went wrong with updating");
				connection.rollback();
			}else connection.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void delete(Request objToDelete) {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "delete from request where request_id = ?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, objToDelete.getRequestId());
			
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			if(count!=1) {
				System.out.println("You were not able to delete the request");
				connection.rollback();
			}else connection.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	/*@Override
	public int getByEmployee_id(int request_id) {
		// TODO Auto-generated method stub
		return 0;
	}*/


	//eventually add list requests by status
	//

	


	
	
}
