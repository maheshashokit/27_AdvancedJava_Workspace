package com.ashokit.dto;

public class SignUp {
	
	private String name;
	
	private String emailId;
	
	private String contactNo;
	
	private String courseName;

	public SignUp() {
		// TODO Auto-generated constructor stub
	}
	
	public SignUp(String name, String emailId, String contactNo, String courseName) {
		this.name = name;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.courseName = courseName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "SignUp [name=" + name + ", emailId=" + emailId + ", contactNo=" + contactNo + ", courseName="
				+ courseName + "]";
	}
}
