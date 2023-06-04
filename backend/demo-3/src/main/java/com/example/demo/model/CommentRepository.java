package com.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CommentRepository extends CrudRepository<Comment, Integer> {	 
	// 根據貼文ID查詢該貼文底下的所有評論
	@Query("SELECT c, u FROM Comment c INNER JOIN User u ON c.UserID = u.UserID WHERE c.PostID = :postID")
	 List<Object[]> findByPostID(Integer postID);
}