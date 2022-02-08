package com.revature.expenseReimburesment.utils;

import com.revature.expenseReimburesment.controllers.IController;

import io.javalin.Javalin;
import io.javalin.plugin.openapi.dsl.OpenApiBuilder;

public class Router {
	
	private Javalin app;
	private IController employeeController;
	private IController refundController;
	public Router(Javalin app, IController employeeController, IController refundController)
	{
		this.app = app;
		this.employeeController = employeeController;
		this.refundController = refundController;
	}
	
	public void setUpEndPoints() {
		app.get("/Employees", OpenApiBuilder.documented(DocumentationFactory.getDoc("getEmployees"), employeeController.getAll()));
		app.get("/Employees/{employee_id}",  OpenApiBuilder.documented(DocumentationFactory.getDoc("getEmployeeById"), employeeController.getById()));
		app.post("/AddEmployee",  OpenApiBuilder.documented(DocumentationFactory.getDoc("addEmployee"), employeeController.add()));
		app.get("/Requests", OpenApiBuilder.documented(DocumentationFactory.getDoc("getRefunds"), refundController.getAll()));
		app.post("/AddRequest", OpenApiBuilder.documented(DocumentationFactory.getDoc("addReimbursement"), refundController.add()));
		app.put("/UpdateRequest/{refund_id}/{status}", OpenApiBuilder.documented(DocumentationFactory.getDoc("changeStatus"), refundController.update()));
		app.get("/RequestsByStatus/{status}", OpenApiBuilder.documented(DocumentationFactory.getDoc("getRefundByStatus"), refundController.getByStatus()));
		app.get("/RequestsByEmployee/{employee_id}", OpenApiBuilder.documented(DocumentationFactory.getDoc("getByEmpId"), refundController.getByEmpId()));
		app.get("/RequestsById/{refund_id}", OpenApiBuilder.documented(DocumentationFactory.getDoc("getRefundById"), refundController.getById()));
	}

}
