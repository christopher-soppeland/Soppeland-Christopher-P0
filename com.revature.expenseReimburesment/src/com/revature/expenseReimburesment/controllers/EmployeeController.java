package com.revature.expenseReimburesment.controllers;

import com.revature.expenseReimburesment.bl.EmployeeBL;
import com.revature.expenseReimburesment.models.Employee;

import io.javalin.http.Handler;

public class EmployeeController implements IController {

	private EmployeeBL employeeBL;

	public EmployeeController(EmployeeBL employeeBL)
	{
		this.employeeBL = employeeBL;
	}

	@Override
	public Handler getAll() {
		// TODO Auto-generated method stub
		return ctx -> {
			// marshalling my list of employees to a json format
			// jsonStream() sets the response body to json
			ctx.jsonStream(employeeBL.getEmployees());
		};
	}

	@Override
	public Handler getById() {
		// TODO Auto-generated method stub
		return ctx -> {
			//get id of employee we want from the path param, 
			// we extract it from the ctx 
			String id = ctx.pathParam("employee_id");
			int actualId = Integer.parseInt(id);
			try {
				ctx.jsonStream(employeeBL.getEmpById(actualId));
			} catch (NullPointerException ex)
			{
				ctx.status(204);
			}
			
		};
	}

	@Override
	public Handler add() {
		// TODO Auto-generated method stub
		return ctx -> {
			// unmarshall my request body into an issue class
			// bodyAsClass method unmarshalls the request body into the structure of the class you input into it
			// transforms the request body as the specified class
			Employee newEmployee = ctx.bodyStreamAsClass(Employee.class);
			try {
				employeeBL.addEmployee(newEmployee);
				ctx.status(201);
			} catch (Exception e)
			{
				ctx.status(400);
			}
			
			
		};
	}

	@Override
	public Handler update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Handler getByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Handler getByEmpId() {
		// TODO Auto-generated method stub
		return null;
	}

}
