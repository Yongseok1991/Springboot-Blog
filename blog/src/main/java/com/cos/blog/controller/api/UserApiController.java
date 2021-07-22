package com.cos.blog.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.User;

@RestController
public class UserApiController {
	
	private Logger log = LoggerFactory.getLogger(UserApiController.class);
	
	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		log.info("\t+ save(user) invoked.");
		// 자바오브젝트를 JSON을 변환해서 리턴
		return new ResponseDTO<Integer>(HttpStatus.OK, 1);
	}
} // end class
