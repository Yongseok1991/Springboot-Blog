package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private  int id;
	private  String username;
	private  String password;
	private  String email;

} // end class