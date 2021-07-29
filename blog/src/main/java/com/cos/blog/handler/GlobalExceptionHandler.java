package com.cos.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.controller.api.UserApiController;
import com.cos.blog.dto.ResponseDTO;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public ResponseDTO<String> handleArgumentException(IllegalArgumentException e) {
		
		log.info("handleArgumentException(e) invoked.");
		
		return new ResponseDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

	} 
	
} // end class
