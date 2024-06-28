package com.app.attendance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.app.attendance.entity.Admin;
import com.app.attendance.repository.AdminRepository;
import com.app.attendance.security.JwtService;
import com.app.attendance.util.PasswordUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final Logger logger= LoggerFactory.getLogger(getClass());

    private JwtService jwtService;
	
	private AdminRepository adminRepository;
	
	public Admin createAdmin(Admin admin) {
		var adminobj = admin.builder().email(admin.getEmail())..build();
		return adminRepository.saveAndFlush(adminobj);
	}
	
	
	

}
