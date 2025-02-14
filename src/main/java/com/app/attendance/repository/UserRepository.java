package com.app.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.attendance.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT * FROM user_details where email =:email ", nativeQuery = true)
	Optional<User> findByDuplicateEamil(String email);
	
	@Query(value = "SELECT * FROM user_details where user_name =:userName ", nativeQuery = true)
	Optional<User> findByDuplicateUserName(String userName);

	@Query(value = "SELECT * FROM user_details where mobile_no =:phoneNumber ", nativeQuery = true)
	Optional<User> findByDuplicateNumber(String phoneNumber);
	
	@Query(value = "SELECT * FROM user_details where password =:password ", nativeQuery = true)
	Optional<User> findByDuplicatePassword(String password);

	@Query(value = "select * from user_details where mobile_no =:mobileNo ", nativeQuery = true)
	Optional<User> getByMobileNo(String mobileNo);

	@Query(value = "SELECT * FROM user_details where user_name =:username",nativeQuery = true)
	Optional<User> findByUserName(String username);


}
