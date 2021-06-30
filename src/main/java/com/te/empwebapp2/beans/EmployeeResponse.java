package com.te.empwebapp2.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@XmlRootElement(name = "response")
@JsonRootName("employeeResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse implements Serializable{

	@XmlElement
	@JsonProperty
	private int statusCode;
	

	@XmlElement
	@JsonProperty
	private String msg;
	

	@XmlElement
	@JsonProperty
	private String description;
	

	@XmlElement
	@JsonProperty
	private EmployeeInfoBean employeeInfoBean;
	
	@XmlElement
	@JsonProperty
	private List<EmployeeInfoBean> employeeInfoBeans;
}