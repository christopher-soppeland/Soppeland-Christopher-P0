package com.revature.expenseReimburesment.dl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.expenseReimburesment.models.Reimbursement;

public class RefundDAO implements DAO<Reimbursement, Integer, String> {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public Reimbursement findById(Integer id) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from expenses where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Reimbursement(rs.getInt("id"), rs.getString("type"), rs.getDouble("amount"),
						rs.getString("status"), rs.getString("description"), rs.getInt("employee_Id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error connecting to DB");
		}
		return null;
	}

	@Override
	public List<Reimbursement> findByEmpId(Integer empId) {
		ArrayList<Reimbursement> refunds = new ArrayList<Reimbursement>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from expenses where employee_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				refunds.add(new Reimbursement(rs.getInt("id"), rs.getString("type"), rs.getDouble("amount"),
						rs.getString("status"), rs.getString("description"), rs.getInt("employee_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error connecting to DB");
		}
		return refunds;
	}

	@Override
	public List<Reimbursement> findAll() {
		// TODO Auto-generated method stub

		ArrayList<Reimbursement> refunds = new ArrayList<Reimbursement>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from expenses";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);

			while (rs.next()) {
				refunds.add(new Reimbursement(rs.getInt("id"), rs.getString("type"), rs.getDouble("amount"),
						rs.getString("status"), rs.getString("description"), rs.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Something went wrong", e);
		}
		return refunds;
	}

	@Override
	public void add(Reimbursement newObject) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "insert into expenses (type, status, description, amount, employee_id) values (?,?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newObject.getType());
			pstmt.setString(2, newObject.getStatus());
			pstmt.setString(3, newObject.getDescription());
			pstmt.setDouble(4, newObject.getAmount());
			pstmt.setInt(5, newObject.getEmpId());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Reimbursement newObject) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "update expenses set status = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newObject.getStatus());
			pstmt.setInt(2, newObject.getId());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Reimbursement> findByStatus(String status) {
		// TODO Auto-generated method stub
		ArrayList<Reimbursement> refunds = new ArrayList<Reimbursement>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from expenses where status = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				refunds.add(new Reimbursement(rs.getInt("id"), rs.getString("type"), rs.getDouble("amount"),
						rs.getString("status"), rs.getString("description"), rs.getInt("employee_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return refunds;
	}

}
