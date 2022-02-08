package com.revature.expenseReimburesment;

import com.revature.expenseReimburesment.bl.EmployeeBL;
import com.revature.expenseReimburesment.bl.RefundBL;
import com.revature.expenseReimburesment.controllers.EmployeeController;
import com.revature.expenseReimburesment.controllers.IController;
import com.revature.expenseReimburesment.controllers.RefundController;
import com.revature.expenseReimburesment.dl.DBRepository;
import com.revature.expenseReimburesment.dl.EmployeeDAO;
import com.revature.expenseReimburesment.dl.RefundDAO;
import com.revature.expenseReimburesment.utils.Router;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;

public class RESTDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IController employeeController = new EmployeeController(
				new EmployeeBL(new DBRepository(new RefundDAO(), new EmployeeDAO())));
		IController refundController = new RefundController(
				new RefundBL(new DBRepository(new RefundDAO(), new EmployeeDAO())));
		Javalin app = Javalin.create(config -> {
			config.registerPlugin(new OpenApiPlugin(getOpenApiOptions()));
			config.enableCorsForAllOrigins();
		}).start(7001);
		Router router = new Router(app, employeeController, refundController);
		router.setUpEndPoints();
	}

	private static OpenApiOptions getOpenApiOptions() {
		// Configuring swagger
		// We'll use swagger for documentation and testing our API
		Info applicationInfo = new Info().version("1.0").description("Reimburesment System REST");
		return new OpenApiOptions(applicationInfo).path("/reimburesment-docs")
				.swagger(new SwaggerOptions("/Reimbursements").title("Reimburesment System API Docs"));
	}

}
