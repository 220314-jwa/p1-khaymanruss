package net.revature.app;

import java.util.Map;

import com.revature.exceptions.IncorrectCredentialsException;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import net.revature.data.DAOFactory;
import net.revature.data.DepartmentDAO;
import net.revature.data.EmployeeDAO;
import net.revature.data.RequestDAO;
import net.revature.models.Department;
import net.revature.models.Employee;
import net.revature.models.Request;
import net.revature.services.ConnectionFactory;
import net.revature.services.UserService;
import net.revature.services.UserServiceImpl;

public class ReimbursementApp {
	
	private static UserService userServ = new UserServiceImpl();

	public static void main(String[] args) {
		Javalin app;
		//set up javalin object
		app = Javalin.create();
		
		app.start(8081);
		
		//app.get("/form", )
		app.post("/request", ctx ->{
			Request request = ctx.bodyAsClass(Request.class);
			RequestDAO requestDAO = DAOFactory.getRequestDAO();
			requestDAO.create(request);
			System.out.println(request);
			ctx.result("the request is working");
	});
		
		app.post("/auth", ctx -> {
			Map<String, String> credentials = ctx.bodyAsClass(Map.class);
			String username = credentials.get("username");
			String password = credentials.get("password");
			
			try {
				Employee employee = userServ.logIn(username, password);
				ctx.json(employee);
			}catch (IncorrectCredentialsException e) {
				ctx.status(HttpCode.UNAUTHORIZED);
			}
		});
	
	app.get("/employee", ctx ->{
		Employee employee = new Employee();
		ctx.json(employee);
	});
	app.post("/department", ctx ->{
		Department department = ctx.bodyAsClass(net.revature.models.Department.class);
		System.out.println(department);
		DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
		departmentDAO.create(department);
		System.out.println(department);
	});
	
	app.get("/department/{id}", ctx ->{
		int dept_id = Integer.parseInt(ctx.pathParam("id"));
		DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
		Department department = departmentDAO.getById(dept_id);
		ctx.json(department);
	});
		
		app.post("/employee", ctx ->{
			Employee employee = ctx.bodyAsClass(net.revature.models.Employee.class);
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			int employee_id = employeeDAO.create(employee);
			ctx.result("the employee id is:" + employee_id);
		});
	}
}
