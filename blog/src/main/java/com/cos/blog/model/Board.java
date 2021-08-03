package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	@Column(nullable = false, length = 100)
	private String title;
	
	//섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨
	@Lob
	private String content;
	
	private int count; // 조회수
	
	// DB는 오브젝트를 저장할 수 없다. FK // 자바는 오브젝트를 저장할 수 있다.
	// Many  = Board , one = User
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="userId")
	private User user; 
	
	// mappedBy 연관관계의 주인이 아니다(난 FK가 아니다)
	// db에 컬럼을 만들지 마세요
	@OneToMany(mappedBy = "board" , fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@UpdateTimestamp
	private Timestamp updateDate;
} // end class
