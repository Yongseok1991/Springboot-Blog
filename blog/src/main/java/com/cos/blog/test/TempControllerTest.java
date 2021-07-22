package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("TempController::tempHome() invoked.");
		return "/home.html";
	} // tempHome
	
	@GetMapping("/temp/img")
	public String tempImg() {
		System.out.println("TempController::tempImg() invoked.");
		return "/IU2.jpg";
	} // tempHome
	
	@GetMapping("/temp/test")
	public String tempTest() {
		System.out.println("TempController::tempTest() invoked.");
		return "test";
	}
	
} // end class
