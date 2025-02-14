package com.app.attendance.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.security.auth.Subject;

import org.springframework.stereotype.Service;

import com.app.attendance.advice.ObjectInvalidException;
import com.app.attendance.entity.User;
import com.app.attendance.enumeration.RequestType;
import com.app.attendance.request.AdminRegister;
import com.app.attendance.response.MessageService;
import com.app.attendance.service.UserService;
import com.app.attendance.util.ValidationUtil;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminValidation {
	private final MessageService messageService;
	private final @NonNull UserService userService;
	List<String> errors = null;
	List<String> errorsObj = null;
	Optional<Subject> subject = null;
	
	public ValidationResult validate(RequestType requestType, AdminRegister request) {
		errors = new ArrayList<>();
		User user = null;

		if (ValidationUtil.isNullOrEmpty(request.getUserName())) {
			errors.add(messageService.messageResponse("register.user.name.required"));
		} else {
			request.setUserName(ValidationUtil.getFormattedString(request.getUserName()));
			if (!ValidationUtil.isUserNameValid(request.getUserName())) {
				errors.add(messageService.messageResponse("register.user.name.invalid"));
			}
		}

		if (ValidationUtil.isNullOrEmpty(ValidationUtil.getFormattedString(request.getEmail()))) {
			errors.add(messageService.messageResponse("register.email.required"));
		} else {
			request.setEmail(ValidationUtil.getFormattedString(request.getEmail()));
			if (!ValidationUtil.isValidEmailId(request.getEmail())) {
				errors.add(messageService.messageResponse("register.email.invalid"));
			}
		}

		if (ValidationUtil.isNullOrEmpty(request.getMobileNo())) {
			errors.add(messageService.messageResponse("register.mobile.required"));
		} else {
			request.setMobileNo(ValidationUtil.getFormattedString(request.getMobileNo()));
			if (!ValidationUtil.isValidMobileNumber(request.getMobileNo())) {
				errors.add(messageService.messageResponse("register.mobile.invalid"));
			}
		}

		if (ValidationUtil.isNullOrEmpty(request.getPassword())) {
			errors.add(messageService.messageResponse("register.password.required"));
		} else {
			request.setPassword(ValidationUtil.getFormattedString(request.getPassword()));
			if (!ValidationUtil.isValidPassword(request.getPassword())) {
				errors.add(messageService.messageResponse("register.password.invalid"));
			}
		}

		Optional<User> userDuplicateMailObj = userService.findByDuplicateEmail(request.getEmail());
		if (userDuplicateMailObj.isPresent()) {
			errors.add(messageService.messageResponse("user.register.email.duplicate"));
		}

		Optional<User> userDuplicateObj = userService.findByDuplicateUserName(request.getUserName());
		if (userDuplicateObj.isPresent()) {
			String[] params = new String[] { request.getUserName() };
			errors.add(messageService.messageResponse("user.name.duplicate", params));
		}

		Optional<User> userExist = userService.getMobileNos(request.getMobileNo());
		if (userExist.isPresent()) {
			String[] params = new String[] { request.getMobileNo() };
			errors.add(messageService.messageResponse("register.mobileno.exist", params));
		}

		Optional<User> userPasswordexit = userService.findByDuplicatePassword(request.getPassword());
		if (userPasswordexit.isPresent()) {
			String[] params = new String[] { request.getPassword() };
			errors.add(messageService.messageResponse("register.password.already.exist", params));
		}

		ValidationResult result = new ValidationResult();
		if (errors.size() > 0) {
			String errorMessage = errors.stream().map(a -> String.valueOf(a)).collect(Collectors.joining(", "));
			throw new ObjectInvalidException(errorMessage);
		}

		user = User.builder().email(request.getEmail()).mobileNo(request.getMobileNo()).password(request.getPassword())
				.userName(request.getUserName()).build();

		result.setObject(user);
		return result;
	}

}
