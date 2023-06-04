package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {	 
	// 根據手機號碼取出使用者相關資訊
	 @Query("SELECT u FROM User u WHERE u.phone = :phone")
	 List<User> findByPhone(String phone);
}