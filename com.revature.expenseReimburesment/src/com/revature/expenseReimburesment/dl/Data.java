package com.revature.expenseReimburesment.dl;

import java.util.ArrayList;
import java.util.List;

import com.revature.expenseReimburesment.models.Employee;
import com.revature.expenseReimburesment.models.Reimbursement;

public class Data implements IData{
	
	private static ArrayList<Reimbursement> listOfRequests;
	private static ArrayList<Employee> listOfEmployees;
	private static int latestId;
	
	public Data() {
		// seeding my list of issues with dummy data
		//Reimbursement dummy data
		listOfRequests = new ArrayList<Reimbursement>(){{
			add(new Reimbursement(1, "Lodging", 200.00, "PENDING", "hotel", 1002));
			add(new Reimbursement(2, "Food", 12.00, "PENDING", "dinner", 1001));
		}};
		latestId = 3;
		
		//Employee dummy data
		listOfEmployees = new ArrayList<Employee>(){{
			add(new Employee(1002, false, "Bill Stearn", 1001));
			add(new Employee(1001, true, "Bobby Axelrod"));
		}};
	}
	
	public void addReimbursement(Reimbursement newReimbursement) {
		// TODO Auto-generated method stub
		newReimbursement.setId(latestId);
		listOfRequests.add(newReimbursement);
		latestId++;
	}
	
	public ArrayList<Reimbursement> getReimbursements() {
		// TODO Auto-generated method stub
		return listOfRequests;
	}

	@Override
	public List<Reimbursement> getRefunds() {
		// TODO Auto-generated method stub
		return listOfRequests;
	}

	@Override
	public Reimbursement getRefundById(int id) throws Exception {
		// TODO Auto-generated method stub
		Reimbursement foundRequest = null;
		for(Reimbursement request:listOfRequests)
		{
			if(request.getId() == id)
			{
				foundRequest = request;
			}
		}
		if(foundRequest == null) throw new Exception("Request not found");
		return foundRequest;
	}

	@Override
	public void changeStatus(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return listOfEmployees;
	}

	@Override
	public Employee getEmpById(int id) throws Exception {
		// TODO Auto-generated method stub
		Employee foundEmployee = null;
		for(Employee employee:listOfEmployees)
		{
			if(employee.getEmpId() == id)
			{
				foundEmployee = employee;
			}
		}
		if(foundEmployee == null) throw new Exception("Employee not found");
		return foundEmployee;
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		//return employee;
	}

}
