package com.example.demo.controller;

import com.example.demo.hash.PasswordEncoder;
import com.example.demo.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	private PasswordEncoder pe = new PasswordEncoder();
	
	// 註冊
	@PostMapping(path="/register")
	public @ResponseBody HashMap<String, Object> addNewUser (@RequestBody User user) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			String hashPw = pe.encode(user.getPassword());  // 密碼雜湊
			user.setPassword(hashPw);
			userRepository.save(user);
			result.put("Success", true);
		}catch (Exception e) {
			result.put("Success", false);
			result.put("Description", e.getMessage());
		}
		return result;
	}
	
	// 登入
	@PostMapping(path="/login")
	public @ResponseBody HashMap<String, Object> login (@RequestBody User user) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> userContent = new HashMap<String, Object>();
		try {
			List<User> u = userRepository.findByPhone(user.getPhone());
			boolean correct = pe.match(user.getPassword(), u.get(0).getPassword());
			
			if (correct) {
				result.put("Success", true);
				userContent.put("user_name", u.get(0).getUser_name());
				userContent.put("phone", u.get(0).getPhone());
				userContent.put("userID", u.get(0).getUserID());
				userContent.put("email", u.get(0).getEmail());
				userContent.put("biography", u.get(0).getBiography());
				result.put("Content", userContent);
			}else {
				result.put("Success", false);
			}
			
		}catch (Exception e) {
			result.put("Success", false);
			result.put("Description", e.getMessage());
		}
		return result;
	}
}