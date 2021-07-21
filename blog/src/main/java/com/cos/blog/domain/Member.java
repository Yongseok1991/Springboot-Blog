package com.cos.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private  int id;
	private  String userName;
	private  String password;
	private  String email;

} // end class