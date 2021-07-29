package com.cos.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	
	@Test
	public void hash() {
		String encPassword = new BCryptPasswordEncoder().encode("1234");
		
		System.out.println("password: "+ encPassword);
	}
}
