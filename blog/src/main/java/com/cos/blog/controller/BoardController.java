package com.cos.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	private Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;

	// 컨트롤러에서 세션을 어떻게 찾는지?
	@GetMapping({ "/", "" })
	public String index(
//			@AuthenticationPrincipal PrincipalDetail principal
			Model model, 
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) 
			Pageable pageable
			) {
		
		log.info("index(model, pageable) invoked.");
		
		Page<Board> pagingBoard = boardService.boardList(pageable);

		int pageNumber = pagingBoard.getPageable().getPageNumber();
		int totalPages = pagingBoard.getTotalPages();
		int pageBlock = 5;
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

//		log.info("로그인 사용자 아이디:{} ", principal.getUsername());

		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("boards", pagingBoard);

		return "index";
	} // index

	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		
		log.info("saveForm() invoked.");
		
		return "board/saveForm";
	}

	@GetMapping("board/{id}")
	public String findById(
			@PathVariable int id,
			Model model
			) {
		
		log.info("findById(id, model) invoked.");
	
		Board detail = boardService.boardDetail(id);
		
		model.addAttribute("board", detail);
		return "board/detail";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		
		log.info("updateForm(id, model) invoked.");
		
		model.addAttribute("board", boardService.boardDetail(id));
		return "board/updateForm";
	}

} // end class
