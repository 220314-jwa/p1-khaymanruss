package net.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "INSERT into Request (request_id, employee_id, event_type_id, status_id, event_date, cost, description, location, submitted_at)" +
				"values (default, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			//create a prepared statement and pass in the sql command
			//also the return generated keys flag so that we can get that id that is generated
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			//set the fields
			preparedStatement.setTimestamp(4, (Timestamp) newObj.getEvent_date());
			preparedStatement.setLong(5, newObj.getCost());
			preparedStatement.setString(6, newObj.getDescription());
			preparedStatement.setString(7, newObj.getLocation());
			preparedStatement.setTimestamp(8, (Timestamp) newObj.getEvent_date());
			//instantiating employeeDAO into Request
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			//passing in the employeeDAO username string and returning the int employee_id
			preparedStatement.setInt(1, employeeDAO.getByUsername(newObj.getEmployee_id()));
			//instantiating
			EventTypeDAO eventTypeDAO = DAOFactory.getEventTypeDAO();
			preparedStatement.setInt(2, eventTypeDAO.getByEvent_type_name(newObj.getEvent_type_id()));
			StatusDAO statusDAO = DAOFactory.getStatusDAO();
			preparedStatement.setInt(3, statusDAO.getByStatus_name(newObj.getStatus_id()));
			
			int count = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(count>0) {
				System.out.println("Request has been added");
				resultSet.next();
				int id = resultSet.getInt(1);
				newObj.setEmployee_id(id);;
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
		return newObj.getRequest_id();
	}

	@Override
	public Request getById(int Request_id) {
		Request request = null;
		
		String sql = "SELECT * FROM Request WHERE request_id = ?";
		
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Request_id);
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
		request.setEmployee_id(resultSet.getInt(1));
		request.setEvent_type_id(resultSet.getInt(2));
		request.setStatus_id(resultSet.getInt(3));
		request.setEvent_date(resultSet.getDate(4));
		request.setCost(resultSet.getLong(5));
		request.setDescription(resultSet.getString(6));
		request.setLocation(resultSet.getString(7));
		request.setSubmitted_at(resultSet.getDate(8));
		return request;

	}
	@Override
	public void update(Request updateObj) {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "update request set employee_id = ?, event_type_id = ?, status_id = ?, event_date = ?, cost = ?, description = ?, location = ?, submitted_at = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			preparedStatement.setInt(1, updateObj.getEmployee_id());
			EventTypeDAO eventTypeDAO = DAOFactory.getEventTypeDAO();
			preparedStatement.setInt(2, updateObj.getEvent_type_id());
			StatusDAO statusDAO = DAOFactory.getStatusDAO();
			preparedStatement.setInt(3, updateObj.getStatus_id());
			preparedStatement.setTimestamp(4, (Timestamp) updateObj.getEvent_date());
			//local date java.util
			preparedStatement.setLong(5, updateObj.getCost());
			preparedStatement.setString(6, updateObj.getDescription());
			preparedStatement.setString(7, updateObj.getDescription());
			preparedStatement.setTimestamp(8, (Timestamp) updateObj.getSubmitted_at());
			
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
			preparedStatement.setInt(0, objToDelete.getRequest_id());
			
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
