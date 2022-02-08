package com.revature.expenseReimburesment.dl;

import java.util.List;

import com.revature.expenseReimburesment.models.Employee;
import com.revature.expenseReimburesment.models.Reimbursement;

public class DBRepository implements IData{
	
	private DAO<Reimbursement, Integer, String> refundDAO;
	private DAO<Employee, Integer, String> employeeDAO;
	
	public DBRepository(DAO<Reimbursement,Integer,String> refundDAO, DAO<Employee,Integer,String> employeeDAO) {
		this.refundDAO = refundDAO;
		this.employeeDAO = employeeDAO;
	}

	@Override
	public void addReimbursement(Reimbursement newReimbursement) {
		// TODO Auto-generated method stub
		refundDAO.add(newReimbursement);
	}

	@Override
	public List<Reimbursement> getRefunds() {
		// TODO Auto-generated method stub
		return refundDAO.findAll();
	}

	@Override
	public Reimbursement getRefundById(int id) throws Exception {
		// TODO Auto-generated method stub
		return refundDAO.findById(id);
	}

	@Override
	public void changeStatus(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		refundDAO.update(reimbursement);
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employeeDAO.findAll();
	}

	@Override
	public Employee getEmpById(int id) throws Exception {
		// TODO Auto-generated method stub
		return employeeDAO.findById(id);
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDAO.add(employee);
	}

	@Override
	public List<Reimbursement> getRefundByStatus(String status) throws Exception {
		// TODO Auto-generated method stub
		return refundDAO.findByStatus(status);
	}

	@Override
	public List<Reimbursement> getRefundByEmpId(Integer empId) throws Exception {
		// TODO Auto-generated method stub
		return refundDAO.findByEmpId(empId);
	}
	

}
