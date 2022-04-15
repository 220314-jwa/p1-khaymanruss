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
			String sql = "INSERT into employee (employee_id, first_name, last_name, username, password, manager_id, dept_id)"
					+ " values (default, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(4, newObj.getusername());
			preparedStatement.setString(5,  newObj.getpassword());
			preparedStatement.setString(2, newObj.getfirstName());
			preparedStatement.setString(3, newObj.getlastName());
			preparedStatement.setInt(6, newObj.getmanagerId());
			DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
			preparedStatement.setInt(7, departmentDAO.getByDeptId(newObj.getdeptId()));
			
			connection.setAutoCommit(false);
			preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				newObj.setemployeeId(resultSet.getInt(1));
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
		return newObj.getemployeeId();
	}

	@Override
	public Employee getById(int employeeId) {
		Employee employee = null;
		try(Connection connection = ConnectionFactory.getConnection()){
			String sql = "SELECT * from employee left join dept_id on department.id=department.dept_id WHERE employee_id = ?";
					//research foreign keys in relation to request_id and employee_id as primary keys\
					//refer to her postgresSQL table pet_owner and how it references the person and pet primary keys
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
//				employee = new Employee();
//				employee.setemployee_id(employee_id);
//				employee.setfirst_name(resultSet.getString("first_name"));
//				employee.setlast_name(resultSet.getString("last_name"));
//				employee.setUsername(resultSet.getString("username"));
//				employee.setPassword(resultSet.getString("password"));
//				employee.setmanager_id(resultSet.getInt("manager_id"));
//				employee.setdept_id(resultSet.getInt("dept_id"));
				employee = parseResultSet(resultSet);
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
		String sql = "SELECT * from employee";
		try(Connection connection = ConnectionFactory.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//Statement statement = connection.createStatement();
			
			ResultSet resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
//				Employee employee = new Employee();
//				employee.setemployee_id(resultSet.getInt("employee_id"));
//				employee.setfirst_name(resultSet.getString("first_name"));
//				employee.setlast_name(resultSet.getString("last_name"));
//				employee.setUsername(resultSet.getString("username"));
//				employee.setPassword(resultSet.getString("password"));
//				employee.setmanager_id(resultSet.getInt("manager_id"));
//				employee.setdept_id(resultSet.getInt("dept_id"));
				Employee employee = parseResultSet(resultSet);
				employees.add(employee);
				
				//RequestDAO requestDAO = DAOFactory.getRequestDAO();
				//employee.setRequests(requestDAO.getById(employee));
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
			preparedStatement.setString(3, updateObj.getusername());
			preparedStatement.setString(4, updateObj.getpassword());
			preparedStatement.setString(1, updateObj.getfirstName());
			preparedStatement.setString(2, updateObj.getlastName());
			preparedStatement.setInt(5, updateObj.getmanagerId());
			preparedStatement.setInt(6, updateObj.getdeptId());
			
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
			preparedStatement.setInt(1, objToDelete.getemployeeId());
			
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
	public Employee getByEmployeeId(String username) {
		Employee employee = null;
		String sql = "SELECT * from employee left join dept_id on department.id=department.dept_id WHERE employee_username = ?";
		try(Connection connection = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
//				employee = new Employee();
//				employee.setemployee_id(resultSet.getInt("id"));
//				employee.setfirst_name(resultSet.getString("first_name"));
//				employee.setlast_name(resultSet.getString("last_name"));
//				employee.setUsername(resultSet.getString("username"));
//				employee.setPassword(resultSet.getString("password"));
//				employee.setmanager_id(resultSet.getInt("manager_id"));
//				employee.setdept_id(resultSet.getInt("dept_id"));
				employee = parseResultSet(resultSet);
				//RequestDAO requestDAO = DAOFactory.getRequestDAO();
				//employee.setRequests(requestDAO.getByUsername(employee));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public int getByUsername(int employeeId) {
		Employee employee = null;
		String sql = "Select * from employee where username = ?";
		//left join dept_id on department.id=department.dept_id
				try(Connection connection = ConnectionFactory.getConnection()){
					
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, employeeId);
					
					ResultSet resultSet = preparedStatement.executeQuery();
					if(resultSet.next()) {
//						employee = new Employee();
//						employee.setemployee_id(resultSet.getInt("id"));
//						employee.setfirst_name(resultSet.getString("first_name"));
//						employee.setlast_name(resultSet.getString("last_name"));
//						employee.setUsername(resultSet.getString("username"));
//						employee.setPassword(resultSet.getString("password"));
//						employee.setmanager_id(resultSet.getInt("manager_id"));
//						employee.setdept_id(resultSet.getInt("dept_id"));
						employee = parseResultSet(resultSet);
						
						//RequestDAO requestDAO = DAOFactory.getRequestDAO();
						//employee.setRequests(requestDAO.getById(employee_id));
				
				}
					
				}catch (SQLException e) {
					e.printStackTrace();
				}
		return employeeId;
	}

	@Override
	public Employee getByUsername(String username) {
		Employee employee = null;
		String sql = "Select * from employee where username = ?";
		//left join dept_id on employee.dept_id=department.dept_id 
		try(Connection connection = ConnectionFactory.getConnection()){
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, username);
			
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
			employee.setemployeeId(resultSet.getInt(1));
			employee.setfirstName(resultSet.getString(2));
			employee.setlastName(resultSet.getString(3));
			employee.setusername(resultSet.getString(4));
			employee.setpassword(resultSet.getString(5));
			employee.setmanagerId(resultSet.getInt(6));
			employee.setdeptId(resultSet.getInt(7));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	

	
	
	
}
