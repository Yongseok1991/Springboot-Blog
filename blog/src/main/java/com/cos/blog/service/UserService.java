package com.cos.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean을 등록을해줌 IoC를 해준다.
@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public int 회원가입(User user) {
		try {
		 userRepository.save(user);
		 return 1;
		} catch(Exception e) {
			e.printStackTrace();
			log.info("\t+ 회원가입(user): " + e.getMessage());
		}
		return -1;
	} // 회원가입

} // end class
