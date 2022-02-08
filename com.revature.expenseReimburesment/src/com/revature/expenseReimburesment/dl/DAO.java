package com.revature.expenseReimburesment.dl;

import java.util.List;

import com.revature.expenseReimburesment.models.Reimbursement;

public interface DAO<T,K,I> {
	T findById(K id);
	List<T> findAll();
	void add(T newObject);
	void update(T newObject);
	List<Reimbursement> findByEmpId(K empId);
	List<T> findByStatus(String status);
}
