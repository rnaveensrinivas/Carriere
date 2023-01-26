package com.entity;

import java.util.Random;

public class Job {

	private int id;
	private String role;
	private String location;
	private String pay;
	private String dateOfInterview;
	private String description;
	private int jobStatus;
	private int code = new Random().nextInt(1, 999_999_999);

	public Job() {
		super();
	}

	public Job(String role, String location, String pay, String dateOfInterview, String description, int jobStatus) {
		super();
		this.role = role;
		this.location = location;
		this.pay = pay;
		this.dateOfInterview = dateOfInterview;
		this.description = description;
		this.jobStatus = jobStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getDateOfInterview() {
		return dateOfInterview;
	}

	public void setDateOfInterview(String dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
