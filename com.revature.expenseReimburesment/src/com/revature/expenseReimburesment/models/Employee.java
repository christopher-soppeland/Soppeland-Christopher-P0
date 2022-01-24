package com.revature.expenseReimburesment.models;

public class Employee {

	private boolean manager = false;
	private int empId;
	private String name;

	public Employee() {

	}

	/*public Employee(int empId) {
		this.empId = empId;
	}

	public Employee(int empId, boolean manager) {
		this(empId);
		this.manager = manager;
	}*/

	public Employee(int empId, boolean manager, String name) {
		//this(empId, manager);
		this.empId = empId;
		this.manager = manager;
		this.name = name;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Employee [employee id=" + empId + ", manager=" + manager + ", name=" + name + "]";
	}

}
