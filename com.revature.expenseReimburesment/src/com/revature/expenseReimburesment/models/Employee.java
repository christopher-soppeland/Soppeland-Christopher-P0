package com.revature.expenseReimburesment.models;

public class Employee {

	private boolean manager = false;
	private int empId;
	private String name;
	private int manId;

	public Employee() {

	}

	public Employee(int empId) {
		this.empId = empId;
	}

	public Employee(int empId, boolean manager) {
		this(empId);
		this.manager = manager;
	}

	public Employee(int empId, boolean manager, String name) {
		// this(empId, manager);
		this.empId = empId;
		this.manager = manager;
		this.name = name;
	}

	public Employee(int empId, boolean manager, String name, int manId) {
		this(empId, manager, name);
		this.manId = manId;
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

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

	@Override
	public String toString() {
		String emp = "Employee [employee id=" + empId + ", manager=" + manager + ", name=" + name;
		if (manId != 0) {
			emp += ", manager id=" + manId;
		}
		emp += "]";

		return emp;
	}

}
