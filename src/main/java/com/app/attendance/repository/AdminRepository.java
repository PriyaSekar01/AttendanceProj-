package com.app.attendance.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.attendance.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID>{
	
	

}
