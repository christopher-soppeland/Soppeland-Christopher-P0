package com.revature.expenseReimburesment.ui;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.revature.expenseReimburesment.bl.EmployeeBL;
import com.revature.expenseReimburesment.bl.IRefundBL;
import com.revature.expenseReimburesment.bl.RefundBL;
import com.revature.expenseReimburesment.models.Employee;
import com.revature.expenseReimburesment.models.Reimbursement;

public class MainMenu {

	private Scanner myScanner;
	private String userInput;
	private IRefundBL refundBL;
	private EmployeeBL employeeBL;
	private Employee employee;
	private Reimbursement foundRequest;
	private String output;
	private String title = "Reimbursement System";

	public MainMenu(Scanner myScanner, IRefundBL refundBL, EmployeeBL employeeBL) {
		this.myScanner = myScanner;
		this.refundBL = refundBL;
		this.employeeBL = employeeBL;
	}

	public void start() {
		// method signature : access modifier*, non access modifier*, return type,
		// methodName, (arguments), throws exceptions*
		boolean keepGoing = true;
		
		do {
			// System.out.println("Reimbursement System");

			while (employee == null & keepGoing) {

				/*
				 * System.out.println("What is your employee id?"); userInput =
				 * myScanner.nextLine();
				 */

				output = "What is your employee id?";
				userInput = JOptionPane.showInputDialog(null, output, title, 1);
				if (userInput != null) {
					try {
						employee = employeeBL.getEmpById(Integer.parseInt(userInput));
						// System.out.println(employee);
						JOptionPane.showMessageDialog(null, employee, title, 1);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						// System.out.println("Please only enter numerics");
						JOptionPane.showMessageDialog(null, "Please only enter numerics", title, 0);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						// System.out.println("No such employee found, try another id");
						JOptionPane.showMessageDialog(null, "No such employee found, try another id", title, 0);
					}
				} else {
					keepGoing = false;
					break;
				}
			}

			if (employee != null) {
				//System.out.println("What do you want do?");
				output = "What do you want do?\n\n";
				//System.out.println("[0] Submit Reimbursement Request");
				output += "[0] Submit Reimbursement Request\n";
				
				if (employee.isManager()) {
					//System.out.println("[1] View Reimbursement Requests");
					output += "[1] View Reimbursement Requests\n";
					//System.out.println("[2] Update Reimbursement Request");
					output += "[2] Update Reimbursement Request\n";
					//System.out.println("[3] View All Employees");
					output += "[3] View All Employees\n";
				} else {
					//System.out.println("[1] View Your Reimbursement Requests");
					output += "[1] View Your Reimbursement Requests\n";
				}
				System.out.println("[x] Exit");
				output += "[x] Exit\n";

				//userInput = myScanner.nextLine();
				userInput = JOptionPane.showInputDialog(null, output, title, 1);
				
				switch (userInput) {
				case "0":
					createRequest();
					break;
				case "1":
					getRefunds();
					break;
				case "2":
					getSpecificRequest();
					break;
				case "3":
					getEmployees();
					break;
				case "x":
					//System.out.println("Goodbye");
					JOptionPane.showMessageDialog(null, "Goodbye", title, 1);
					keepGoing = false;
					break;
				default:
					//System.out.println("Sorry wrong input, please try again");
					JOptionPane.showMessageDialog(null, "Sorry wrong input, please try again", title, 0);
					break;
				}
			} else {
				keepGoing = false;
				break;
			}

		} while (keepGoing);

	}

	private void createRequest() {
		// TODO Auto-generated method stub
		String type = "";
		int id = 0;
		double amount = 0;

		boolean keepGoing = true;
		do {
			//System.out.println("What type of expense is this for?");
			output = "What type of expense is this for?\n";
			//System.out.println("[1] Lodging");
			output += "[1] Lodging\n";
			//System.out.println("[2] Travel");
			output += "[2] Travel\n";
			//System.out.println("[3] Food");
			output += "[3] Food\n";
			//System.out.println("[4] Other");
			output += "[4] Other\n";
			//System.out.println("[x] Exit");
			output += "[x] Exit\n";
			//userInput = myScanner.nextLine();
			userInput = JOptionPane.showInputDialog(null, output, title, 1);
			
			switch (userInput) {
			case "1":
				type = "Lodging";
				break;
			case "2":
				type = "Travel";
				break;
			case "3":
				type = "Food";
				break;
			case "4":
				type = "Other";
				break;
			case "x":
				//System.out.println("Goodbye");
				JOptionPane.showMessageDialog(null, "Goodbye", title, 1);
				keepGoing = false;
				break;
			default:
				//System.out.println("Sorry wrong input, please try again");
				JOptionPane.showMessageDialog(null, "Sorry wrong input, please try again", title, 0);
				break;
			}

			if (keepGoing) {
				//System.out.println("How much was the expense?: ");
				//userInput = myScanner.nextLine();
				output = "How much was the expense?:\n";
				userInput = JOptionPane.showInputDialog(null, output, title, 1);
				try {
					amount = Double.parseDouble(userInput);
					keepGoing = false;
				} catch (NumberFormatException e1) {
					//System.out.println("Please enter numbers only.");
					JOptionPane.showMessageDialog(null, "Please enter numbers only.", title, 0);
				}
			}

			Reimbursement newReimbursement = new Reimbursement(id, type, amount);
			// saving to storage
			refundBL.addReimbursement(newReimbursement);
			//System.out.println(newReimbursement);
			JOptionPane.showMessageDialog(null, newReimbursement, title, 1);

		} while (keepGoing);
	}

	private void getRefunds() {
		output = "";
		// TODO Auto-generated method stub
		for (Reimbursement refund : refundBL.getRefunds()) {
			if (employee.isManager()) {
				//System.out.println(refund);
				output += refund + "\n";
			} else if (refund.getEmpId() == employee.getEmpId()) {
				//System.out.println(refund);
				output += refund + "\n";
			}
		}
		JOptionPane.showMessageDialog(null, output, title, 1);
	}

	private void getEmployees() {
		// TODO Auto-generated method stub
		for (Employee employee : employeeBL.getEmployees()) {
			System.out.println(employee);
		}
	}

	private void getSpecificRequest() {
		// TODO Auto-generated method stub
		System.out.println("Enter the id of the request you'd like to view: ");
		String stringId = myScanner.nextLine();
		// Integer.parseInt() is a method used to parse strings to integers

		try {
			foundRequest = refundBL.getRefundById(Integer.parseInt(stringId));
			System.out.println(foundRequest);
			/*
			 * for (Solution solution : foundIssue.getSolutions()) {
			 * System.out.println(solution); }
			 */
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			System.out.println("Please only enter numerics");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("No such request found, try another id");
		}

		System.out.println("Would you like to change the status of this request?");
		System.out.println("[y] Yes");
		System.out.println("[n] No");
		String userInput = myScanner.nextLine();

		switch (userInput) {
		case "y":
			changeStatus(Integer.parseInt(stringId));
			break;
		case "n":
			break;
		}

	}

	private void changeStatus(int id) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("Enter the id of the request you'd like to approve/deny: "
		 * ); String stringId = myScanner.nextLine();
		 */
		String status = "";

		try {
			foundRequest = refundBL.getRefundById(id);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			System.out.println("Please only enter numerics");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("No such request found, try another id");
		}

		/*
		 * try { int id = Integer.parseInt(stringId); foundRequest =
		 * refundBL.getRefundById(id); String type = foundRequest.getType(); Double
		 * amount = foundRequest.getAmount(); status = foundRequest.getStatus();
		 * Reimbursement reimbursement = new Reimbursement(id, type, amount, status);
		 * 
		 * } catch (NumberFormatException ex) {
		 * System.out.println("Please only enter numerics"); } catch (Exception e) { //
		 * TODO Auto-generated catch block
		 * System.out.println("No such request found, try another id"); }
		 */
		// String status = myScanner.nextLine();

		System.out.println("Select status: ");
		System.out.println("[1] PENDING");
		System.out.println("[2] APPROVED");
		System.out.println("[3] DENIED");
		System.out.println("[x] Exit");
		userInput = myScanner.nextLine();
		switch (userInput) {
		case "1":
			status = "PENDING";
			break;
		case "2":
			status = "APPROVED";
			break;
		case "3":
			status = "DENIED";
			break;
		case "x":
			System.out.println("Goodbye");
			break;
		default:
			System.out.println("Sorry wrong input, please try again");
			break;
		}

		foundRequest.setStatus(status);

		System.out.println(foundRequest);

	}

}
