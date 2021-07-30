package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;
// @Reqository 생략 가능
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	@Modifying
	@Query(" UPDATE Board b SET   b.count = IFNULL(b.count, 0) +1 WHERE id =?1")
	void increaseViewCount(int id);
} // end class
