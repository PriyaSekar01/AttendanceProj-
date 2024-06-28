package com.app.attendance.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.attendance.dto.AdminRegisterDto;
import com.app.attendance.response.ResponseGenerator;
import com.app.attendance.response.TransactionContext;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ResponseGenerator responseGenerator;
	
	 @PostMapping
	    public ResponseEntity<?> adminRegister(@RequestBody AdminRegisterDto request, @RequestHeader HttpHeaders httpHeader) {
	        logger.info("New admin create {}", LocalDateTime.now());
	        TransactionContext context = responseGenerator.generateTransationContext(httpHeader);
	        try {
	            ResponseEntity<?> registrationResponse = userRegisterService.adminRegister(request);
	            return responseGenerator.successResponse(context, registrationResponse.getBody(), HttpStatus.OK);
	        } catch (Exception e) {
	            logger.error("Error occurred while registering admin details ", e);
	            return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }

}
