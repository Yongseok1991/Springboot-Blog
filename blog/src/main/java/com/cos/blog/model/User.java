package com.cos.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert 시에 null인 컬럼을 제외시켜준다
@Entity // User 클래스가 MySQL의 테이블이 생성이 된다.
public class User {
	
	// primary key
	@Id 
	// 프로젝트에서 연결된 db의 넘버링 전략을 따라감
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password; // 비밀번호
	
	@Column(nullable = false, length = 50)
	private String email; // 이메일
	
	//Enum을 쓰는게 좋음 // ADMIN, USER
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	//시간이 자동 입력
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp 
	private LocalDateTime createDate; //생성시간
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@UpdateTimestamp
	private LocalDateTime updateDate;
	
	
} // end class
