package com.entity;

import java.util.Random;

public class User {

	private int id;
	private String fullname;
	private String email;
	private String password;
	private int experience;
	private boolean notificationStatus;
	private int activationStatus;
	private int code;

	public User() {
		super();
	}

	public User(String fullname, String email, String password, int experience, boolean notificationStatus) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.experience = experience;
		this.notificationStatus = notificationStatus;
		this.code = new Random().nextInt();
	}

	public User(String fullname, String email, int experience) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.experience = experience;
	}

	public int getId() {
		return id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public boolean getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(boolean notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public int getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(int activationStatus) {
		this.activationStatus = activationStatus;
	}

	public int getCode() {
		return this.code;
	}

}
