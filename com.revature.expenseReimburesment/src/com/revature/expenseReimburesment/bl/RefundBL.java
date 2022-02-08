package com.revature.expenseReimburesment.bl;

import java.util.List;

import com.revature.expenseReimburesment.dl.IData;
import com.revature.expenseReimburesment.models.Reimbursement;

public class RefundBL implements IRefundBL {

	private IData repo;

	public RefundBL(IData repo) {
		this.repo = repo;
	}

	@Override
	public void addReimbursement(Reimbursement newReimbursement) {
		// TODO Auto-generated method stub
		repo.addReimbursement(newReimbursement);

	}

	public List<Reimbursement> getRefunds() {
		return repo.getRefunds();
	}

	@Override
	public Reimbursement getRefundById(int id) throws Exception {
		// TODO Auto-generated method stub
		return repo.getRefundById(id);
	}

	public void changeStatus(Reimbursement reimbursement) throws Exception {

		repo.changeStatus(reimbursement);

	}

	public List<Reimbursement> getRefundByStatus(String status) throws Exception {
		return repo.getRefundByStatus(status);
	}
	
	public List<Reimbursement> getRefundByEmpId(Integer empId) throws Exception {
		return repo.getRefundByEmpId(empId);
	}

}
