package net.revature.app;

import io.javalin.Javalin;
import net.revature.data.DAOFactory;
import net.revature.data.EmployeeDAO;
import net.revature.data.RequestDAO;
import net.revature.models.Employee;
import net.revature.models.Request;
import net.revature.services.ConnectionFactory;

public class ReimbursementApp {

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
	
	app.get("/employee", ctx ->{
		Employee employee = new Employee();
		ctx.json(employee);
	});
		
		app.post("/employee/id", ctx ->{
			Employee employee = ctx.bodyAsClass(net.revature.models.Employee.class);
			EmployeeDAO employeeDAO = DAOFactory.getEmployeeDAO();
			int employee_id = employeeDAO.create(employee);
			ctx.result("the employee id is:" + employee_id);
		});
	}
}
