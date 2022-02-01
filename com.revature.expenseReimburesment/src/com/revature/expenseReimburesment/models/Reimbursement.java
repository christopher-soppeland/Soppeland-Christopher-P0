package com.revature.expenseReimburesment.models;

import javax.swing.JTable;

public class Reimbursement {

	private int id;
	private String type;
	private double amount;
	private String status = "PENDING";
	private String description;
	private int empId;

	public Reimbursement() {
		this(0, "No type", 0.0);
	}

	public Reimbursement(String type, double amount) {
		// TODO Auto-generated constructor stub
		// this.id = id;
		this.type = type;
		this.amount = amount;
	}

	public Reimbursement(int id, String type, double amount) {
		// Calling an existing constructor of the same class
		this(type, amount);
		this.id = id;
	}

	public Reimbursement(int id, String type, double amount, String status) {
		// Calling an existing constructor of the same class
		this(id, type, amount);
		this.status = status;
	}

	public Reimbursement(int id, String type, double amount, String status, String description) {
		// Calling an existing constructor of the same class
		this(id, type, amount, status);
		this.description = description;
	}
	
	public Reimbursement(int id, String type, double amount, String status, String description, int empId) {
		// Calling an existing constructor of the same class
		this(id, type, amount, status, description);
		this.empId = empId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		String output = "Reimbursement [";
		if(id != 0) {
			output += "id=" + id + ", "; 
		}
		output += "type=" + type + ", amount=" + amount + ", status=" + status
				+ ", description=" + description + ", employee id=" + empId + "]";
		
		JTable table = new JTable();
		return output;
	}

}
