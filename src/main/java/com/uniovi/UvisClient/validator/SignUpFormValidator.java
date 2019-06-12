package com.uniovi.UvisClient.validator;

import org.springframework.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.services.UserService;

@Component
public class SignUpFormValidator implements Validator {

	@Autowired
	private UserService usersService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		this.checkUsername(user, errors);
		this.checkNameAndSurnames(user, errors);
		this.checkPassword(user, errors);		
	}
	
	/**
	 * Checks that the username introduced is correct. For an username to be right, it is
	 * necessary that it is not empty or duplicated, and it has at least 5 characters and 
	 * maximum 25 characters. 
	 * 
	 * @param user
	 * 			The new user to be added.
	 * @param errors
	 */
	private void checkUsername(User user, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.empty");
		
		if (user.getUsername().length() < 5 || user.getUsername().length() > 25) {
			errors.rejectValue("username", "error.signup.username.length");
		}
		if (usersService.getUserByUsername(user.getUsername())!=null) {
			errors.rejectValue("username", "error.signup.username.duplicated");
		}
	}
	
	/**
	 * Checks if the name and surnames introduced are correct. For them to be right, it is
	 * necessary that they are not empty, and they have at least 5 characters and maximum 25 characters. 
	 * 
	 * @param user
	 * 			The new user to be added.
	 * @param errors
	 */
	private void checkNameAndSurnames(User user, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname1", "error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname2", "error.empty");
		
		if (user.getName().length() < 2 || user.getName().length() > 25) {
			errors.rejectValue("name", "error.signup.name.length");
		}
		
		if (user.getSurname1().length() < 2 || user.getSurname1().length() > 25) {
			errors.rejectValue("surname1", "error.signup.surname.length");
		}
		
		if (user.getSurname2().length() < 2 || user.getSurname2().length() > 25) {
			errors.rejectValue("surname2", "error.signup.surname.length");
		}
	}
	
	/**
	 * It checks that the password introduced is valid and that the two passwords
	 * are equals.
	 * 
	 * @param user
	 * 			The new user to be added.
	 * @param errors
	 */
	private void checkPassword(User user, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "error.empty");
		
		if (user.getPassword().length() < 6 || user.getName().length() > 25) {
			errors.rejectValue("password", "error.signup.password.length");
		}
		
		if (!user.getPassword().equals(user.getPasswordConfirm())) {
			errors.rejectValue("passwordConfirm", "error.signup.password.coincidence");
		}
	}
}
