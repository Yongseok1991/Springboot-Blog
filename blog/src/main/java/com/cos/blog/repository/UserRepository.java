package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;
// @Reqository 생략 가능
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
	
	
} // end class


//JPA Naming 쿼리
// SELECT * FROM user WHERE username=? AND password = ?;
// User findByUserNameAndPassword(String username, String password);

//@Query(
//		value="SELECT * FROM user WHERE username= ?1 AND password = ?2",
//		nativeQuery =true
//		)
//User login(String username, String password);
//