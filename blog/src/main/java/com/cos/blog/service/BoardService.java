package com.cos.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;



//스프링이 컴포넌트 스캔을 통해서 Bean을 등록을해줌 IoC를 해준다.
@Service
public class BoardService {

	private Logger log = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void boardWrite(Board board, User user) {

		log.info("\t+ boardWrite(board, user) invoked.");

		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
		
	} // boardWrite

	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {

		log.info("\t+ boardList(pageable) invoked.");

		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = false)
	public Board boardDetail(int id) {

		log.info("\t+ boardDetail(id) invoked."); 
		boardRepository.increaseViewCount(id);

		return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패"));
	}

	@Transactional
	public void boardDelete(int id) {

		log.info("\t+ boardDelete(id) invoked.");

		boardRepository.deleteById(id);

	}

	@Transactional
	public void boardUpdate(int id, Board requestBoard) {

		log.info("\t+ boardUpdate(id, requestBoard) invoked.");

		Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("글 찾기 실패"));
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(service가 종료될때) 트랜잭션이 종료됩니다. 이때 더티체킹
		// 자동 업데이트가 됨 db flush
	}

	public void increaseViewCount(int id, Board board) {

		log.info("\t+ increaseViewCount(id) invoked.");

	}

} // end class