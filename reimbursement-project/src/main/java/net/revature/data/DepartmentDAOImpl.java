package net.revature.data;
/*
 * DOUBLE CHECK THESE METHODS 
 * AND MAKE SURE THE IDS ARE CORRECT AND IN THE RIGHT PLACE
 * EX LINE 32
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.revature.models.Department;
import net.revature.services.ConnectionFactory;

public class DepartmentDAOImpl implements DepartmentDAO{

	Connection connection;
	
	public DepartmentDAOImpl() {
		connection = ConnectionFactory.getConnection();
	}
	
	@Override
	public int create(Department newObj) {
		String sql = "insert into Department(dept_id, dept_name, dept_head_id)" +
				"values (default, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			//preparedStatement.setInt(1, newObj.getDept_id());
			preparedStatement.setString(1, newObj.getDeptName());
			preparedStatement.setInt(2, newObj.getDeptHeadId());
			
			int count = preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(count > 0) {
				System.out.println("This is your Department");
				resultSet.next();
				int deptId = resultSet.getInt(1);
				//newObj.setDept_id(dept_id);
				//connection.commit();
				return deptId;
			}
			else {
				System.out.println("Department has not been created.");
				connection.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return newObj.getDeptId();
	}

	
	@Override
	public int getByDeptId(int deptId) {
		Department department = null;
		String sql = "SELECT * FROM Department WHERE dept_id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deptId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				
				return deptId;}
				else {
					System.out.println("Cant get Dept");
					return deptId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			return deptId;
		
	}


	@Override
	public Department getByDeptName(String deptName) {
		Department department = null;
		String sql = "SELECT * FROM Department WHERE dept_name = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, deptName);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return department;
			}else {
				System.out.println("Cant get Dept");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public Department getByDeptHeadId(int deptHeadId) {
		Department department = null;
		String sql = "SELECT * FROM Department WHERE Dept_head_id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deptHeadId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				department = parseResultSet(resultSet);
			}else {
				System.out.println("Cant get Dept");	
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	private Department parseResultSet(ResultSet resultSet)  throws SQLException{
		Department department = new Department();
		department.setDeptId(resultSet.getInt(1));
		department.setDeptName(resultSet.getString(2));
		department.setDeptHeadId(resultSet.getInt(3));
		return department;
	}

	@Override
	public Department getById(int deptId) {
		Department department = null;
		String sql = "SELECT * FROM Department WHERE dept_id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deptId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				department = parseResultSet(resultSet);
			}else {
				System.out.println("Cant get Dept");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Department updateObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Department objToDelete) {
		// TODO Auto-generated method stub
		
	}

	

	
}
