package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.Member;
// 사용자가 요청 -> 응답(HTML 파일)
// @Controller
// 사용자가 요청 -> 응답(Data)
@RestController
@RequestMapping("/http")
public class HttpController {

	// http://localhost:8080/http/get(select)
	@GetMapping("/get")
	public String getTest(Member mem) {
		System.out.println("getTest() invoked.");
		return "get 요청" + mem.getId() + mem.getUserName() + mem.getPassword() + mem.getEmail();
	} // getTest
	
	@PostMapping("/post")
	public String postTest(@RequestBody Member mem) {
		System.out.println("postTest() invoked.");
		return "post 요청 " + mem.getId() + mem.getUserName() + mem.getPassword() + mem.getEmail();
	} // getTest
	
	@PutMapping("/put")
	public String putTest() {
		System.out.println("putTest() invoked.");
		return "put 요청";
	} // getTest
	
	@DeleteMapping("/delete")
	public String deleteTest() {
		System.out.println("deleteTest() invoked.");
		return "delete 요청";
	} // getTest
	
} // end class
