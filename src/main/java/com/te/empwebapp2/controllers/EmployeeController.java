package com.te.empwebapp2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.empwebapp2.beans.EmployeeInfoBean;
import com.te.empwebapp2.beans.EmployeeResponse;
import com.te.empwebapp2.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping(path = "/getEmployee", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse getEmployeeData(int id) {
		EmployeeInfoBean infoBean = service.getEmployeeData(id);
		EmployeeResponse response = new EmployeeResponse();
		if (infoBean != null) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data found for id : " + id);
			response.setEmployeeInfoBean(infoBean);
		} else {
			response.setStatusCode(404);
			response.setMsg("Failure");
			response.setDescription("Data not found for id : " + id);
		}
		return response;
	}

	@PostMapping(path = "/addEmployee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse addEmployeeData(@RequestBody EmployeeInfoBean infoBean) {
		EmployeeResponse response = new EmployeeResponse();

		if (service.addEmployee(infoBean)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data inserted Sucessfully");
		} else {
			response.setStatusCode(500);
			response.setMsg("failure");
			response.setDescription("Data Could not be inserted");
		}
		return response;
	}

	@PutMapping(path = "/updateEmployee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse updateEmpData(@RequestBody EmployeeInfoBean infoBean) {
		EmployeeResponse response = new EmployeeResponse();

		if (service.updateRecord(infoBean)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data Updated Sucessfully");
		} else {
			response.setStatusCode(500);
			response.setMsg("failure");
			response.setDescription("Data Could not be updated");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteRecord/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse deleteRecord(@PathVariable int id) {
		EmployeeResponse response = new EmployeeResponse();

		if (service.deleteEmpData(id)) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data Delete Sucessfully");
		} else {
			response.setStatusCode(500);
			response.setMsg("failure");
			response.setDescription("Data Not Exist");
		}
		return response;

	}//

	@GetMapping(path = "/getAll", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse getAllRecord() {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeInfoBean> employeeInfoBeans = service.getAllEmployees();
		if (employeeInfoBeans != null) {
			response.setStatusCode(200);
			response.setMsg("Success");
			response.setDescription("Data Found Sucessfully");
			response.setEmployeeInfoBeans(employeeInfoBeans);
		} else {
			response.setStatusCode(500);
			response.setMsg("failure");
			response.setDescription("Data Not Exist");
		}
		return response;
	}

}