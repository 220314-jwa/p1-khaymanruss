package net.revature.app;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.RequestAlreadySubmittedException;

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
		
		//set up javalin object
		Javalin app = Javalin.create((config) ->{
			config.enableCorsForAllOrigins();});

		
		app.start(8081);
		
		app.post("/submit", ctx ->{
			try {
				
				Request newRequest = ctx.bodyAsClass(Request.class);
				
				newRequest = userServ.submitRequest(newRequest);
				ctx.json(newRequest);
			}catch (RequestAlreadySubmittedException e) {
			
				e.printStackTrace();
			
			}
		});
		
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
			System.out.println(username);
			String password = credentials.get("password");
			System.out.println(password);
			
				Employee employee = userServ.logIn(username, password);
				if(employee != null) {
				ctx.json(employee);
				}else {
					
				ctx.status(HttpCode.UNAUTHORIZED);
			}
		});
		
//		app.post("/login", ctx -> {
//			Map<String, String> credentials = ctx.bodyAsClass(Map.class);
//			String username = credentials.get("username");
//			System.out.println(username);
//			String password = credentials.get("password");
//			System.out.println(password);
//
//			Employee employee = userService.logIn(username, password);
//
//			if (employee != null) {
//				ctx.json(employee);
//			} else {
//				ctx.status(HttpCode.UNAUTHORIZED);
//			}
//		});

	
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
		int deptId = Integer.parseInt(ctx.pathParam("id"));
		DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAO();
		Department department = departmentDAO.getById(deptId);
		ctx.json(department);
	});
	app.get("/requests", ctx->{
		List<Request> requests = userServ.viewRequests();
		ctx.json(requests);
	});
		

	
	
	
		app.post("/employee", ctx ->{
			Employee employee = ctx.bodyAsClass(net.revature.models.Employee.class);
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			int employeeId = employeeDAO.create(employee);
			ctx.result("the employee id is:" + employeeId);
		});
	}
}
