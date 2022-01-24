package com.revature.expenseReimburesment.models;

public class Reimbursement {
	
	private int id;
	private String type;
	private double amount;
	private String status = "PENDING";

	public Reimbursement () {
		this(0, "No type", 0.0);
	}

	public Reimbursement(String type, double amount) {
		// TODO Auto-generated constructor stub
		//this.id = id;
		this.type = type;
		this.amount = amount;
	}
	
	public Reimbursement(int id, String type, double amount)
	{
		// Calling an existing constructor of the same class
		this(type, amount);
		this.id = id;
	}
	
	public Reimbursement(int id, String type, double amount, String status)
	{
		// Calling an existing constructor of the same class
		this(id, type, amount);
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", type=" + type + ", amount=" + amount + ", status=" + status + "]";
	}

}
