package com.revature.expenseReimburesment.ui;

import java.util.Scanner;

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
			System.out.println("Reimbursement System\n");

			while (employee == null) {
				System.out.println("What is your employee id?");
				userInput = myScanner.nextLine();
				try {
					/*int test = Integer.parseInt(userInput);
					System.out.println(test + " " + ((Object) test).getClass().getSimpleName());*/
					
					employee = employeeBL.getEmpById(Integer.parseInt(userInput));
					System.out.println(employee);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					System.out.println("Please only enter numerics");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("No such employee found, try another id");
				}
			}

			System.out.println("What do you wanna do?");
			System.out.println("[0] Submit Reimbursement Request");
			if (employee.isManager()) {
				System.out.println("[1] View Reimbursement Requests");
				System.out.println("[2] Update Reimbursement Request");
			}
			System.out.println("[x] Exit");

			userInput = myScanner.nextLine();
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
			case "x":
				System.out.println("Goodbye");
				keepGoing = false;
				break;
			default:
				System.out.println("Sorry wrong input, please try again");
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
			System.out.println("What type of expense is this for?");
			System.out.println("[1] Lodging");
			System.out.println("[2] Travel");
			System.out.println("[3] Food");
			System.out.println("[4] Other");
			System.out.println("[x] Exit");
			userInput = myScanner.nextLine();
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
				System.out.println("Goodbye");
				keepGoing = false;
				break;
			default:
				System.out.println("Sorry wrong input, please try again");
				break;
			}

			if (keepGoing) {
				System.out.println("How much was the expense?: ");
				String input = myScanner.nextLine();
				try {
					amount = Double.parseDouble(input);
					keepGoing = false;
				} catch (NumberFormatException e1) {
					System.out.println("Please enter numbers only.");
				}
			}

			Reimbursement newReimbursement = new Reimbursement(id, type, amount);
			// saving to storage
			refundBL.addReimbursement(newReimbursement);
			System.out.println(newReimbursement);

		} while (keepGoing);
	}

	private void getRefunds() {
		// TODO Auto-generated method stub
		for (Reimbursement refund : refundBL.getRefunds()) {
			System.out.println(refund);
		}
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

		System.out.println("Would you like to change the statu of this request?");
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
