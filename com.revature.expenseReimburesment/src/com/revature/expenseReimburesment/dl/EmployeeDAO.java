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

import com.revature.expenseReimburesment.models.Employee;

public class EmployeeDAO implements DAO<Employee, Integer>{
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public Employee findById(Integer id) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from employees where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Employee(
						rs.getInt("id"),
						rs.getBoolean("is_manager"),
						rs.getString("name"),
						rs.getInt("manager_id")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()){
			String query = "select * from employees";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				employees.add(new Employee(
						rs.getInt("id"),
						rs.getBoolean("is_manager"),
						rs.getString("name"),
						rs.getInt("manager_id")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Something went wrong", e);
		}
		return employees;
	}

	@Override
	public void add(Employee newObject) {
		// TODO Auto-generated method stub
		try(Connection conn= ConnectionFactory.getInstance().getConnection())
		{
			String query = "insert into employees (name, is_manager, manager_id) values (?,?);";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newObject.getName());
			pstmt.setBoolean(2, newObject.isManager());
			pstmt.setInt(3, newObject.getManId());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Employee newObject) {
		// TODO Auto-generated method stub
		
	}

}
