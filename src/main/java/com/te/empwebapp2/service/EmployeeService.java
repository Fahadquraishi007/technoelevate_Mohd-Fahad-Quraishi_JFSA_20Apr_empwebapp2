package com.te.empwebapp2.service;

import java.util.List;

import com.te.empwebapp2.beans.EmployeeInfoBean;

public interface EmployeeService {

	public EmployeeInfoBean authenticate(int id, String pwd);

	public EmployeeInfoBean getEmployeeData(int id);

	public boolean deleteEmpData(int id);
	
	public boolean addEmployee(EmployeeInfoBean employeeInfoBean);
	
	public boolean updateRecord(EmployeeInfoBean employeeInfoBean);
	
	public List<EmployeeInfoBean> getAllEmployees();
}