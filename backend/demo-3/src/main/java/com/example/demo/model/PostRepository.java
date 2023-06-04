package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Transactional
public interface PostRepository extends CrudRepository<Post, Integer> {
	// 根據userID查詢該使用者的所有貼文
	 @Query("SELECT p, u FROM Post p INNER JOIN User u ON p.UserID = u.UserID WHERE p.UserID = :userID")
	 List<Object[]> findByUserID(Integer userID);
	 
	 // 列出所有貼文，因為需要使用者名稱在前端顯示，所以用join
	 @Query("SELECT p, u FROM Post p INNER JOIN User u ON p.UserID = u.UserID")
	 List<Object[]> findAllJoin();

}