package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;
// @Reqository 생략 가능
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	
} // end class
