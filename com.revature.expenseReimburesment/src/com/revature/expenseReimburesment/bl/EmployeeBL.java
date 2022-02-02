package com.revature.expenseReimburesment.bl;

import java.util.List;

import com.revature.expenseReimburesment.dl.IData;
import com.revature.expenseReimburesment.models.Employee;

public class EmployeeBL {

	private IData repo;

	public EmployeeBL(IData repo)
	{
		this.repo = repo;
	}
	
	public List<Employee> getEmployees()
	{
		return repo.getEmployees();
	}

	public Employee getEmpById(int id) throws Exception {
		// TODO Auto-generated method stub
		return repo.getEmpById(id);
	}
	
	public void addEmployee(Employee employee) {
		repo.addEmployee(employee);
	}

}
