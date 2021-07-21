package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

} // end class
