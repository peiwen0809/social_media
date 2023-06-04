package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer PostID;
	private Integer UserID;
	private String content;
	private Date created_at;

	public Integer getPostID() {
		return PostID;
	}
	public void setPostID(Integer PostID) {
		this.PostID = PostID;
	}

	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer UserID) {
		this.UserID = UserID;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}