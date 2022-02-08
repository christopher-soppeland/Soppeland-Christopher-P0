package com.revature.expenseReimburesment.controllers;

import com.revature.expenseReimburesment.bl.RefundBL;
import com.revature.expenseReimburesment.models.Reimbursement;

import io.javalin.http.Handler;

public class RefundController implements IController {

	private RefundBL refundBL;

	public RefundController(RefundBL refundBL)
	{
		this.refundBL = refundBL;
	}

	@Override
	public Handler getAll() {
		// TODO Auto-generated method stub
		return ctx -> {
			// marshalling my list of requests to a json format
			// jsonStream() sets the response body to json
			ctx.jsonStream(refundBL.getRefunds());
			ctx.status(201);
		};
	}

	@Override
	public Handler getById() {
		// TODO Auto-generated method stub
		return ctx -> {
			//get id of employee we want from the path param, 
			// we extract it from the ctx 
			String id = ctx.pathParam("refund_id");
			int actualId = Integer.parseInt(id);
			try {
				ctx.jsonStream(refundBL.getRefundById(actualId));
				ctx.status(201);
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
			Reimbursement newReimbursement = ctx.bodyStreamAsClass(Reimbursement.class);
			try {
				refundBL.addReimbursement(newReimbursement);
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
		return ctx -> {
			Integer refundId = Integer.parseInt(ctx.pathParam("refund_id"));
			String status = ctx.pathParam("status");
			Reimbursement foundRequest = refundBL.getRefundById(refundId);
			foundRequest.setStatus(status);
			refundBL.changeStatus(foundRequest);
			ctx.status(201);
		};
	}

	@Override
	public Handler getByStatus() {
		// TODO Auto-generated method stub
		return ctx -> {
			String status = ctx.pathParam("status");
			ctx.jsonStream(refundBL.getRefundByStatus(status));
			ctx.status(201);
		};
	}

	@Override
	public Handler getByEmpId() {
		// TODO Auto-generated method stub
		return ctx -> {
			Integer empId = Integer.parseInt(ctx.pathParam("employee_id"));
			ctx.jsonStream(refundBL.getRefundByEmpId(empId));
			ctx.status(201);
		};
	}

}
