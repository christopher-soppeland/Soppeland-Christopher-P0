package com.revature.expenseReimburesment.controllers;

import io.javalin.http.Handler;

public interface IController {

	Handler getAll();
	Handler getById();
	Handler add();
	Handler update();
	Handler getByStatus();
	Handler getByEmpId();

}
