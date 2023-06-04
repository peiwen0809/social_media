package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer CommentID;
	private Integer UserID;
	private Integer PostID;
	private String content;
	private Date created_at;

	public Integer getCommentID() {
		return CommentID;
	}
	public void setCommentID(Integer CommentID) {
		this.CommentID = CommentID;
	}
	
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
