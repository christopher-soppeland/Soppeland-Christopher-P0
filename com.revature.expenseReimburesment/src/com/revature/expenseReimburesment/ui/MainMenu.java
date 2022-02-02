package com.revature.expenseReimburesment.ui;

//import java.util.Scanner;

import javax.swing.JOptionPane;

import com.revature.expenseReimburesment.bl.EmployeeBL;
import com.revature.expenseReimburesment.bl.IRefundBL;
import com.revature.expenseReimburesment.models.Employee;
import com.revature.expenseReimburesment.models.Reimbursement;

public class MainMenu {

	// private Scanner myScanner;
	private String userInput;
	private IRefundBL refundBL;
	private EmployeeBL employeeBL;
	private Employee employee;
	private Reimbursement foundRequest;
	private String output;
	// private IODialogs dialog = new IODialogs();
	private String title = "Reimbursement System";
	private String[] options = new String[7];

	public MainMenu(/* Scanner myScanner, */IRefundBL refundBL, EmployeeBL employeeBL) {
		// this.myScanner = myScanner;
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
				// userInput = JOptionPane.showInputDialog(null, output, title, 3);
				userInput = JOptionPane.showInputDialog(null, output, title, 3);
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
					System.exit(0);
				}
			}

			if (employee != null) {
				// System.out.println("What do you want do?");
				output = "What do you want do?\n\n";
				// System.out.println("[0] Submit Reimbursement Request");
				options[1] = "Submit Reimbursement Request";

				if (employee.isManager()) {
					// System.out.println("[1] View Reimbursement Requests");
					options[2] = "View Reimbursement Requests";
					// System.out.println("[2] Update Reimbursement Request");
					options[3] = "Update Reimbursement Request";
					// System.out.println("[3] View All Employees");
					options[4] = "View All Employees";
					options[5] = "Add Employee";
					options[6] = "Exit";
				} else {
					// System.out.println("[1] View Your Reimbursement Requests");
					options[2] = "View Your Reimbursement Requests";
					options[3] = "Exit";
				}
				// System.out.println("[x] Exit");
				

				// userInput = myScanner.nextLine();
				userInput = (String) JOptionPane.showInputDialog(null, output, title, 1, null, options, options[0]);

				if (userInput != null) {
					switch (userInput) {
					case "Submit Reimbursement Request":
						createRequest();
						break;
					case "View Reimbursement Requests":
						getRefunds();
						break;
					case "View Your Reimbursement Requests":
						getRefunds();
						break;
					case "Update Reimbursement Request":
						getSpecificRequest();
						break;
					case "View All Employees":
						getEmployees();
						break;
					case "Add Employee":
						addEmployee();
						break;
					case "Exit":
						// System.out.println("Goodbye");
						JOptionPane.showMessageDialog(null, "Goodbye", title, 1);
						System.exit(0);
					default:
						// System.out.println("Sorry wrong input, please try again");
						JOptionPane.showMessageDialog(null, "Sorry wrong input, please try again", title, 0);
						break;
					}
				} else {
					System.exit(0);
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
		String desc = "";
		String status = "PENDING";
		int empId = employee.getEmpId();

		boolean keepGoing = true;
		do {
			options = new String[6];
			// System.out.println("What type of expense is this for?");
			output = "What type of expense is this for?\n";
			// System.out.println("[1] Lodging");
			options[1] = "Lodging";
			// System.out.println("[2] Travel");
			options[2] = "Travel";
			// System.out.println("[3] Food");
			options[3] = "Food";
			// System.out.println("[4] Other");
			options[4] = "Other";
			// System.out.println("[x] Exit");
			options[5] = "Exit";
			// userInput = myScanner.nextLine();
			userInput = (String) JOptionPane.showInputDialog(null, output, title, 1, null, options, options[0]);

			if (userInput != null) {
				switch (userInput) {
				case "Lodging":
					type = "Lodging";
					break;
				case "Travel":
					type = "Travel";
					break;
				case "Food":
					type = "Food";
					break;
				case "Other":
					type = "Other";
					break;
				case "x":
					// System.out.println("Goodbye");
					JOptionPane.showMessageDialog(null, "Goodbye", title, 1);
					keepGoing = false;
					break;
				default:
					// System.out.println("Sorry wrong input, please try again");
					JOptionPane.showMessageDialog(null, "Sorry wrong input, please try again", title, 0);
					break;
				}
			} else {
				System.exit(0);
			}

			if (keepGoing) {
				// System.out.println("How much was the expense?: ");
				// userInput = myScanner.nextLine();
				output = "How much was the expense?:\n";
				userInput = JOptionPane.showInputDialog(null, output, title, 1);
				try {
					amount = Double.parseDouble(userInput);
					keepGoing = false;
				} catch (NumberFormatException e1) {
					// System.out.println("Please enter numbers only.");
					JOptionPane.showMessageDialog(null, "Please enter numbers only.", title, 0);
				}

				output = "Optional description\n" + "Leave blank if none";
				desc = JOptionPane.showInputDialog(null, output, title, 1);
			}

			Reimbursement newReimbursement = new Reimbursement(id, type, amount, status, desc, empId);
			// saving to storage
			refundBL.addReimbursement(newReimbursement);
			// System.out.println(newReimbursement);
			JOptionPane.showMessageDialog(null, "Reimbursement Request Submitted Successfully\n" + newReimbursement,
					title, 1);

		} while (keepGoing);
	}

	private void getRefunds() {
		output = "";
		// TODO Auto-generated method stub
		for (Reimbursement refund : refundBL.getRefunds()) {
			if (employee.isManager()) {
				// System.out.println(refund);
				output += refund + "\n";
			} else if (refund.getEmpId() == employee.getEmpId()) {
				// System.out.println(refund);
				output += refund + "\n";
			}
		}
		JOptionPane.showMessageDialog(null, output, title, 1);
	}

	private void getEmployees() {
		// TODO Auto-generated method stub
		output = "";

		for (Employee employee : employeeBL.getEmployees()) {
			// System.out.println(employee);
			output += employee + "\n";
		}
		JOptionPane.showMessageDialog(null, output, title, 1);
	}

	private void addEmployee() {
		boolean is_manager = false;
		int id = 0;
		String name;
		int man_id = 0;
		boolean keepGoing = true;
		String[] options = { "Yes", "No" };
		
		userInput = (String) JOptionPane.showInputDialog(null, "Is this employee a manager?\n", title, 1, null, options,
				options[1]);
		if (userInput == "Yes") {
			is_manager = true;
		} else {
			is_manager = false;
			man_id = Integer.parseInt(
					JOptionPane.showInputDialog(null, "What is the ID of this employee's manager?\n", title, 1));
		}
		name = JOptionPane.showInputDialog(null, "Employee Name:\n", title, 1);
		
		Employee newEmployee = new Employee(id, is_manager, name, man_id);
		
		employeeBL.addEmployee(newEmployee);
		
		JOptionPane.showMessageDialog(null, "Employee Successfully\n" + newEmployee,
				title, 1);
	}

	private void getSpecificRequest() {
		// TODO Auto-generated method stub
		output = "Enter the id of the request you'd like to view: ";
		// System.out.println("Enter the id of the request you'd like to view: ");
		// String stringId = myScanner.nextLine();
		String stringId = JOptionPane.showInputDialog(null, output, title, 1);
		// Integer.parseInt() is a method used to parse strings to integers

		try {
			foundRequest = refundBL.getRefundById(Integer.parseInt(stringId));
			// System.out.println(foundRequest);
			JOptionPane.showMessageDialog(null, foundRequest, title, 1);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			// System.out.println("Please only enter numerics");
			JOptionPane.showMessageDialog(null, "Please only enter numerics", title, 0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// System.out.println("No such request found, try another id");
			JOptionPane.showMessageDialog(null, "No such request found, try another id", title, 0);
		}

		/*
		 * System.out.println("Would you like to change the status of this request?");
		 * System.out.println("[y] Yes"); System.out.println("[n] No"); userInput =
		 * myScanner.nextLine();
		 */
		options = new String[2];
		options[0] = "Yes";
		options[1] = "No";
		output = "Would you like to change the status of this request?\n";

		userInput = (String) JOptionPane.showInputDialog(null, output, title, 1, null, options, options[0]);

		switch (userInput) {
		case "Yes":
			changeStatus(Integer.parseInt(stringId));
			break;
		case "No":
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
			// System.out.println("Please only enter numerics");
			JOptionPane.showMessageDialog(null, "Please only enter numerics", title, 0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// System.out.println("No such request found, try another id");
			JOptionPane.showMessageDialog(null, "No such request found, try another id", title, 0);
		}
		// String status = myScanner.nextLine();

		/*
		 * System.out.println("Select status: "); System.out.println("[1] PENDING");
		 * System.out.println("[2] APPROVED"); System.out.println("[3] DENIED");
		 * System.out.println("[x] Exit"); userInput = myScanner.nextLine();
		 */

		options = new String[4];
		options[0] = "PENDING";
		options[1] = "APPROVED";
		options[3] = "DENIED";
		options[4] = "Exit";
		output = "Select status: \n" + "[1] PENDING\n" + "[2] APPROVED\n" + "[3] DENIED\n" + "[x] Exit";
		userInput = (String) JOptionPane.showInputDialog(null, output, title, 1, null, options, options[0]);
		switch (userInput) {
		case "PENDING":
			status = "PENDING";
			break;
		case "APPROVED":
			status = "APPROVED";
			break;
		case "DENIED":
			status = "DENIED";
			break;
		case "Exit":
			System.out.println("Goodbye");
			break;
		default:
			System.out.println("Sorry wrong input, please try again");
			break;
		}

		foundRequest.setStatus(status);
		try {
			refundBL.changeStatus(foundRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(foundRequest);
		JOptionPane.showMessageDialog(null, foundRequest, title, 1);

	}

}
