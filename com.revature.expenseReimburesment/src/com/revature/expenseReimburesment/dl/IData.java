package com.revature.expenseReimburesment.dl;

import java.util.List;

import com.revature.expenseReimburesment.models.Employee;
import com.revature.expenseReimburesment.models.Reimbursement;

public interface IData {
	
	void addReimbursement(Reimbursement newReimbursement);
	List<Reimbursement> getRefunds();
	Reimbursement getRefundById(int id) throws Exception;
	void changeStatus(Reimbursement reimbursement);
	List<Employee> getEmployees();
	Employee getEmpById(int id) throws Exception;
	void addEmployee(Employee employee);
	List<Reimbursement> getRefundByStatus(String status) throws Exception;
	List<Reimbursement> getRefundByEmpId(Integer empId) throws Exception;
}