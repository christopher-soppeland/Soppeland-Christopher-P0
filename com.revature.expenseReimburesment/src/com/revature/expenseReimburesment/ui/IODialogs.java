package com.revature.expenseReimburesment.ui;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class IODialogs implements ActionListener {
	private String title = "Reimburesment System";
	private String output;
	private int msgType;
	String[] types = { "LODGING", "FOOD", "TRAVEL", "OTHER" };
	String[] options;
	private String dialog;

	public String Input(String output, int msgType) {
		

		dialog = (String)JOptionPane.showInputDialog(null, output, title, msgType);

		return dialog;
	}

	public void Output() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(JOptionPane.OK_OPTION)) {
			// statusLabel.setText("Ok Button clicked.");
		} else if (command.equals(JOptionPane.CANCEL_OPTION)) {
			// statusLabel.setText("Cancel Button clicked.");
			System.exit(0);
		}
	}

}
