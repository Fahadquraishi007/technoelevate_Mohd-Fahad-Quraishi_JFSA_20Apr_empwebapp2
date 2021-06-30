package com.te.empwebapp2.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.empwebapp2.beans.EmployeeResponse;
import com.te.empwebapp2.customexp.EmployeeException;

@RestControllerAdvice
public class EmployeeControllerAdvice {

	@ExceptionHandler(EmployeeException.class)
	public EmployeeResponse handleEmployeeException(EmployeeException exception) {
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(500);
		response.setMsg("Failure");
		response.setDescription(exception.getMessage());
		return response;
	}
}