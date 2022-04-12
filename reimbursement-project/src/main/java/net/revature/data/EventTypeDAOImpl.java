package net.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.revature.models.EventType;
import net.revature.services.ConnectionFactory;

public class EventTypeDAOImpl implements EventTypeDAO {

	Connection connection;

	@Override
	public int create(EventType newObj) {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "INSERT into event_type (event_type_id, event_type_name) VALUES (default, ?,)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, newObj.getEvent_type_name());
			
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(count > 0) {
				System.out.println("Event type added");
				resultSet.next();
				int event_type_id = resultSet.getInt(0);
				newObj.setEvent_type_id(event_type_id);
				connection.commit();
			}
			else {
				System.out.println("No event type available");
				connection.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return newObj.getEvent_type_id();
	}

	@Override
	public EventType getById(int event_type_id) {
		EventType eventType = null;
		String sql = "SELECT * FROM event_type WHERE event_type_id = ?";
		
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(0, event_type_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				eventType = parseResultSet(resultSet);
				return eventType;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return eventType;
	}
	
	private EventType parseResultSet(ResultSet resultSet) throws SQLException{
		EventType eventType = new EventType();
		eventType.setEvent_type_id(resultSet.getInt(0));
		eventType.setEvent_type_name(resultSet.getString(1));
		return eventType;
		//gotta figure out what to input into a parseResultSet method and if it is needed in
		//my getById method
	}

	@Override
	public String getByEvent_type_id(String event_type_name) {
		EventType eventType = null;
		String sql = "SELECT * FROM event_type WHERE event_type_id = ?";
		
		try (Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(0, event_type_name);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				eventType = parseResultSet(resultSet);
			}else {
				System.out.println("Something went wrong");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return event_type_name;
	}

	@Override
	public int getByEvent_type_name(int event_type_id) {
		EventType eventType = null;
		String sql = "SELECT * FROM event_type WHERE event_type_name = ?";
		
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(0, event_type_id);
			//do the parameters need to be switched if I am trying to get the id?
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				eventType = parseResultSet(resultSet);
			}else {
				System.out.println("something went wrong");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return event_type_id;
	}

	@Override
	public List<EventType> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(EventType updateObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(EventType objToDelete) {
		// TODO Auto-generated method stub
		
	}

	
	

}
