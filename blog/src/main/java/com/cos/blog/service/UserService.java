package com.cos.blog.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean을 등록을해줌 IoC를 해준다.
@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void signUp(User user) {
		
		log.info("\t+ signUp(user) invoked.");
		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
	} // 회원가입
	
	/*
	 * @Transactional(readOnly=true) // Select할 때 트렌젝션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	 * public User login(User user) {
	 * 
	 * log.info("\t+ login(user) invoked");
	 * 
	 * log.info("\t+ user: " + user); // return
	 * userRepository.login(user.getUsername(), user.getPassword()); return
	 * userRepository.findByUserNameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */

} // end class
