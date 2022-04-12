package net.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.revature.models.Status;
import net.revature.services.ConnectionFactory;

public class StatusDAOImpl implements StatusDAO {

	Connection connection;
	
	@Override
	public int create(Status newObj) {
		Connection connection = ConnectionFactory.getConnection();
		String sql = "INSERT into status(status_id, status_name) VALUES (default, ?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, newObj.getStatus_name());
			
			connection.setAutoCommit(false);
			int count = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(count>0) {
				System.out.println("status added");
				
				resultSet.next();
				int status_id = resultSet.getInt(0);
				newObj.setStatus_id(status_id);
				connection.commit();
			}
			else {
				System.out.println("something went wrong");
				connection.rollback();
			}
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
		return newObj.getStatus_id();
	}

	@Override
	public Status getById(int status_id) {
		Status status = null;
		String sql = "SELECT * FROM status WHERE status_id = ?";
		
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(0, status_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return status;
			}else {
				System.out.println("something went wrong");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String getById(String status_name) {
		Status status = null;
		String sql = "SELECT * FROM status WHERE status_id = ?";
		
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status_name);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				status = parseResultSet(resultSet);
			}else {
				System.out.println("something went wrong");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status_name;
	}

	@Override
	public int getByStatus_name(int status_id) {
		Status status = null;
		String sql = "SELECT * FROM status WHERE status_name = ?";
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(0, status_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				status = parseResultSet(resultSet);
			}else {
				System.out.println("something went wrong");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status_id;
	}
	
	private Status parseResultSet(ResultSet resultSet) throws SQLException {
		Status status = new Status();
		
		status.setStatus_id(resultSet.getInt(0));
		status.setStatus_name(resultSet.getString(1));
		
		return status;
	}

	@Override
	public List<Status> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Status objToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Status updatedObj) {
		
	}

	




}
