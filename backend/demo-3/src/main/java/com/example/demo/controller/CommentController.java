package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Comment;
import com.example.demo.model.CommentRepository;
import com.example.demo.model.Post;
import com.example.demo.model.User;

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:8080")
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;
	
	// 建立評論
	@PostMapping(path="/create")
	public @ResponseBody HashMap<String, Object> addComment (@RequestBody Comment comment) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			// 建立時間
			Date curr = new Date();
			comment.setCreated_at(curr);
			commentRepository.save(comment);
			result.put("Success", true);
		}catch (Exception e) {
			result.put("Success", false);
			System.out.println(e);
			result.put("Description", e.getMessage());
		}
		return result;
	}
	
	// 列出該貼文的所有評論
	@PostMapping(path="/list")
	public @ResponseBody HashMap<String, Object> listself (@RequestBody Comment comment) {
		HashMap<String, Object> result = new HashMap<String, Object>();  // 回傳的外層
		List<HashMap<String, Object>> comments = new ArrayList<>();  // 最後要回傳的所有評論
		try {
			result.put("Success", true);	
			List<Object[]> comment_results = commentRepository.findByPostID(comment.getPostID());
			for (Object[] comment_result : comment_results) {
			    Comment single_comment = (Comment) comment_result[0];
			    User user = (User) comment_result[1];
			    HashMap<String, Object> commentContent = new HashMap<String, Object>();  // 把回傳的content加上user_name
			    commentContent.put("commentID", single_comment.getCommentID());
			    commentContent.put("content", single_comment.getContent());
			    commentContent.put("user_name", user.getUser_name());
			    commentContent.put("created_at", single_comment.getCreated_at());
			    comments.add(commentContent);
			}
			result.put("Content", comments);
//			result.put("Content", commentRepository.findByPostID(comment.getPostID()));
			
		}catch (Exception e) {
			result.put("Success", false);
			result.put("Description", e.getMessage());
		}
		return result;
	}
}
