package net.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import net.revature.models.Employee;
import net.revature.services.ConnectionFactory;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	Connection connection;
	
	public EmployeeDAOImpl() {
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public int create(Employee newObj) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "INSERT into employee (employee_id, username, password, first_name, last_name, manager_id, dept_id)"
					+ " values (default, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, newObj.getUsername());
			preparedStatement.setString(2,  newObj.getPassword());
			preparedStatement.setString(3, newObj.getfirst_name());
			preparedStatement.setString(4, newObj.getlast_name());
			preparedStatement.setInt(5, newObj.getmanager_id());
			DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
			preparedStatement.setInt(6, departmentDAO.getByDept_id(newObj.getdept_id()));
			
			connection.setAutoCommit(false);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				newObj.setemployee_id(resultSet.getInt(1));
				connection.commit();
			}else {
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return newObj.getemployee_id();
	}

	@Override
	public Employee getById(int employee_id) {
		Employee employee = null;
		try(Connection connection = ConnectionFactory.getConnection()){
			String sql = "SELECT * from employee left join dept_id on department.id=department.dept_id WHERE employee_id = ?";
					//research foreign keys in relation to request_id and employee_id as primary keys\
					//refer to her postgresSQL table pet_owner and how it references the person and pet primary keys
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(0, employee_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				employee = new Employee();
				employee.setemployee_id(employee_id);
				employee.setfirst_name(resultSet.getString("first_name"));
				employee.setlast_name(resultSet.getString("last_name"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setmanager_id(resultSet.getInt("manager_id"));
				employee.setdept_id(resultSet.getInt("dept_id"));
				
				//RequestDAO requestDAO = DAOFactory.getRequestDAO();
				//employee.setRequests(requestDAO.getById(id));
				//I have no idea what this is doing. in petapp the user setPets get by owner
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> employees = new LinkedList<>();
		try(Connection connection = ConnectionFactory.getConnection()){
			String sql = "SELECT * from employee WHERE employee_id = ?";
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setemployee_id(resultSet.getInt("employee_id"));
				employee.setfirst_name(resultSet.getString("first_name"));
				employee.setlast_name(resultSet.getString("last_name"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setmanager_id(resultSet.getInt("manager_id"));
				employee.setdept_id(resultSet.getInt("dept_id"));
				
				//RequestDAO requestDAO = DAOFactory.getRequestDAO();
				//employee.setRequests(requestDAO.getById(employee));
				
				employees.add(employee);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public void update(Employee updateObj) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "update employee set first_name = ?, last_name = ?, username = ?, password = ?, WHERE employee_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, updateObj.getUsername());
			preparedStatement.setString(2, updateObj.getPassword());
			preparedStatement.setString(3, updateObj.getfirst_name());
			preparedStatement.setString(4, updateObj.getlast_name());
			preparedStatement.setInt(5, updateObj.getmanager_id());
			preparedStatement.setInt(6, updateObj.getdept_id());
			
			connection.setAutoCommit(false);
			int rowsUpdated = preparedStatement.executeUpdate();
			
			if(rowsUpdated<=1) {
				connection.commit();
			}else {
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void delete(Employee objToDelete) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			String sql = "delete from employee where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, objToDelete.getemployee_id());
			
			connection.setAutoCommit(false);
			int rowsUpdated = preparedStatement.executeUpdate();
			
			if(rowsUpdated<=1) {
				connection.commit();
			}else {
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
		
	}

	@Override
	public Employee getByEmployee_id(String username) {
		Employee employee = null;
		try(Connection connection = ConnectionFactory.getConnection()){
			String sql = "SELECT * from employee left join dept_id on department.id=department.dept_id WHERE employee_username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee = new Employee();
				employee.setemployee_id(resultSet.getInt("id"));
				employee.setfirst_name(resultSet.getString("first_name"));
				employee.setlast_name(resultSet.getString("last_name"));
				employee.setUsername(resultSet.getString("username"));
				employee.setPassword(resultSet.getString("password"));
				employee.setmanager_id(resultSet.getInt("manager_id"));
				employee.setdept_id(resultSet.getInt("dept_id"));
				
				//RequestDAO requestDAO = DAOFactory.getRequestDAO();
				//employee.setRequests(requestDAO.getByUsername(employee));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public int getByUsername(int employee_id) {
		Employee employee = null;
				try(Connection connection = ConnectionFactory.getConnection()){
					String sql = "Select * from employee left join dept_id on department.id=department.dept_id where username = ?";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(0, employee_id);
					
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next()) {
						employee = new Employee();
						employee.setemployee_id(resultSet.getInt("id"));
						employee.setfirst_name(resultSet.getString("first_name"));
						employee.setlast_name(resultSet.getString("last_name"));
						employee.setUsername(resultSet.getString("username"));
						employee.setPassword(resultSet.getString("password"));
						employee.setmanager_id(resultSet.getInt("manager_id"));
						employee.setdept_id(resultSet.getInt("dept_id"));
						
						//RequestDAO requestDAO = DAOFactory.getRequestDAO();
						//employee.setRequests(requestDAO.getById(employee_id));
				
				}
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
		return employee_id;
	}

	@Override
	public Employee getByUsername(String username) {
		Employee employee = null;
		try(Connection connection = ConnectionFactory.getConnection()){
			String sql = "Select * from employee left join dept_id on department.id=department.dept_id where username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(3, username);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee = EmployeeDAOImpl.parseResultSet(resultSet);
			}else {
				System.out.println("something went wrong getting username");
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return employee;
		
	}

	private static Employee parseResultSet(ResultSet resultSet) {
		Employee employee = new Employee();
		try {
			employee.setemployee_id(resultSet.getInt(0));
			employee.setfirst_name(resultSet.getString(1));
			employee.setlast_name(resultSet.getString(2));
			employee.setUsername(resultSet.getString(3));
			employee.setPassword(resultSet.getString(4));
			employee.setmanager_id(resultSet.getInt(5));
			employee.setdept_id(resultSet.getInt(6));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	

	
	
	
}
