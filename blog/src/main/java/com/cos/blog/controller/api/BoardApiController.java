package com.cos.blog.controller.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDTO;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;

@RestController
public class BoardApiController {

	private Logger log = LoggerFactory.getLogger(BoardApiController.class);

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {

		log.info("\t+ save(board, principal) invoked.");

		boardService.boardWrite(board, principal.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDTO<Integer> deleteById(@PathVariable int id) {

		log.info("\t+ deleteById(id) invoked.");

		boardService.boardDelete(id);

		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDTO<Integer> updateById(@PathVariable int id, @RequestBody Board board) {

		log.info("\t+ deleteById(id, board) invoked.");

		boardService.boardUpdate(id, board);

		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

	@PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json")
	public Map<String, Object> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

		log.info("\t+ uploadSummernoteImageFile(multipartFile) invoked.");

		Map<String, Object> params = new HashMap<>();

		String fileRoot = "C:\\summernote_image\\"; // 저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자

		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명

		File targetFile = new File(fileRoot + savedFileName);

		try {

			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			params.put("url", "/summernoteImage/" + savedFileName);
			params.put("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			params.put("responseCode", "error");
			e.printStackTrace();
		}
		log.info("params:  " + params);
		return params;
	}
	// 데이터 받을때 컨틀롤러에서 dto를 만들어서 받는게 좋다.
	// dto를 사용하지 않는 이유는!! 
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDTO<Integer> Replysave( 
			@RequestBody ReplySaveRequestDTO reply
			) {

		log.info("\t+ save(board, principal) invoked.");
		
		boardService.replyWrite(reply);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDTO<Integer> Replysave(@PathVariable int replyId) {
		
		log.info("\t+ save(board, principal) invoked.");
		
		boardService.replyDelete(replyId);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDTO<Integer> ReplyUpdate(
			@PathVariable String boardId,
			@PathVariable int replyId,
			@RequestBody Reply reply
			) {
		
		log.info("replyId:" + replyId);
		log.info("\t+ content:" + reply.getContent());
		boardService.replyUpdate(reply.getContent(), replyId);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
} // end class
