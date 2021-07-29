package com.cos.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// 인증이 안 된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp
// static 이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		log.info("joinForm() invoked.");
		return "user/joinForm";
	} // joinForm
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		log.info("loginForm() invoked.");
		return "user/loginForm";
	} // loginForm
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		log.info("updateForm() invoked.");
		return "user/updateForm";
	} // loginForm
} // end class
