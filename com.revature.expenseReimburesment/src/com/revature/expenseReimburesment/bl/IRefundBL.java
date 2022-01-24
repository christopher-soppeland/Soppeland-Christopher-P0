package com.revature.expenseReimburesment.bl;

import java.util.List;

import com.revature.expenseReimburesment.models.Reimbursement;

public interface IRefundBL {
	
	void addReimbursement(Reimbursement newReimbursement);
	List<Reimbursement> getRefunds();
	Reimbursement getRefundById(int id) throws Exception;
	void changeStatus(Reimbursement reimbursement) throws Exception;

}
