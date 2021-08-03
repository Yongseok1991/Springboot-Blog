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
		
	} // signUp
	
	@Transactional
	public void userUpdate(User user) {
		
		log.info("\t+ userUpdate(user) invoked.");
		// 수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고,
		// 영속화된 User 오브젝트를 수정
		// select를 해서 User오브젝트를 DB로 부터 가져오는 이유는
		// 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
		User persistance = userRepository.findById(user.getId()).orElseThrow(()-> new IllegalArgumentException("회원찾기 실패"));
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		// 회원 수정 함수 종료시 => 서비스 종료 => 트렌잭션종료 => commit이 자동으로 됨
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
		
	} // userUpdate
	
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
