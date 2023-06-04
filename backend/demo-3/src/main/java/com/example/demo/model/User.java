package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer UserID;
	@Column(name = "user_name")
	private String user_name;
	private String email;
	private String password;
	private String biography;
	private String phone;
	
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer UserID) {
		this.UserID = UserID;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}