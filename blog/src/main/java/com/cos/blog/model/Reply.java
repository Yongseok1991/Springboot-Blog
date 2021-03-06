package com.cos.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="boardId")
	
	private Board board;
	
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@UpdateTimestamp
	private LocalDateTime updateDate;
	
	
	public void update(User user, Board board, String content) {
		setUser(user);
		setBoard(board);
		setContent(content);
	}
} // end class
