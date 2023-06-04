package com.example.demo.controller;

import com.example.demo.model.*;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:8080")
public class PostController {	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	// 建立貼文
	@PostMapping(path="/create")
	public @ResponseBody HashMap<String, Object> addPost (@RequestBody Post post) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			// 建立時間
			Date curr = new Date();
			post.setCreated_at(curr);
			System.out.println(post.getUserID());
			postRepository.save(post);
			result.put("Success", true);
		}catch (Exception e) {
			result.put("Success", false);
			System.out.println(e);
			result.put("Description", e.getMessage());
		}
		return result;
	}
	
	// 修改貼文
	@Transactional
	@PostMapping(path="/modify")
	public @ResponseBody HashMap<String, Object> modifyPost (@RequestBody Post post) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			Post p = entityManager.find(Post.class, post.getPostID());
	        
	        if (p != null) {
	            // 更新屬性
	            p.setContent(post.getContent());
	            // 儲存更新後的實體
	            entityManager.persist(p);
	            result.put("Success", true);
	        }
		}catch (Exception e) {
			result.put("Success", false);
			System.out.println(e);
			result.put("Description", e.getMessage());
		}
		return result;
	}
	
	// 刪除貼文
	@DeleteMapping(path="/delete/{postID}")
	public @ResponseBody HashMap<String, Object> delPost(@PathVariable Integer postID) {
	    HashMap<String, Object> result = new HashMap<>();

	    try {
	        postRepository.deleteById(postID);
	        result.put("Success", true);
	    } catch (Exception e) {
	        result.put("Success", false);
	        result.put("Description", e.getMessage());
	    }
	    return result;
	}

	// 列出所有貼文
	@GetMapping(path="/listall")
	public @ResponseBody HashMap<String, Object> listall () {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HashMap<String, Object>> posts = new ArrayList<>();
		try {
			result.put("Success", true);	
//			result.put("Content", postRepository.findAll());
			List<Object[]> post_results = postRepository.findAllJoin();
			for (Object[] post_result : post_results) {
			    Post single_post = (Post) post_result[0];
			    User user = (User) post_result[1];
			    HashMap<String, Object> postContent = new HashMap<String, Object>();
			    postContent.put("postID", single_post.getPostID());
			    postContent.put("content", single_post.getContent());
			    postContent.put("user_name", user.getUser_name());
			    postContent.put("created_at", single_post.getCreated_at());
			    posts.add(postContent);
			}
			result.put("Content", posts);

		}catch (Exception e) {
			result.put("Success", false);
			result.put("Description", e.getMessage());
		}
		return result;
	}
	
	// 列出自己的貼文
	@PostMapping(path="/listself")
	public @ResponseBody HashMap<String, Object> listself (@RequestBody Post post) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HashMap<String, Object>> posts = new ArrayList<>();
		try {
			result.put("Success", true);	
			List<Object[]> post_results = postRepository.findByUserID(post.getUserID());
			for (Object[] post_result : post_results) {
			    Post single_post = (Post) post_result[0];
			    User user = (User) post_result[1];
			    HashMap<String, Object> postContent = new HashMap<String, Object>();
			    postContent.put("postID", single_post.getPostID());
			    postContent.put("content", single_post.getContent());
			    postContent.put("user_name", user.getUser_name());
			    postContent.put("created_at", single_post.getCreated_at());
			    posts.add(postContent);
			}
			result.put("Content", posts);
//			result.put("Content", postRepository.findByUserID(post.getUserID()));
			
		}catch (Exception e) {
			result.put("Success", false);
			result.put("Description", e.getMessage());
		}
		return result;
	}
}