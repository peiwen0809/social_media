package com.example.demo.hash;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	// 加密
	public String encode(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
	}
	
	// 比對
	public boolean match(String password, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, encodedPassword);
	}

}

