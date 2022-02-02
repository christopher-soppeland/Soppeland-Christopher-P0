package com.revature.expenseReimburesment.utils;

import com.revature.expenseReimburesment.models.Employee;
import com.revature.expenseReimburesment.models.Reimbursement;

import io.javalin.plugin.openapi.dsl.OpenApiBuilder;
import io.javalin.plugin.openapi.dsl.OpenApiDocumentation;

public class DocumentationFactory {
	
	public static OpenApiDocumentation getDoc(String endPoint)
	{
		switch (endPoint)
		{
		case "getEmployees":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("Employee");
			}).jsonArray("200", Employee.class);
		case "getEmployeeById":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("Employee");
			}).json("200", Employee.class);
		case "addEmployee":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("Employee");
			}).body(Employee.class).result("201");
		case "addReimbursement":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("Reimburesment");
			}).body(Reimbursement.class).result("201");
		case "changeStatus":
			return OpenApiBuilder.document().operation(op -> 
			{
				op.addTagsItem("Reimburesment");
			}).pathParam("status", String.class).result("204");
		case "getRefunds":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("Reimburesment");
			}).json("200", Reimbursement.class);
		case "getRefundById":
			return OpenApiBuilder.document().operation(op -> {
				op.addTagsItem("Reimburesment");
			}).json("200", Reimbursement.class);
		default:
			return null;
		}
	}

}
