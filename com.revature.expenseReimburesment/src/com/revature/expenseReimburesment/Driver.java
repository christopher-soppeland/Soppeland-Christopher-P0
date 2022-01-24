package com.revature.expenseReimburesment;

import java.util.Scanner;

import com.revature.expenseReimburesment.bl.EmployeeBL;
import com.revature.expenseReimburesment.bl.RefundBL;
import com.revature.expenseReimburesment.dl.Data;
import com.revature.expenseReimburesment.ui.MainMenu;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenu menu = new MainMenu(new Scanner(System.in), new RefundBL(new Data()), new EmployeeBL(new Data()));
		menu.start();

	}

}
