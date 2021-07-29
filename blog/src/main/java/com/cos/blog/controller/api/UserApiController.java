package com.cos.blog.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
// ctrl + shift + R 파일찾기
@RestController
public class UserApiController {
	
	private Logger log = LoggerFactory.getLogger(UserApiController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		
		log.info("\t+ save(user) invoked.");
		// 자바오브젝트를 JSON을 변환해서 리턴
		
		log.info("\t+ user" + user);
		
		userService.signUp(user);
		
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")
	public ResponseDTO<Integer> update(@RequestBody User user) {
		
		log.info("\t+ update(user) invoked.");
		
		userService.userUpdate(user);
		// 여기서는 트랜잭션이 종료되기 때문에 DB의 값은 변경이 됐음
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
		
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	/*
	 * @PostMapping("/api/user/login") public ResponseDTO<Integer> login(
	 * @RequestBody User user, HttpSession session ) {
	 * log.info("\t+ login(user) invoked");
	 * User principal = userService.login(user); // principal(접근주체)
	 * if (principal != null) { session.setAttribute("principal", principal); }
	 * return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1); }
	 */
} // end class
